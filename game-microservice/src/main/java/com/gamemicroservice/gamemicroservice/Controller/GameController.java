package com.gamemicroservice.gamemicroservice.Controller;

import com.DTOLibrary.GameDTO;
import com.gamemicroservice.gamemicroservice.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class GameController {

    @Autowired
    GameService gameService;


    //
    @RequestMapping(method = POST, value = "/cardChosen")
    public boolean cardChosen(@CookieValue(value="id") Integer playerId, @RequestBody GameDTO gameDTO) {
        gameService.cardChosen(playerId, gameDTO);
        return true;
    }


    @RequestMapping(method = GET, value = "/isGameReady/{roomName}")
    public boolean isGameReady(@PathVariable String roomName){
        return gameService.isGameReady(roomName);
    }

    //Returns the results of a game
    @RequestMapping(method = GET, value = "/getGameResult/{roomName}")
    public GameDTO getGame(@PathVariable String roomName){
        return gameService.getGame(roomName);
    }

}