package com.usermicroservice.controller;

import com.DTOLibrary.UserDTO;
import com.usermicroservice.model.User;
import com.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //Returns a given User's data
    @RequestMapping(method= RequestMethod.GET, path = "/{userId}")
    public UserDTO getUserData(@PathVariable Integer userId) {
        return userService.getUserData(userId);
    }

    //Updates a given user's money count
    @RequestMapping(method= RequestMethod.PUT, path = "/updateUserMoney")
    public void updateUserMoney(@RequestBody UserDTO userDTO) {
        userService.updateUserMoney(userDTO);
    }

    //Registers a new user
    @RequestMapping(method= RequestMethod.POST, path = "/register")
    public void register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);

  }


    //Logins a user
    @RequestMapping(method= RequestMethod.POST, path = "/login")
    public void login(@RequestBody UserDTO userDTO, HttpServletResponse response){
        User user = userService.login(userDTO);
        if (user != null) {
            //Création d'un cookie portant l'id de l'utilisateur dans la base de donnée
            Cookie cookie = new Cookie("id", Integer.toString(user.getUserId()));
            cookie.setMaxAge(3600); // Durée de vie du cookie en secondes (Connexion accordée pour 1h)
            cookie.setPath("/");
            response.addCookie(cookie);
        }else{
            throw new RuntimeException("Mauvais identifiants");
        }



    }

    //logouts a user
    @RequestMapping(method= RequestMethod.POST, path = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    // Définir la durée de vie du cookie à 0 pour le supprimer
                    cookie.setMaxAge(0);

                    // Définir le chemin du cookie pour s'assurer qu'il correspond à celui d'origine
                    cookie.setPath("/");

                    // Ajouter le cookie modifié à la réponse pour le supprimer
                    response.addCookie(cookie);

                    break;
                }
            }
        }

    }


}
