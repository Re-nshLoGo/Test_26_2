package com.example.controller;

import com.example.dao.StdRepo;
import com.example.model.Book;
import com.example.model.Course;
import com.example.model.Laptop;
import com.example.model.Student;
import com.example.service.LappyService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class LappyController {
    @Autowired
    LappyService lappyService;
    @Autowired
    StdRepo stdRepo;
    @PostMapping("add-laptop")
    public ResponseEntity<String> addLap(@RequestBody String lappy){
        Laptop laptop = setlaptop(lappy);
        int id = lappyService.add(laptop);
        return new ResponseEntity<>("added id "+id, HttpStatus.CREATED);
    }
    @GetMapping("get-laptop")
    public ResponseEntity<String> getLap(@Nullable @RequestParam String id){
        JSONArray jsonArray = lappyService.getlaps(id);
        return new ResponseEntity<>(jsonArray.toString(),HttpStatus.OK);
    }
    @PutMapping("update-lappy/{id}")
    public String updateLappy(@PathVariable String id , @RequestBody String reqlappy){
        Laptop laptop = setlaptop(reqlappy);
        lappyService.update(laptop,id);
        return "updated";
    }
    @DeleteMapping("/delete/{id}")
    public void deletelap(@PathVariable String id){
        lappyService.delete(id);
    }
    private Laptop setlaptop(String reqlappy) {
        JSONObject jsonObject = new JSONObject(reqlappy);
        Laptop laptop = new Laptop();
        laptop.setID(jsonObject.getInt("lappId"));
        laptop.setName(jsonObject.getString("name"));
        laptop.setBrand(jsonObject.getString("brand"));
        laptop.setPrice(jsonObject.getInt("price"));
        int stId = jsonObject.getInt("stdId");
        Student std = stdRepo.findById(stId).get();
        laptop.setStudent(std);
        return laptop;
    }
}