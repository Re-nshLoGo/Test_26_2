package com.example.controller;

import com.example.model.Address;
import com.example.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddController {
    @Autowired
    AddService addService;

    @PostMapping("set-address")
    public ResponseEntity<String> saveAdd(@RequestBody Address address){
        addService.add(address);
        return new ResponseEntity<>("save", HttpStatus.CREATED);
    }
    @GetMapping("get-all")
    public List<Address> findAll() {
        return addService.getAll();
    }
}
