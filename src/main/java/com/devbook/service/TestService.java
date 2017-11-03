package com.devbook.service;

import com.devbook.model.Post;
import com.devbook.model.User;
import com.devbook.model.UserDetails;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TestService {

    @Autowired
    private UserRepository repository;

    public void run(){
        repository.deleteAll();

        // save a couple of customers

        User user = new User();
        user.setFirstName("jasiu");
        user.setLastName("kowalski");
        List<Post> postList = new ArrayList<>();
        postList.add(new Post(new Date(),"wiadomosc"));
        postList.add(new Post(new Date(),"wiadomosc2"));

        user.setPosts(postList);

        UserDetails userDetails = new UserDetails();
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Css");
        userDetails.setSkills(skills);

        user.setUserDetails(userDetails);
        repository.save(user);
        repository.save(new User("Alice", "Smith","AliceSmith@gmail.com"));
        repository.save(new User("Bob", "Smith","BobSmith@gmail.com"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User userx : repository.findAll()) {
            System.out.println(userx);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("User found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));
    }
}
