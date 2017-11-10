package com.devbook.repository;

import com.devbook.model.FriendRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends MongoRepository<FriendRequest, String> {

    FriendRequest findByOriginUserIdAndTargetUserId(String originUserId, String targetUserId);
}
