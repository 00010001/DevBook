package com.devbook.controller;

import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import com.devbook.service.SearchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by RENT on 2017-11-07.
 */
@RestController
public class UserController {

    private SearchUserService searchUserService;


    @Autowired
    public UserController(SearchUserService searchUserService) {
        this.searchUserService = searchUserService;
    }

    @RequestMapping(value = "api/user/", method = RequestMethod.GET)
    public ModelAndView searchUsers(@RequestParam("searchQuery") String searchQuery,
                                    Model model){

        List<User> userList = searchUserService.searchQueryFromUsers(searchQuery);
        model.addAttribute("userList", userList);

        return new ModelAndView("search");
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public ModelAndView searchOneUser(@PathVariable("id")String id,
                                    Model model){

        User user = searchUserService.findById(id);
        model.addAttribute("user", user);

        return new ModelAndView("userhomebyid");
    }

}
