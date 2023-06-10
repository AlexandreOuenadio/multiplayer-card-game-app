package com.gamemicroservice.gamemicroservice.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Game {


    @Id
    @GeneratedValue
    private Integer gameId;


    private Integer player1Id;
    private Integer player2Id;

    private String roomName;
    private Integer bet;

    private Integer player1CardId;

    private Integer player2CardId;

    private String fightLog ;

    private Integer winner;




    // ------ CONSTRUCTORS --------

    public Game() {
    }

    public Game(String roomName) {
        this.roomName = roomName;
    }

    public Game(Integer gameId, Integer player1Id, Integer player2Id, String roomName, Integer bet, Integer player1CardId, Integer player2CardId, String fightLog, Integer winner) {
        this.gameId = gameId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.roomName = roomName;
        this.bet = bet;
        this.player1CardId = player1CardId;
        this.player2CardId = player2CardId;
        this.fightLog = fightLog;
        this.winner = winner;
    }




    //-------------GETTERS AND SETTERS---------------------


    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public Integer getPlayer1CardId() {
        return player1CardId;
    }

    public void setPlayer1CardId(Integer player1CardId) {
        this.player1CardId = player1CardId;
    }

    public Integer getPlayer2CardId() {
        return player2CardId;
    }

    public void setPlayer2CardId(Integer player2CardId) {
        this.player2CardId = player2CardId;
    }

    public String getFightLog() {
        return fightLog;
    }

    public void setFightLog(String fightLog) {
        this.fightLog = fightLog;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }
}

