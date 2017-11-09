package com.devbook.service;

import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap;
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
                if ( word.equalsIgnoreCase(user.getFirstName())||word.equalsIgnoreCase(user.getLastName().toLowerCase())){
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return searchUserList;
    }

    public User findById(String id) {
        User user = userRepository.findBy_id(id);
        System.out.println(user.getPostList());
        return user;
    }
}
