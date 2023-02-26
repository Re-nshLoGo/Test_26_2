package com.example.controller;

import com.example.dao.StdRepo;
import com.example.model.Book;
import com.example.model.Course;
import com.example.model.Student;
import com.example.service.CourseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    StdRepo stdRepo;
    @PostMapping("add-course")
    public ResponseEntity<String> addCourse(@RequestBody String reqcourse){
        Course course = setCourse(reqcourse);
        int id =  courseService.add(course);
        return new ResponseEntity<>("course added id "+id , HttpStatus.CREATED);

    }
    @PutMapping("/update-course/{id}")
    public ResponseEntity<String> updateBook(@PathVariable String id , @RequestBody String reqcourse){
        Course course = setCourse(reqcourse);
        courseService.update(course,id);
        return new ResponseEntity<>("course updated",HttpStatus.OK);
    }
    @GetMapping("get-course")
    public ResponseEntity<String> getcourse(@Nullable @RequestParam String id){
        JSONArray jsonArray = courseService.getcourses(id);
        return new ResponseEntity<>(jsonArray.toString(),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable String id){
        courseService.delete(id);
    }

    private Course setCourse(String reqcourse) {
        JSONObject jsonObject = new JSONObject(reqcourse);
        Course course = new Course();
        course.setID(jsonObject.getInt("couId"));
        course.setTitle(jsonObject.getString("title"));
        course.setDescription(jsonObject.getString("description"));
        course.setDuration(jsonObject.getInt("duration"));
        int stId = jsonObject.getInt("stdId");
        Student std = stdRepo.findById(stId).get();
        course.setStudent(std);
        return course;

    }
}
