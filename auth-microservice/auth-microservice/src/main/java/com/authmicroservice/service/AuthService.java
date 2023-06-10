package com.authmicroservice.service;

import com.DTOLibrary.UserDTO;
import com.authmicroservice.model.Authentification;
import com.authmicroservice.repository.AuthRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    //Function that generates a new token and saves it in the database
    public Integer generateToken() {
        Authentification authentification = new Authentification();
        Authentification authentification_created = authRepository.save(authentification);
        return authentification_created.getTokenId();
    }

    //Function that checks if a given token exists in the database
    public boolean checkToken(Integer tokenId) {
        return !authRepository.findById(tokenId).isEmpty();
    }


}
