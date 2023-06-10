package com.transactionmicroservice.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="table_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private Integer userId;

    private Integer cardId;
    @Column(name = "price")
    private Integer price;

    @Column(name = "action")
    private TransactionType action;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "recorded_at")
    private Date recorded_at;



    // ------ CONSTRUCTORS --------

    public Transaction() {

    }

    public Transaction(Integer userId, Integer cardId, Integer price, TransactionType action, Date recorded_at) {
        this.userId = userId;
        this.cardId = cardId;
        this.price = price;
        this.action = action;
        this.recorded_at = recorded_at;
    }



    //-------------GETTERS AND SETTERS---------------------

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public TransactionType getAction() {
        return action;
    }

    public void setAction(TransactionType action) {
        this.action = action;
    }

    public Date getRecorded_at() {
        return recorded_at;
    }

    public void setRecorded_at(Date recorded_at) {
        this.recorded_at = recorded_at;
    }
}
