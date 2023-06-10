package com.DTOLibrary;

import java.util.List;

public class CardDTO {
    private Integer cardId;

    private String name;

    private Integer hp;

    private Integer mp;

    private Integer attack;

    private Integer defence;

    private String family;

    private String description;

    private Integer price;

    private String imgURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefence() {
        return defence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }


    // ------ CONSTRUCTOR --------

    public void Card(){

    }
    public void  Card(String name, Integer hp, Integer mp, Integer attack, Integer defence, String family, String description, Integer price, String imgURL) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.family = family;
        this.description = description;
        this.price = price;
        this.imgURL = imgURL;

    }
}

