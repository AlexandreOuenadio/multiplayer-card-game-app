package com.usermicroservice.service;

import com.DTOLibrary.CardUserDTO;
import com.DTOLibrary.UserDTO;
import com.usermicroservice.model.User;
import com.usermicroservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final String URL_CARD = "http://localhost/cardMicroservice/generateDeck";


    //Gets a given user's data from the database and returns it
    public UserDTO getUserData(Integer userId) {
        User user = userRepository.findById(userId).get();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setPassword(null); //We don't show the password
        return userDTO;
    }

    //Updates a user's money count into the database
    public void updateUserMoney(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId()).get();
        user.setMoney(userDTO.getMoney());
        userRepository.save(user);
    }

    //Registers a new user into the database
    public void register(UserDTO userDTO) {
        if (!userExist(userDTO.getName())) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            User user2 = userRepository.save(user);
            generateDeck(user2.getUserId());
        }

    }

    //Checks whether a given user exists in the database or not
    private boolean userExist(String name) {
        return userRepository.findByName(name) != null;
    }
    
    // Méthode utilisée pour les tests du service
    public User getUser(int id) {
		Optional<User> hOpt = userRepository.findById(id);
		if (hOpt.isPresent()) {
			return hOpt.get();
		}else {
			return null;
		}
	}

    //Logins a given user
    public User login(UserDTO userDTO) {
        return userRepository.findByNameAndPassword(userDTO.getName(), userDTO.getPassword());
    }




    //---------COMMUNCATION FUNCTION----------------


    //Calls the card-microservice to generate the deck of the given user. Communication function
    private void generateDeck(Integer userId) {
        System.out.println(userId);
        System.out.println("Generate deck called in user microservice");
        CardUserDTO cardUserDTO = new CardUserDTO();
        cardUserDTO.setUserId(userId);
        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<CardUserDTO> requestBody = new HttpEntity<>(cardUserDTO);

        // Send request with POST method.
        restTemplate.postForEntity(URL_CARD, requestBody, CardUserDTO.class);
    }


}
