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

    public void sendFriendRequest(String originUserId, String targetUserId) {

        FriendRequest friendRequest = new FriendRequest(originUserId, targetUserId);
        User targetUser = userRepository.findBy_id(targetUserId);
        User originUser = userRepository.findBy_id(originUserId);
        friendRequest.setOriginUserProfileImageUrl(originUser.getProfileImageUrl());
        friendRequest.setOriginUserFirstName(originUser.getFirstName());
        friendRequest.setOriginUserLastName(originUser.getLastName());


        targetUser.getFriendRequestsList().add(friendRequest);
        userRepository.save(targetUser);
        friendRequestRepository.save(friendRequest);

    }

    public void acceptFriendRequest(String originUserId, String targetUserId) {

        FriendRequest friendRequest = friendRequestRepository
                .findByOriginUserIdAndTargetUserId(originUserId, targetUserId);

        System.out.println(friendRequest);

        User targetUser = userRepository.findBy_id(friendRequest.getTargetUserId());
        User originUser = userRepository.findBy_id(friendRequest.getOriginUserId());

        originUser.getFriendsList().add(new Friend(targetUser.get_id()));
        userRepository.save(originUser);
        targetUser.getFriendsList().add(new Friend(originUser.get_id()));
        userRepository.save(targetUser);
        friendRequestRepository.delete(friendRequest);

    }

    public List<FriendRequest> getFriendRequestList() {
        return userRepository.findBy_id(userService.getCurrentlyLoggedUser().get_id()).getFriendRequestsList();
    }
}
