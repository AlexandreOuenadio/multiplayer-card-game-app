package com.usermicroservice.model;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;


import javax.persistence.*;
@Entity
@Table(name="table_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotNull
    private String name;
    @NotNull
    private String password;

    private Integer money = 0;

    private boolean new_user = true;



    // ---- CONSTRUCTOR ----

    public User(){

    }
    
    public User(Integer userId, String name, String password, Integer money, boolean new_user) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.money = money;
        this.new_user = new_user;
    }



    //-------------GETTERS AND SETTERS---------------------

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public boolean isNew_user() {
        return new_user;
    }

    public void setNew_user(boolean new_user) {
        this.new_user = new_user;
    }

    // --------- METHODS ---------
    @Override
	public String toString() {
		return "USER ["+this.userId+"]: name:"+this.name+", password:"+this.password+", money:"+this.money+" new_user:"+this.new_user;
	}


    







}
