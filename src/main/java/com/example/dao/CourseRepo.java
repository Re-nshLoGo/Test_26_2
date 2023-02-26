package com.example.dao;

import com.example.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
}
