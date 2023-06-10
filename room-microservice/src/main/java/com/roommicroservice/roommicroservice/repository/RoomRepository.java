package com.roommicroservice.roommicroservice.repository;

import com.roommicroservice.roommicroservice.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    public Room findByName(String roomName);

    public Room findByPlayer1Id(Integer player1Id);

    public Room findByPlayer2Id(Integer player2Id);

}