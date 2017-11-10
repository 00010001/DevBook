package com.devbook.service;

import com.devbook.model.Levenshtein;
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
        searchUserList = userRepository.findAll().stream().filter(user -> {
            if (words.length<=1) {
                for (String word : words) {
                    if (Levenshtein.distance(word, user.getFirstName()) < 2 || Levenshtein.distance(word, user.getLastName()) < 2) {
                        return true;
                    }
                }
                return false;
            } else {
                for (int i = 0; i < words.length; i++) {
                    for (int j = 0; j < words.length; j++) {
                        if (Levenshtein.distance(words[i], user.getFirstName()) < 2 && Levenshtein.distance(words[j], user.getLastName()) < 2) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }).collect(Collectors.toList());
        return searchUserList;
    }

    public User findById(String id) {
        return userRepository.findBy_id(id);
    }
}
