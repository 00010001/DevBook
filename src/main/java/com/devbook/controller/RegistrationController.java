package com.devbook.controller;

import com.devbook.model.Role;
import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import com.devbook.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RegistrationController {

    private RegistrationService registrationService;
    private UserRepository userRepository;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserRepository userRepository) {
        this.registrationService = registrationService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView registerUser(@RequestParam("inputFirstName6") String firstName,
                             @RequestParam("inputLastName6") String lastName,
                             @RequestParam("inputEmail6") String email,
                             @RequestParam("inputPassword6") String password){

        if(registrationService.register(email,password,firstName,lastName)){
            return new RedirectView("/");
        }
        else
            return new RedirectView("/login?emailtaken");

    }

}
