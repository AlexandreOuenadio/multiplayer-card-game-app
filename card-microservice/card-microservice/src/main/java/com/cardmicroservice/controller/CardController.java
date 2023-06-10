package com.cardmicroservice.controller;


import com.DTOLibrary.CardDTO;
import com.DTOLibrary.CardUserDTO;
import com.cardmicroservice.model.Card;
import com.cardmicroservice.model.CardUser;
import com.cardmicroservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;


    //Updates the card of a given user. Takes a DTO containing userId and cardUserID
    @RequestMapping(method = PUT, value = "/updateCardUser")
    public void updateCardUser(@RequestBody CardUserDTO cardUserDTO) {
        cardService.updateCardUser(cardUserDTO);
    }




    //Returns the CardDTO corresponding to the given id
    @RequestMapping(method = GET, value = "/card/{cardId}")
    public CardDTO findCard(@PathVariable String cardId) {
        return cardService.findCard(Integer.valueOf(cardId));
    }

    //Returns the CardUserDTO corresponding to the given id
    @RequestMapping(method = GET, value = "/cardUser/{cardId}")
    public CardUserDTO findCardUser(@PathVariable String cardId) {
        return cardService.findCardUser(Integer.valueOf(cardId));
    }



    //Returns all the Cards through a list of DTOs
    @RequestMapping(method = GET, value = "/getAllCards")
    public List<CardDTO> getAllCards() {
        return cardService.getAllCards();
    }

    //Returns all the CardUser through a list of DTOs
    @RequestMapping(method = GET, value = "/getUserCards/{userId}")
    public List<CardUserDTO> getUserCards(@PathVariable Integer userId) {
        return cardService.getUserCards(userId);
    }





    //Assigns a given card to a given user (info in DTO)
    @RequestMapping(method = POST, value = "/assignCardToUser")
    public void assignCardToUser(@RequestBody CardUserDTO cardUserDTO) {

        cardService.assignCardToUser(cardUserDTO);

    }

    //Unassigns a given card to its user
    @RequestMapping(method = POST, value = "/unassignCardToUser")
    public void unassignCardToUser(@RequestBody CardUserDTO cardUserDTO) {
        cardService.unassignCardToUser(cardUserDTO);
    }




    //Generate a deck of 5 cards to a given user
    @RequestMapping(method = POST, value = "/generateDeck")
    public void generateDeck(@RequestBody CardUserDTO cardUserDTO) {
        System.out.println("Information was passed to Card microservice");
        cardService.generateDeck(cardUserDTO.getUserId());
    }


}
