package com.example.service;

import com.example.dao.CourseRepo;
import com.example.model.Book;
import com.example.model.Course;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepo courseRepo;
    public int add(Course course) {
        Course course1 = courseRepo.save(course);
        return course1.getID();
    }

    public JSONArray getcourses(String id) {
            JSONArray jsonArray = new JSONArray();
            if(id != null && courseRepo.findById(Integer.valueOf(id)).isPresent()){
                Course course = courseRepo.findById(Integer.valueOf(id)).get();
                if (course != null) {
                    JSONObject json = getJsonobj(course);
                    jsonArray.put(json);
                }
            }
            else {
                List<Course> courses = courseRepo.findAll();
                for (Course course : courses) {
                    JSONObject json = getJsonobj(course);
                    jsonArray.put(json);
                }
            }
            return jsonArray;
        }

        private JSONObject getJsonobj(Course course) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseId",course.getID());
            jsonObject.put("title",course.getTitle());
            jsonObject.put("description",course.getDescription());
            jsonObject.put("duration",course.getDuration());
            jsonObject.put("student",course.getStudent());
            return jsonObject;

        }


    public void delete(String id) {
        if (courseRepo.findById(Integer.valueOf(id)).isPresent()) {
            courseRepo.deleteById(Integer.valueOf(id));
        }
    }

    public void update(Course course, String id) {
        if(courseRepo.findById(Integer.valueOf(id)).isPresent()){
            Course oldcourse = courseRepo.findById(Integer.valueOf(id)).get();
            oldcourse.setStudent(course.getStudent());
            oldcourse.setID(course.getID());
            oldcourse.setTitle(course.getTitle());
            oldcourse.setDuration(course.getDuration());
            oldcourse.setStudent(course.getStudent());
            oldcourse.setDescription(course.getDescription());
            courseRepo.save(oldcourse);
        }
    }
}
