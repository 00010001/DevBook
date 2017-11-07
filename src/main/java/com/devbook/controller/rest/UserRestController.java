package com.devbook.controller.rest;

import com.devbook.service.rest.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private UserRestService userRestService;

    @Autowired
    public UserRestController(UserRestService userRestService) {
        this.userRestService = userRestService;
    }

    //TODO interceptor sprawdza czy wynik zawiera cos

    @GetMapping(value = "/rest/user", produces = "application/json")
    public String getUser(){
        return userRestService.getAll();
    }

    @GetMapping(value="/rest/user/firstname/{firstName}", produces = "application/json")
    public @ResponseBody String findAllByFirstName(@PathVariable String firstName) {
       return userRestService.findAllByFirstNameIgnoreCase(firstName);
    }

    @GetMapping(value="/rest/user/lastname/{lastName}", produces = "application/json")
    public @ResponseBody String findAllByLastName(@PathVariable String lastName) {
        return userRestService.findAllByLastNameIgnoreCase(lastName);
    }

    @GetMapping(value="/rest/user/email/{email}", produces = "application/json")
    public @ResponseBody String findAllByEmailIgnoreCase(@PathVariable String email) {
        return userRestService.findAllByEmailIgnoreCase(email);
    }

}
