package com.usermicroservice.repository;


import com.usermicroservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByName(String name);
    User findByNameAndPassword(String name, String password);
}
