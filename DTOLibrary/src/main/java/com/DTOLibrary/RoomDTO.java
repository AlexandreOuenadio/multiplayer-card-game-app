package com.DTOLibrary;

public class RoomDTO {

    private Integer player1Id;
    private Integer player2Id;

    private String name;
    private Integer bet;

    public RoomDTO() {
    }

    public RoomDTO(Integer player1Id, Integer player2Id, String name, Integer bet) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.name = name;
        this.bet = bet;
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
