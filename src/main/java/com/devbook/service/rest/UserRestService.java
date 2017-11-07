package com.devbook.service.rest;

import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRestService {

    private UserRepository userRepository;
    private static ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    public UserRestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getAll(){
        List<User> userList = userRepository.findAll();
        return mapObject(userList);
    }

    public String findAllByFirstNameIgnoreCase(String firstName){
        List<User> userList = userRepository.findAllByFirstNameIgnoreCase(firstName);
        return mapObject(userList);
    }

    public String findAllByLastNameIgnoreCase(String lastName) {
        List<User> userList = userRepository.findAllByLastNameIgnoreCase(lastName);
        return mapObject(userList);
    }

    public String findAllByEmailIgnoreCase(String email) {
        List<User> userList = userRepository.findAllByEmailIgnoreCase(email);
        return mapObject(userList);
    }

    private String mapObject(List<User> userList){
        if(isUserListEmpty(userList)){
            return "no results";
        } else {
            String jsonString = "";
            try {
                jsonString = objectMapper.writeValueAsString(userList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return jsonString;
        }
    }

    private boolean isUserListEmpty(List<User> users){
        return users.size() == 0;
    }
}
