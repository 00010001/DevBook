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
import java.util.Optional;

@Service
public class CommentService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> addComment(String commentBody, String postUserId, String postId, String commentUserId) {
        User postUser = userRepository.findBy_id(postUserId);
        User commentUser = userRepository.findBy_id(commentUserId);
        Comment comment = new Comment(commentBody,commentUser.getFirstName(),commentUser.getLastName(),postUserId, commentUserId);
        Post post = postRepository.findBy_id(postId);
        List<Comment> commentList = post.getCommentList();
        commentList.add(comment);
        Collections.sort(commentList);
        Collections.reverse(commentList);
        Optional <Post> temporaryPost = postUser.getPostList().stream().filter(p->p.get_id().equals(postId)).findFirst();
        temporaryPost.get().getCommentList().add(comment);
        postRepository.save(post);
        userRepository.save(postUser);

        return post.getCommentList();
    }

}
