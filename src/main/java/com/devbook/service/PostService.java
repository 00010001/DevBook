package com.devbook.service;

import com.devbook.model.Post;
import com.devbook.model.User;
import com.devbook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    Post post = new Post();

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public List<Post> getUserPost(User user){
        return postRepository.findByUser(user);
    }

    public void addPost(){

    }
}
