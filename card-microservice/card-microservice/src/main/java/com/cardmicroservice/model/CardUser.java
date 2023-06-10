package com.cardmicroservice.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "table_card_user")
public class CardUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardUserId;
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="cardId")
    private Card card;
    private Integer userId;
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


    //-------------GETTERS AND SETTERS---------------------

    public Integer getCardUserId() {
        return cardUserId;
    }

    public void setCardUserId(Integer cardUserId) {
        this.cardUserId = cardUserId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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
