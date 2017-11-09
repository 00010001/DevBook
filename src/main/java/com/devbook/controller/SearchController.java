package com.devbook.controller;


import com.devbook.service.SearchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    private SearchUserService searchUserService;

    @Autowired
    public SearchController(SearchUserService searchUserService) {
        this.searchUserService = searchUserService;
    }

    @GetMapping(value = "search")
    public ModelAndView search(@RequestParam("searchQuery") String searchQuery, Model model) {

        model.addAttribute("userList", searchUserService.searchQueryFromUsers(searchQuery));
        return new ModelAndView("search");

    }
}
