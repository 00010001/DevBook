package com.devbook.controller;


import com.devbook.service.AddToFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddToFriendsController {

    private AddToFriendsService addToFriendsService;

    @Autowired
    public AddToFriendsController(AddToFriendsService addToFriendsService) {
        this.addToFriendsService = addToFriendsService;
    }

    //TODO fix redirect

    @RequestMapping(value = "/addToFriends", method = RequestMethod.POST)
    public String addToFriend(
            @RequestParam("originUserId") String originUserId,
            @RequestParam("targetUserId") String targetUserId) {

            addToFriendsService.sendFriendRequest(originUserId,targetUserId);
        return ("/userhome");
    }

    @RequestMapping(value = "/acceptFriendRequest", method = RequestMethod.POST)
    public String acceptFriendRequest(
            @RequestParam("friendRequestId") String friendRequestId) {

        addToFriendsService.acceptFriendRequest(friendRequestId);
        return ("/userhome");
    }
}
