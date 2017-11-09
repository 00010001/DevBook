package com.devbook.controller;

import com.devbook.model.FriendRequest;
import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import com.devbook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    private SearchUserService searchUserService;
    private AddToFriendsService addToFriendsService;
    private UserService userService;
    private PostService postService;


    //TODO fix searching case sensivity
    //TODO w restowym api jest jeden search controller i jest robione z parametrami metody


    @Autowired
    public UserController(SearchUserService searchUserService, AddToFriendsService addToFriendsService, UserService userService, PostService postService) {
        this.searchUserService = searchUserService;
        this.addToFriendsService = addToFriendsService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/user")
    public RedirectView homeWhenViewingLoggedUser() {
        String userId = userService.getCurrentlyLoggedUser().get_id();
        return new RedirectView("user/" + userId);
    }

    @GetMapping("/user/{id}")
    public ModelAndView homeWhenViewingOtherPersonProfile(@PathVariable("id") String userId, Model model) {
        User user = searchUserService.findById(userId);
        model.addAttribute("postList", user.getPostList());
        model.addAttribute("user", user);
        return new ModelAndView("home");
    }

    @GetMapping("/user/friendrequests")
    public ModelAndView userFriendRequests(Model model) {

        List<FriendRequest> friendRequestList = addToFriendsService.getFriendRequestList();
        model.addAttribute("friendRequestsList", friendRequestList);
        return new ModelAndView("userfriendrequests");
    }

    @PostMapping("/user/addpost")
    public RedirectView searchUsers(@RequestParam("postBody") String postBody,
                                    @RequestParam("userId") String userId) {

        postService.addPost(postBody, userId);
        return new RedirectView(userId);
    }

    @PostMapping("user/addToFriends")
    public RedirectView addToFriend(
            @RequestParam("originUserId") String originUserId,
            @RequestParam("targetUserId") String targetUserId) {

        addToFriendsService.sendFriendRequest(originUserId, targetUserId);
        return new RedirectView("/user");
    }

    @PostMapping("user/acceptFriendRequest")
    public RedirectView acceptFriendRequest(
            @RequestParam("friendRequestId") String friendRequestId) {

        addToFriendsService.acceptFriendRequest(friendRequestId);
        return new RedirectView("/user");
    }

    @GetMapping("/user/edit")
    public ModelAndView usereditor(Model model) {
        User user = userService.getCurrentlyLoggedUser();
        model.addAttribute("user", user);
        return new ModelAndView("usereditprofile");
    }

    @PostMapping("user/updateprofile")
    public RedirectView updateprofile(@RequestParam("firstname") String firstName,
                                      @RequestParam("lastname") String lastName,
                                      @RequestParam("summary") String summary,
                                      @RequestParam("currentstatus") String currentStatus) {


        userService.updateCurrentUserProfile(firstName,lastName,summary,currentStatus);
        return new RedirectView("/user");
    }


}
