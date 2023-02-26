package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Add_tbl")
@Embeddable
public class Address {
    private String landmark;
    private String zipcode;
    private String district;
    private String state;
    private String country;
}
