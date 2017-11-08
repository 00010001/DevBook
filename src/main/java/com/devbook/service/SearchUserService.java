package com.devbook.service;

import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchUserService {

    private UserRepository userRepository;

    @Autowired
    public SearchUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> searchQueryFromUsers(String searchQuery) {
        List<User> searchUserList;
        String[] words = searchQuery.split("\\s");
        searchUserList = userRepository.findAll().stream().filter(user->{
            for (String word : words) {
                if ( word.equals(user.getFirstName())||word.equals(user.getLastName())){
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return searchUserList;
    }

    public User findById(String id) {
        return userRepository.findBy_id(id);
    }
}
