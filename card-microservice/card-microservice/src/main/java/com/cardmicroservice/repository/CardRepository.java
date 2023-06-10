package com.cardmicroservice.repository;

import com.cardmicroservice.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {

}
