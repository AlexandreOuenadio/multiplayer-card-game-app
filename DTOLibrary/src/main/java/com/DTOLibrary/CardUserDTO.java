package com.DTOLibrary;


public class CardUserDTO extends CardDTO {

    private Integer userId;
    private Integer cardId;
    private Integer cardUserId;

    public CardUserDTO() {
    }

    public CardUserDTO(Integer userId, Integer cardId) {
        this.userId = userId;
        this.cardId = cardId;
    }

    public CardUserDTO(Integer cardUserId) {
        this.cardUserId = cardUserId;
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

    public Integer getCardUserId() {
        return cardUserId;
    }

    public void setCardUserId(Integer cardUserId) {
        this.cardUserId = cardUserId;
    }
}

