package com.gamemicroservice.gamemicroservice.Repository;

import com.gamemicroservice.gamemicroservice.Model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {
    public Game findByRoomName(String roomName);
}