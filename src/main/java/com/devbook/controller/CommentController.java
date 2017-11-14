package com.devbook.controller;

import com.devbook.model.User;
import com.devbook.repository.PostRepository;
import com.devbook.service.CommentService;
import com.devbook.service.SearchUserService;
import com.devbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Krzysztof on 2017-11-12.
 */
@RestController
public class CommentController {

    CommentService commentService;
    PostRepository postRepository;
    SearchUserService searchUserService;
    UserService userService;

    @Autowired
    public CommentController(CommentService commentService, PostRepository postRepository) {
        this.commentService = commentService;
        this.postRepository = postRepository;
    }

    @PostMapping("/user/addcomment")
    public RedirectView addComment(@RequestParam("commentBody") String commentBody,
                                   @RequestParam("postUserId") String postUserId,
                                   @RequestParam("postId") String postId,
                                   @RequestParam("commentUserId") String commentUserId) {

        commentService.addComment(commentBody, postUserId, postId, commentUserId);
        return new RedirectView(postUserId);
    }
}
