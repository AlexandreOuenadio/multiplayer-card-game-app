package com.authmicroservice.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="table_auth")
public class Authentification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;



    //--------------CONSTRUCTOR---------------

    public Authentification() {
    }



    //-------------GETTERS AND SETTERS---------------------

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }
}

