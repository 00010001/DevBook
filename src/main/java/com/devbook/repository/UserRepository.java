package com.devbook.repository;

import java.util.List;

import com.devbook.model.User;
import com.devbook.model.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {


    User findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    User addNewUser(String firstName, String lastName, String email);
    UserDetails getUserDetails(String id);
    User findByUsername(String email);




}