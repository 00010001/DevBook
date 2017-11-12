package com.devbook.controller;

import com.devbook.service.CommentService;
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

    @PostMapping("/user/addcomment")
    public RedirectView searchUsers(@RequestParam("commentBody") String commentBody,
                                    @RequestParam("userId") String userId,
                                    @RequestParam("postId") String postId) {

        commentService.addComment(commentBody,userId,postId);
        return new RedirectView(userId);
    }
}
