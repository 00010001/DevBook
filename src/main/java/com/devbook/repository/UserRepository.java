package com.devbook.repository;

import com.devbook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    User findByEmail(String email);
    User findBy_id(String id);
    List<User> findBy_id(List<String> ids);

    List<User> findAllByFirstNameIgnoreCase(String firstName);
    List<User> findAllByLastNameIgnoreCase(String firstName);
    List<User> findAllByEmailIgnoreCase(String email);

}