package com.devbook.service;

import com.devbook.model.Friend;
import com.devbook.model.FriendRequest;
import com.devbook.model.User;
import com.devbook.repository.FriendRequestRepository;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddToFriendsService {

    private UserRepository userRepository;
    private FriendRequestRepository friendRequestRepository;
    private UserService userService;

    @Autowired
    public AddToFriendsService(UserRepository userRepository, FriendRequestRepository friendRequestRepository, UserService userService) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.userService = userService;
    }

    public void sendFriendRequest(String originUserId, String targetUserId){

        FriendRequest friendRequest = new FriendRequest(originUserId,targetUserId);
        User targetUser = userRepository.findBy_id(targetUserId);
        friendRequest.setOriginUserProfileImageUrl(userRepository.findBy_id(originUserId).getProfileImageUrl());
        targetUser.getFriendRequestsList().add(friendRequest);
        userRepository.save(targetUser);
        friendRequestRepository.save(friendRequest);
    }

    public void acceptFriendRequest(String friendRequestId){
        FriendRequest friendRequest = friendRequestRepository.findBy_id(friendRequestId);

        User targetUser = userRepository.findBy_id(friendRequest.getTargetUserId());
        User originUser = userRepository.findBy_id(friendRequest.getOriginUserId());

        originUser.getFriendsList().add(new Friend(targetUser.get_id()));
        targetUser.getFriendsList().add(new Friend(originUser.get_id()));

        friendRequest.setAccepted(true);

    }

    public List<FriendRequest> getFriendRequestList() {
        return userService.getCurrentlyLoggedUser().getFriendRequestsList();
    }
}
