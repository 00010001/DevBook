package com.devbook.controller;

import com.devbook.model.FriendRequest;
import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import com.devbook.service.AddToFriendsService;
import com.devbook.service.SearchUserService;
import com.devbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    private SearchUserService searchUserService;
    private AddToFriendsService addToFriendsService;

    //TODO fix searching case sensivity
    //TODO w restowym api jest jeden search controller i jest robione z parametrami metody

    @Autowired
    public UserController(SearchUserService searchUserService, AddToFriendsService addToFriendsService) {
        this.searchUserService = searchUserService;
        this.addToFriendsService = addToFriendsService;
    }

    @RequestMapping(value = "api/user/", method = RequestMethod.GET)
    public ModelAndView searchUsers(@RequestParam("searchQuery") String searchQuery,
                                    Model model) {

        List<User> userList = searchUserService.searchQueryFromUsers(searchQuery);
        model.addAttribute("userList", userList);

        return new ModelAndView("search");
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public ModelAndView searchOneUser(@PathVariable("id") String id,
                                      Model model) {

        User user = searchUserService.findById(id);
        model.addAttribute("user", user);

        return new ModelAndView("userhomebyid");
    }


    @RequestMapping(value = "/userfriendrequests", method = RequestMethod.GET)
    public ModelAndView userFriendRequests(Model model) {


        List<FriendRequest> friendRequestList = addToFriendsService.getFriendRequestList();
        System.out.println(friendRequestList);
        model.addAttribute("friendRequestsList", friendRequestList);


        return new ModelAndView("userfriendrequests");
    }

}
