package com.devbook.service;

import com.devbook.model.Comment;
import com.devbook.model.Post;
import com.devbook.model.User;
import com.devbook.repository.PostRepository;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> addComment(String commentBody, String userId, String postId) {

        Comment comment = new Comment(commentBody);
        User user = userRepository.findBy_id(userId);
        Post post = postRepository.findBy_id(postId);
        List<Comment> commentList = post.getCommentList();
        commentList.add(comment);
        Collections.sort(commentList);
        Collections.reverse(commentList);
        userRepository.save(user);

        return post.getCommentList();
    }

}
