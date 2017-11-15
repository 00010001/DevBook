package com.devbook.service;

import com.devbook.model.Post;
import com.devbook.model.User;
import com.devbook.repository.PostRepository;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Post> addPost(String postBody, String userid) {

        Post post = new Post(postBody);
        postRepository.save(post);
        User user = userRepository.findBy_id(userid);
        List<Post> postList = user.getPostList();
        postList.add(post);
        Collections.sort(postList);
        Collections.reverse(postList);
        userRepository.save(user);

        return user.getPostList();
    }
}
