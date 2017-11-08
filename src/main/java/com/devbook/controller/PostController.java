package com.devbook.controller;

import com.devbook.model.Post;
import com.devbook.model.User;
import com.devbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by RENT on 2017-11-08.
 */
@RestController
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @RequestMapping(value = "/api/post", method = RequestMethod.POST)
    public ModelAndView searchUsers(@RequestParam("exampleFormControlTextarea1") String postBody,
                                    @RequestParam("UserId") String userId,
                                    Model model){

        List<Post> postList = postService.addPost(postBody, userId);

        model.addAttribute("postList", postList);

        return new ModelAndView("userhome");
    }



}
