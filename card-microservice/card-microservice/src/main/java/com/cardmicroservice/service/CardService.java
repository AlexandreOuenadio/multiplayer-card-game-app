package com.cardmicroservice.service;


import com.DTOLibrary.CardDTO;
import com.DTOLibrary.CardUserDTO;
import com.cardmicroservice.model.Card;
import com.cardmicroservice.model.CardUser;
import com.cardmicroservice.repository.CardRepository;
import com.cardmicroservice.repository.CardUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CardService {


    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardUserRepository cardUserRepository;




    //Updates cardUser by saving new carduser info
    public void updateCardUser(CardUserDTO cardUserDTO){
        CardUser cardUser = cardUserRepository.findById(cardUserDTO.getCardId()).get();
        BeanUtils.copyProperties(cardUserDTO, cardUser);
        cardUserRepository.save(cardUser);
    }





    //Finds a given card by returning a DTO of its info
    public CardDTO findCard(Integer cardId){
        CardDTO cardDTO = new CardDTO();
        BeanUtils.copyProperties(cardRepository.findById(cardId).get(), cardDTO);
        return cardDTO;

    }

    //Finds a given cardUser by returning a DTO of its info
    public CardUserDTO findCardUser(Integer cardId){
        CardUserDTO cardUserDTO = new CardUserDTO();
        BeanUtils.copyProperties(cardUserRepository.findById(cardId).get(), cardUserDTO);
        return cardUserDTO;

    }




    //Gets all the existing cards in the database and returns them
    public List<CardDTO> getAllCards(){
        List<CardDTO> cards = new ArrayList<CardDTO>();

        for (Card card: cardRepository.findAll()){

            CardDTO cardDTO = new CardDTO();
            BeanUtils.copyProperties(card, cardDTO);
            cards.add(cardDTO);
        }

        return cards;
    }

    //Gets all the existing cardUser in the database and returns them
    public List<CardUserDTO> getUserCards(Integer userId){
        List<CardUserDTO> cards = new ArrayList<CardUserDTO>();

        for (CardUser cardUser : cardUserRepository.findByUserId(userId)) {
            CardUserDTO cardUserDTO = new CardUserDTO();
            BeanUtils.copyProperties(cardUser, cardUserDTO);
            cards.add(cardUserDTO);
        }
        return cards;

    }




    //Assigns a card to a given user by creating a cardUser with given card and user info
    public void assignCardToUser(CardUserDTO cardUserDTO) {

        CardUser cardUser = new CardUser();
        Card card = cardRepository.findById(cardUserDTO.getCardId()).get();

        BeanUtils.copyProperties(card, cardUser);
        cardUser.setUserId(cardUserDTO.getUserId());

        cardUserRepository.save(cardUser);


    }

    //Unassigns a card to its user by simply deleting the cardUser from the database
    public void unassignCardToUser(CardUserDTO cardUserDTO) {
        //body contient cardUserId qui est récupérer du front
        cardUserRepository.deleteById(Integer.valueOf(cardUserDTO.getCardUserId()));
    }





    //Generates a given user's deck by assigning 5 random cards to him
    public void generateDeck(Integer userId) {
        System.out.println(userId);

        for (int i=0; i < 5; i++) {
            //Génération de Id
            Random random = new Random();
            int cardId = random.nextInt((int)cardRepository.count())+1;
            System.out.println(cardId);

            CardUser cardUser = new CardUser();
            Card card = cardRepository.findById(cardId).get();
            BeanUtils.copyProperties(card, cardUser);
            cardUser.setUserId(userId);
            cardUserRepository.save(cardUser);

        }
        System.out.println("Generation done");

    }






}
