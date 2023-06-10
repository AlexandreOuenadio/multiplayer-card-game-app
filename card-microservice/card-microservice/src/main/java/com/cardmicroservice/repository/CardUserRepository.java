package com.cardmicroservice.repository;

import com.cardmicroservice.model.Card;
import com.cardmicroservice.model.CardUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardUserRepository extends CrudRepository<CardUser, Integer> {
            List<CardUser> findByUserId(Integer userId);


}
