package com.numsource.artproject.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private long userId;
    private String emailAddress;
    private String password;
    private String address;
    private String role;
}
