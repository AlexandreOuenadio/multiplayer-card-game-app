package com.gamemicroservice.gamemicroservice.Service;


import com.DTOLibrary.*;
import com.gamemicroservice.gamemicroservice.Model.Game;
import com.gamemicroservice.gamemicroservice.Repository.GameRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    private final String URL_ROOM_OF_USER = "http://localhost/roomMicroservice/getRoomOfPlayer";
    private final String URL_GET_USER_DATA = "http://localhost/userMicroservice/";
    private final String URL_GET_CARD_USER = "http://localhost/cardMicroservice/cardUser/";
    private final String URL_UPDATE_USER_MONEY = "http://localhost/userMicroservice/updateUserMoney";
    private final String URL_UPDATE_CARD_USER = "http://localhost/cardMicroservice/updateCardUser";

    @Autowired
    GameRepository gameRepository;



    //Checks if a given game is ready, i.e. if its winner is decided
    public boolean isGameReady(String roomName){
        Game game = gameRepository.findByRoomName(roomName);
        if (game.getWinner() != null) {
            return true;
        }
        else{
            return false;
        }
    }


    //Fetches and returns a given game
    public GameDTO getGame(String roomName){
        GameDTO gameDTO = new GameDTO();
        Game game = gameRepository.findByRoomName(roomName);
        BeanUtils.copyProperties(game, gameDTO);
        return gameDTO;
    }


    //Inputs a given user's choice of card into the game
    public void cardChosen(Integer playerId, GameDTO gameDTO) {

        //Fetch player's room
        String roomName = getRoomOfPlayer(playerId).getName();
        Game existingGame = gameRepository.findByRoomName(roomName);


        //If the game doesn't exist, create it and add player1 card
        if (existingGame == null) {
            Game newGame = new Game(roomName);
            newGame.setBet(getRoomOfPlayer(playerId).getBet());
            System.out.println(getRoomOfPlayer(playerId).getBet());
            newGame.setPlayer1CardId(gameDTO.getPlayer1CardId());
            gameRepository.save(newGame);
        }

        //If the game exists, add player2 card and launch fight
        else {
            existingGame.setPlayer2CardId(gameDTO.getPlayer1CardId());
            existingGame.setBet(getRoomOfPlayer(playerId).getBet());
            fight(roomName);
        }
    }


    //Processes the fighting consequences
    private void fight(String roomName) {

        //Fetch game and card info
        Game game = gameRepository.findByRoomName(roomName);

        CardUserDTO player1Card = getCardUserData(game.getPlayer1CardId());
        CardUserDTO player2Card = getCardUserData(game.getPlayer2CardId());


        //Compute fight winner and apply card changes
        List<CardUserDTO> winnerAndLoser = fightWinner(player1Card, player2Card, roomName, game);
        CardUserDTO winnerCard = winnerAndLoser.get(0);
        CardUserDTO loserCard = winnerAndLoser.get(1);

        winnerCard.setMp(winnerCard.getMp());
        loserCard.setMp(loserCard.getMp());


        //Fetch player info and apply bet rewards
        UserDTO winnerPlayer = getUserData(winnerCard.getUserId());
        UserDTO loserPlayer = getUserData(loserCard.getUserId());

        winnerPlayer.setMoney(winnerPlayer.getMoney() + game.getBet());
        loserPlayer.setMoney(loserPlayer.getMoney() - game.getBet());


        //Apply All changes to microservices
        updateUserData(winnerPlayer);
        updateUserData(loserPlayer);

        //Update CardUser



        //Save in repo winner and fightLog changed
        gameRepository.save(game);


    }

    //Contains the fight logic and decides who's the winner
    private List<CardUserDTO> fightWinner(CardUserDTO card1, CardUserDTO card2, String roomName, Game game) {

        List<CardUserDTO> winnerAndLoser = new ArrayList<CardUserDTO>();

        // Function that can be extended if more conditions to winning are set
        //This part is purely implementation based
        //It is also where a more detailed fightLog can be generated
        //Here, as an example, a simple attack comparison will be used
        if (card1.getAttack() >= card2.getAttack()) {
            winnerAndLoser.add(card1); //Winner
            winnerAndLoser.add(card2); //Loser

            game.setFightLog("blabla 1");
            game.setWinner(1);
            return winnerAndLoser;
        } else {
            winnerAndLoser.add(card2); //Winner
            winnerAndLoser.add(card1); //Loser

            game.setFightLog("blabla 2");
            game.setWinner(2);
            return winnerAndLoser;
        }


    }




    // --------- COMMUNICATION FUNCTIONS------------

    private void updateCardUser(CardUserDTO cardUserDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        // Data attached to the request.
        HttpEntity<CardUserDTO> requestBody = new HttpEntity<>(cardUserDTO, headers);

        // Send request with PUT method.
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(URL_UPDATE_CARD_USER, HttpMethod.PUT, requestBody, Void.class);

    }

    private void updateUserData(UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        // Data attached to the request.
        HttpEntity<UserDTO> requestBody = new HttpEntity<>(userDTO, headers);

        // Send request with PUT method.
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(URL_UPDATE_USER_MONEY, HttpMethod.PUT, requestBody, Void.class);

    }

    private CardUserDTO getCardUserData(Integer cardId) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();


        String URL = URL_GET_CARD_USER + cardId;
        HttpEntity<CardUserDTO> carduserDTO = new HttpEntity<CardUserDTO>(headers);
        // Send request with GET method, and Headers.
        ResponseEntity<CardUserDTO> cardUser_response = restTemplate.exchange(URL, //
                HttpMethod.GET, carduserDTO, CardUserDTO.class);

        return cardUser_response.getBody();
    }

    private UserDTO getUserData(Integer userId) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();


        String URL = URL_GET_USER_DATA + userId;
        HttpEntity<UserDTO> userDTO = new HttpEntity<UserDTO>(headers);
        // Send request with GET method, and Headers.
        ResponseEntity<UserDTO> user_response = restTemplate.exchange(URL, //
                HttpMethod.GET, userDTO, UserDTO.class);

        return user_response.getBody();
    }

    private RoomDTO getRoomOfPlayer(Integer playerId) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();


        String URL = URL_ROOM_OF_USER + "/" + playerId;
        HttpEntity<RoomDTO> roomDTO = new HttpEntity<RoomDTO>(headers);
        // Send request with GET method, and Headers.
        ResponseEntity<RoomDTO> room_response = restTemplate.exchange(URL, //
                HttpMethod.GET, roomDTO, RoomDTO.class);


        return room_response.getBody();
    }


}
