package com.devbook.service;

import com.devbook.model.Post;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by RENT on 2017-11-08.
 */
@Service
public class PostService {

    UserRepository userRepository;

    @Autowired
    public PostService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    public List<Post> addPost(String postBody) {
//
//
//    }
}
