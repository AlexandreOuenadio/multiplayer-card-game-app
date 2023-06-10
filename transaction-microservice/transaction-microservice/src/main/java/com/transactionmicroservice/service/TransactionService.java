package com.transactionmicroservice.service;


import com.DTOLibrary.CardDTO;
import com.DTOLibrary.CardUserDTO;
import com.DTOLibrary.UserDTO;
import com.transactionmicroservice.model.Transaction;
import com.transactionmicroservice.model.TransactionType;
import com.transactionmicroservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    private final String URL_USER = "http://localhost/userMicroservice";
    private final String URL_CARD = "http://localhost/cardMicroservice";


    //A user buys a card, so it assigns the card to him and updates his money
    public void buy(Integer userId, Integer cardId) {
        UserDTO userDTO = getUserData(userId);
        CardDTO cardDTO = getCardData(cardId);

        if (userDTO.getMoney() >= cardDTO.getPrice()) {
            if (assignCardToUser(userId, cardId)) {
                userDTO.setMoney(userDTO.getMoney() - cardDTO.getPrice());
                updateUserMoney(userDTO);
            }
        }
    }

    //A user sells a card, so it unassigns the card to him and updates his money
    public void sell(Integer userId, Integer cardUserId) {
        UserDTO userDTO = getUserData(userId);
        CardUserDTO cardUserDTO = getCardUserData(cardUserId);

        if (unassignCardToUser(cardUserId)) {
            userDTO.setMoney(userDTO.getMoney() + cardUserDTO.getPrice());
            updateUserMoney(userDTO);
        }
    }

    //Saves the given transaction info into the database
    private void logTransaction(Integer userId, Integer cardId, Integer price, TransactionType action) {
        Transaction transaction = new Transaction(userId, cardId, price, action, new Date());
        transactionRepository.save(transaction);
    }





    //----------COMMUNICATION FUNCTIONS---------------


    //Returns a user's info from the User-microservice. Communication function
    private UserDTO getUserData(Integer userId) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();


        String URL_USER_COPY = URL_USER.substring(0);
        URL_USER_COPY += "/" + userId;
        HttpEntity<UserDTO> userDTO = new HttpEntity<UserDTO>(headers);
        // Send request with GET method, and Headers.
        ResponseEntity<UserDTO> user_response = restTemplate.exchange(URL_USER_COPY, //
                HttpMethod.GET, userDTO, UserDTO.class);

        return user_response.getBody();
    }

    //Returns a card's info from the Card-microservice. Communication function
    private CardDTO getCardData(Integer cardId) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        String URL_CARD_COPY = URL_CARD.substring(0);
        URL_CARD_COPY += "/card/" + cardId;
        HttpEntity<CardDTO> cardDTO = new HttpEntity<CardDTO>(headers);
        // Send request with GET method, and Headers.
        ResponseEntity<CardDTO> card_response = restTemplate.exchange(URL_CARD_COPY, //
                HttpMethod.GET, cardDTO, CardDTO.class);

        return card_response.getBody();
    }

    //Returns a CardUser's info from the CardUser-microservice. Communication function
    private CardUserDTO getCardUserData(Integer cardUserId) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        String URL_CARD_COPY = URL_CARD.substring(0);
        URL_CARD_COPY += "/cardUser/" + cardUserId;
        HttpEntity<CardUserDTO> cardUserDTO = new HttpEntity<CardUserDTO>(headers);
        // Send request with GET method, and Headers.
        ResponseEntity<CardUserDTO> card_user_response = restTemplate.exchange(URL_CARD_COPY, //
                HttpMethod.GET, cardUserDTO, CardUserDTO.class);

        return card_user_response.getBody();
    }

    //Updates the money of a given user by calling the User_Microservice. Communication function
    private boolean updateUserMoney(UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<UserDTO> requestBody = new HttpEntity<>(userDTO, headers);

        String URL_USER_COPY = URL_USER.substring(0);
        URL_USER_COPY += "/updateUserMoney";
        // Send request with PUT method.
        restTemplate.exchange(URL_USER_COPY, HttpMethod.PUT, requestBody, Void.class);
        return true;
    }

    //Assigns a card to a given user, updating the Card microservice. Communication function
    private boolean assignCardToUser(Integer userId, Integer cardId) {
        CardUserDTO cardUserDTO = new CardUserDTO(userId, cardId);
        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<CardUserDTO> requestBody = new HttpEntity<>(cardUserDTO);

        String URL_CARD_COPY = URL_CARD.substring(0);
        URL_CARD_COPY += "/assignCardToUser";
        // Send request with POST method.
        ResponseEntity<CardUserDTO> result
                = restTemplate.postForEntity(URL_CARD_COPY, requestBody, CardUserDTO.class);
        return true;
    }

    //Unassigns a card to a given user, updating the Card microservice. Communication function
    private boolean unassignCardToUser(Integer cardUserId) {
        CardUserDTO cardUserDTO = new CardUserDTO(cardUserId);
        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<CardUserDTO> requestBody = new HttpEntity<>(cardUserDTO);

        String URL_CARD_COPY = URL_CARD.substring(0);
        URL_CARD_COPY += "/unassignCardToUser";
        // Send request with POST method.
        ResponseEntity<CardUserDTO> result
                = restTemplate.postForEntity(URL_CARD_COPY, requestBody, CardUserDTO.class);
        return true;
    }


}
