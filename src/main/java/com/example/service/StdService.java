package com.example.service;

import com.example.dao.StdRepo;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StdService {
    @Autowired
    StdRepo stdRepo;
    public int  save(Student student) {
       Student st =  stdRepo.save(student);
       return st.getStd_id();
    }

    public List<Student> getstd(String id) {
        List<Student> students = null;
        if(id != null && stdRepo.findById(Integer.valueOf(id)).isPresent()){
            Student st = stdRepo.findById(Integer.valueOf(id)).get();
            students.add(st);
        }else {
            students = stdRepo.findAll();
        }
        return students;
    }

    public void update(String id, Student student) {
        if (id != null && stdRepo.findById(Integer.valueOf(id)).isPresent()) {
            Student oldstd = stdRepo.findById(Integer.valueOf(id)).get();
            oldstd.setStd_id(student.getStd_id());
            oldstd.setAge(student.getAge());
            oldstd.setName(student.getName());
            oldstd.setBranch(student.getBranch());
            oldstd.setAddress(student.getAddress());
            oldstd.setDepartment(student.getDepartment());
            oldstd.setPhoneNum(student.getPhoneNum());
            stdRepo.save(oldstd);
        }
    }

    public void delet(String id) {
        if (id != null && stdRepo.findById(Integer.valueOf(id)).isPresent()) {
             stdRepo.deleteById(Integer.valueOf(id));
        }
    }
}
