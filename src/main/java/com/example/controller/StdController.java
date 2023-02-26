package com.example.controller;

import com.example.model.Student;
import com.example.service.StdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StdController {
    @Autowired
    StdService stservice;

    @PostMapping("/add-student")
    public ResponseEntity<String> addSt(@RequestBody Student student){
        int id = stservice.save(student);
        return new ResponseEntity<>("Saved student id "+id, HttpStatus.CREATED);
    }
    @GetMapping("/get-student")
    public List<Student> getSt(@Nullable @RequestParam String id){
        return stservice.getstd(id);
    }
    @PutMapping("update-student/{id}")
    public String updateSt(@PathVariable String id , @RequestBody Student student){
        stservice.update(id,student);
        return "updated";
    }
    @DeleteMapping("/delete/{id}")
    public void deleteSt(@PathVariable String id){
        stservice.delet(id);
    }

}
