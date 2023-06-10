package com.authmicroservice.controller;

import com.DTOLibrary.UserDTO;
import com.authmicroservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    //Returns true if the token in cookies is valid, otherwise wrong
    @RequestMapping(method= RequestMethod.GET, path = "/authentification")
    public boolean authentification(HttpServletRequest request, HttpServletResponse response) {

        // Retrieve all cookies
        Cookie[] cookies = request.getCookies();
        // Check if the desired cookie exists
        boolean cookieExists = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    Integer tokenId = Integer.valueOf(cookie.getValue());
                    cookieExists = true;
                    return authService.checkToken(tokenId);
                }
            }
        }

        //If token not stored in cookie yet, create it
        if (!cookieExists) {
            Cookie cookie = new Cookie("token", Integer.toString(authService.generateToken()));
            cookie.setMaxAge(3600); // Durée de vie du cookie en secondes (Connexion accordée pour 1h)
            response.addCookie(cookie);
            return true;
        }

        return false;
    }


}
