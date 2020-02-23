package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class user implements Serializable {
    private Integer id;
    private String account;
    private Integer password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }
}
