package com.example.service;

import com.example.dao.AddRepo;
import com.example.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddService {
    @Autowired
    AddRepo addRepo;
    public void add(Address address) {
        addRepo.save(address);
    }
    public List<Address> getAll(){
        List<Address> list = addRepo.findAll();
        return list;
    }
}
