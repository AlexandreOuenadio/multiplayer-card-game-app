package com.roommicroservice.roommicroservice.controlleur;


import com.DTOLibrary.RoomDTO;
import com.DTOLibrary.UserDTO;
import com.roommicroservice.roommicroservice.model.Room;
import com.roommicroservice.roommicroservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RoomController {

    @Autowired
    RoomService roomService;

    //Creates a room
    @RequestMapping(method = POST, value = "/create")
    public RoomDTO createRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.createRoom(roomDTO);

    }

    //Joining a room
    @RequestMapping(method = POST, value = "/join/{roomName}")
    public boolean joinRoom(@PathVariable String roomName, @CookieValue(value="id") Integer userId) {
        return roomService.joinRoom(roomName, userId);
    }

    //Gets all the existing rooms
    @RequestMapping(method = GET, value = "/getAllRooms")
    public Iterable<Room> getAllRooms() {
        return roomService.getAllRooms();

    }

    //Returns whether a given room is full (2 players in it) through a boolean
    @RequestMapping(method = GET, value = "/isRoomFull/{roomName}")
    public boolean isRoomFull(@PathVariable String roomName) {
        return roomService.isRoomFull(roomName);

    }

    //Returns the room of a given player (only the name and bet of it)
    @RequestMapping(method = GET, value = "/getRoomOfPlayer/{playerId}")
    public RoomDTO getRoomOfPlayer(@PathVariable Integer playerId) {
        return roomService.getRoomOfPlayer(playerId);

    }

}
