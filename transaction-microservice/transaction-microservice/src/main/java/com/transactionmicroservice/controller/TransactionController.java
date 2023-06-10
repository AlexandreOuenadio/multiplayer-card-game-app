package com.transactionmicroservice.controller;


import com.DTOLibrary.UserDTO;
import com.transactionmicroservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class TransactionController {
    static String URL_USER = "http://localhost:80/user";

    @Autowired
    private TransactionService transactionService;

    //A given user buys a given cardUser
    @RequestMapping(method = RequestMethod.POST , value = "/buy/{cardId}")
    public void buy(@CookieValue(value="id") Integer userId, @PathVariable Integer cardId) {
        transactionService.buy(userId, cardId);
    }

    //A given user sells a given cardUser
    @RequestMapping(method = RequestMethod.POST , value = "/sell/{cardUserId}")
    public void sell(@CookieValue(value="id") Integer userId, @PathVariable Integer cardUserId) {
        transactionService.sell(userId, cardUserId);
    }


}
