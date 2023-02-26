package com.example.service;

import com.example.dao.LappyRepo;
import com.example.model.Book;
import com.example.model.Laptop;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LappyService {
    @Autowired
    LappyRepo lappyRepo;
    public int add(Laptop laptop) {
        Laptop laptop1 = lappyRepo.save(laptop);
        return laptop1.getID();
    }

    public void update(Laptop laptop, String id) {
        if(lappyRepo.findById(Integer.valueOf(id)).isPresent()){
            Laptop oldlaptop = lappyRepo.findById(Integer.valueOf(id)).get();
            oldlaptop.setStudent(laptop.getStudent());
            oldlaptop.setName(laptop.getName());
            oldlaptop.setPrice(laptop.getPrice());
            oldlaptop.setBrand(laptop.getBrand());
            lappyRepo.save(oldlaptop);
        }
    }

    public JSONArray getlaps(String id) {
        JSONArray jsonArray = new JSONArray();
        if(id != null && lappyRepo.findById(Integer.valueOf(id)).isPresent()){
            Laptop laptop = lappyRepo.findById(Integer.valueOf(id)).get();
            if (laptop != null) {
                JSONObject json = getJsonobj(laptop);
                jsonArray.put(json);
            }
        }
        else {
            List<Laptop> laptops = lappyRepo.findAll();
            for (Laptop laptop : laptops) {
                JSONObject json = getJsonobj(laptop);
                jsonArray.put(json);
            }
        }
        return jsonArray;
    }

    private JSONObject getJsonobj(Laptop laptop) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lappyId",laptop.getID());
        jsonObject.put("brand",laptop.getBrand());
        jsonObject.put("name",laptop.getName());
        jsonObject.put("student",laptop.getStudent());
        jsonObject.put("price",laptop.getPrice());
        return jsonObject;

    }

    public void delete(String id) {
        if (id != null && lappyRepo.findById(Integer.valueOf(id)).isPresent()) {
            lappyRepo.deleteById(Integer.valueOf(id));
        }
    }
}
