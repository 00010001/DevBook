package com.devbook.repository;

import com.devbook.model.Post;
import com.devbook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Krzysztof on 2017-11-12.
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    Post findBy_id(String id);

}
