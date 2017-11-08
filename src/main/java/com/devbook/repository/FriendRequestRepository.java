package com.devbook.repository;

import com.devbook.model.FriendRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendRequestRepository extends MongoRepository<FriendRequest, String> {

    FriendRequest findBy_id(String id);
}
