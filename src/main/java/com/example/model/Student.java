package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student_tbl")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer std_id;
    private String name;
    private Integer age;
    private String phoneNum;
    private String branch;
    private String department;

    @Embedded
    private Address address;

}
