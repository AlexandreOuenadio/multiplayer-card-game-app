package com.DTOLibrary;

import org.springframework.stereotype.Component;

public class UserDTO {

    private Integer userId;
    private String name;
    private Integer money= 0;

    private String password;
    public UserDTO() {

    }

    public UserDTO(Integer userId, String name, Integer money) {
        this.userId = userId;
        this.name = name;
        this.money = money;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}


