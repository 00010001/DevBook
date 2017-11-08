package com.devbook.service;

import com.devbook.model.Post;
import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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


    public List<Post> addPost(String postBody, String userid) {
        Post post = new Post();
        post.setBody(postBody);
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        post.setLocalDateTime(localtDateAndTime);
        User user = userRepository.findBy_id(userid);
//        checkIfPostListIsValid(user.getPosts());
        user.addPostToUserPostList(post);
        userRepository.save(user);

        return user.getPosts();
    }

    private List<Post> checkIfPostListIsValid(List<Post> postList){
        if (postList==null){
            postList = new ArrayList<>();
        }
        return postList;
    }
}
