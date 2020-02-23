package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class manager implements Serializable {
    private Integer id;
    private String account;
    private Integer password;
}