package com.devbook.controller;

import com.devbook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Krzysztof on 2017-11-12.
 */
@RestController
public class CommentController {


    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/user/addcomment")
    public RedirectView searchUsers(@RequestParam("commentBody") String commentBody,
                                    @RequestParam("postUserId") String postUserId,
                                    @RequestParam("postId") String postId,
                                    @RequestParam("commentUserId") String commentUserId) {

        commentService.addComment(commentBody, postUserId, postId, commentUserId);
        return new RedirectView("/user");
    }
}
