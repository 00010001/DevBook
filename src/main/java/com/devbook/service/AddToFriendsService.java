package com.devbook.service;

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
        friendRequestRepository.save(friendRequest);

        targetUser.getFriendRequestsList().add(friendRequest);

        userRepository.save(targetUser);
        friendRequestRepository.save(friendRequest);
    }

    public void acceptFriendRequest(String originUserId, String targetUserId) {

        FriendRequest friendRequest = friendRequestRepository
                .findByOriginUserIdAndTargetUserId(originUserId, targetUserId);
        removeFriendRequestFromTargetUserFriendRequestList(targetUserId,friendRequest);
        makeUsersFriends(friendRequest.getTargetUserId(),friendRequest.getOriginUserId());
        removeFriendRequestFromDB(friendRequest);

    }

    private void makeUsersFriends(String firstUserId, String secondUserId){
        User user1 = userRepository.findBy_id(firstUserId);
        User user2 = userRepository.findBy_id(secondUserId);
        user2.getFriendsList().add(user1.get_id());
        userRepository.save(user2);
        user1.getFriendsList().add(user2.get_id());
        userRepository.save(user1);
    }

    private void removeFriendRequestFromTargetUserFriendRequestList(String targetUserId, FriendRequest friendRequest){

        User targetUser = userRepository.findBy_id(targetUserId);
        targetUser.getFriendRequestsList().remove(friendRequest);
        userRepository.save(targetUser);

    }

    private void removeFriendRequestFromDB(FriendRequest friendRequest){
        friendRequestRepository.delete(friendRequest);
    }

    public List<FriendRequest> getFriendRequestList() {
        return userRepository.findBy_id(userService.getCurrentlyLoggedUser().get_id()).getFriendRequestsList();
    }
}
