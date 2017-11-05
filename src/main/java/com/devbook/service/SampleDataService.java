package com.devbook.service;

import com.devbook.model.*;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SampleDataService {

    private UserRepository userRepository;

    @Autowired
    public SampleDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addSampleDataToDatabase(boolean deleteEverythingFromDatabase){

        if(deleteEverythingFromDatabase){
            userRepository.deleteAll();
        }

        addSampleUsers();

    }

    private void addSampleUsers() {
        User user1 = generateUser1();
        User user2 = generateUser2();

        userRepository.save(user1);
        userRepository.save(user2);
    }




    private User generateUser1(){

        User user = new User();

        user.setFirstName("John");
        user.setLastName("Snow");
        user.setEmail("johnsnow@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);


        List<Post> postList = new ArrayList<>();
        postList.add(new Post(new Date(), "This is post 1"));
        postList.add(new Post(new Date(), "This is post 2"));
        user.setPosts(postList);

        UserSkills userSkills = new UserSkills();
        List<String> skills = new ArrayList<>();
        skills.add("Html");
        skills.add("JavaScript");
        skills.add("Css");
        userSkills.setSkills(skills);
        user.setUserSkills(userSkills);

        return user;
    }

    private User generateUser2(){
        User user = new User();

        user.setFirstName("Aria");
        user.setLastName("Stark");
        user.setEmail("ariastark@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_ADMIN);


        List<Post> postList = new ArrayList<>();
        postList.add(new Post(new Date(), "This is Aria post 1"));
        postList.add(new Post(new Date(), "This is Aria post 2"));
        user.setPosts(postList);

        UserSkills userSkills = new UserSkills();
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Maven");
        skills.add("Spring");
        userSkills.setSkills(skills);
        user.setUserSkills(userSkills);

        return user;
    }
}
