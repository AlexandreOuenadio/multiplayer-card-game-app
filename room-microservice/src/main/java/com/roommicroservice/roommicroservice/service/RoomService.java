package com.roommicroservice.roommicroservice.service;

import com.DTOLibrary.RoomDTO;
import com.roommicroservice.roommicroservice.model.Room;
import com.roommicroservice.roommicroservice.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    //Adds a new room into the database
    public RoomDTO createRoom (RoomDTO roomDTO){
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);

        Room roomCreated = roomRepository.save(room);

        RoomDTO roomDTOToSend = new RoomDTO();

        BeanUtils.copyProperties(roomCreated, roomDTOToSend);

        return roomDTOToSend;
    }

    //Make a player join a room by setting the room's 2nd player as his id
    public boolean joinRoom (String roomName, Integer userId){
        Room room = roomRepository.findByName(roomName);
        room.setPlayer2Id(userId);
        roomRepository.save(room);
        return true;
    }

    //Returns all the rooms found in database
    public Iterable<Room> getAllRooms (){
        return roomRepository.findAll();
    }

    //Checks if a given room is full (2 players in it)
    public boolean isRoomFull (String roomName){
        Room room = roomRepository.findByName(roomName);
        return (room.getPlayer2Id() != null) && (room.getPlayer1Id() != null);
    }


    //Returns the room of a given player (only its name and bet info)
    public RoomDTO getRoomOfPlayer (Integer playerId){
        RoomDTO roomDTO = new RoomDTO();
        Room roomP1 = roomRepository.findByPlayer1Id(playerId);
        Room roomP2 = roomRepository.findByPlayer2Id(playerId);
        if ( roomP1 != null){
            BeanUtils.copyProperties(roomP1, roomDTO);
            return roomDTO;
        } else if (roomP2 != null) {
            BeanUtils.copyProperties(roomP2, roomDTO);
            return roomDTO;
        }
        return null;
    }

}
