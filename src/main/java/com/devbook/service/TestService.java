package com.devbook.service;

import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService {

    @Autowired
    private UserRepository repository;

    public void run(){
        repository.deleteAll();

        // save a couple of customers
        repository.save(new User("Alice", "Smith","AliceSmith@gmail.com"));
        repository.save(new User("Bob", "Smith","BobSmith@gmail.com"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User user : repository.findAll()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("User found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));
    }
}
