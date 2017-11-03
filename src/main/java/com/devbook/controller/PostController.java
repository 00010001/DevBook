package com.devbook.controller;

import com.devbook.model.Post;
import com.devbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value="/post")
    public List<Post> getPost(){
        return postService.getAll();
    }
}
