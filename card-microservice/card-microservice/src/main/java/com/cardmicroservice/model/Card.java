package com.cardmicroservice.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="table_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    @OneToMany(mappedBy = "card")
    private List<CardUser> cardUserList;
    @NotNull
    private String name;
    @NotNull
    private Integer hp;
    @NotNull
    private Integer mp;
    @NotNull
    private Integer attack;
    @NotNull
    private Integer defence;
    @NotNull
    private String family;
    @NotNull
    private String description;
    @NotNull
    private Integer price;
    @NotNull
    private String imgURL;



    // ------ CONSTRUCTOR --------

    public Card() {

    }

    public Card(String name, Integer hp, Integer mp, Integer attack, Integer defence, String family, String description, Integer price, String imgURL) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.attack = attack;
        this.defence = defence;
        this.family = family;
        this.description = description;
        this.price = price;
        this.imgURL = imgURL;
    }


    //-------------GETTERS AND SETTERS---------------------

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public List<CardUser> getCardUserList() {
        return cardUserList;
    }

    public void setCardUserList(List<CardUser> cardUserList) {
        this.cardUserList = cardUserList;
    }

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

}

