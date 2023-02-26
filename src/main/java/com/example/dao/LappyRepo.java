package com.example.dao;

import com.example.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LappyRepo extends JpaRepository<Laptop,Integer> {
}
