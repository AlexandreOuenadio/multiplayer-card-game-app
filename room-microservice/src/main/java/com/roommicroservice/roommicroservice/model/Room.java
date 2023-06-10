package com.roommicroservice.roommicroservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {


    @Id
    @GeneratedValue
    private Integer roomId;

    private Integer player1Id;
    private Integer player2Id;

    private String name;
    private Integer bet;



    // ------ CONSTRUCTORS --------

    public Room(Integer roomId, Integer player1Id, String name, Integer bet) {
        this.roomId = roomId;
        this.player1Id = player1Id;
        this.name = name;
        this.bet = bet;
    }

    public Room() {

    }



    //-------------GETTERS AND SETTERS---------------------


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Integer player1Id) {
        this.player1Id = player1Id;
    }

    public Integer getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Integer player2Id) {
        this.player2Id = player2Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }
}
