package com.devbook.repository;

import com.devbook.model.Post;
import com.devbook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    public List<Post> findAll();
    public List<Post> findByUser(User user);
}
