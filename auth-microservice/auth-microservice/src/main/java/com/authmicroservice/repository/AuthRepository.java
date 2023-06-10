package com.authmicroservice.repository;

import com.authmicroservice.model.Authentification;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<Authentification,Integer>{

}



