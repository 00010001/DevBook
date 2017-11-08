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
        user.setSummary("Currently enrolled in Java Coding Bootcamp \n" +
                "at Software Development Academy in Katowice\n" +
                "which I will finish in November 2017. \n" +
                "Expierienced with web development process. \n" +
                "Able to work well independently or as part \n" +
                "of a professional development team.");


        List<Post> postList = new ArrayList<>();
//        postList.add(new Post(new Date(), "This is post 1"));
//        postList.add(new Post(new Date(), "This is post 2"));
        user.setPosts(postList);


        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));
        user.setSkills(userSkills);

        List<FriendRequest> friendRequestList = new ArrayList<>();
        friendRequestList.add(new FriendRequest("a","b"));
        friendRequestList.add(new FriendRequest("a2","b2"));
        user.setFriendRequestsList(friendRequestList);

        return user;
    }

    private User generateUser2(){
        User user = new User();

        user.setFirstName("Aria");
        user.setLastName("Stark");
        user.setEmail("ariastark@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_ADMIN);
        user.setSummary("Innovator of next-generation solutions, systems and applications giving companies a competitive edge and producing outstanding results for customers.");

        List<Post> postList = new ArrayList<>();
//        postList.add(new Post(new Date(), "This is Aria post 1"));
//        postList.add(new Post(new Date(), "This is Aria post 2"));
        user.setPosts(postList);

        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));
        user.setSkills(userSkills);

        return user;
    }
}
