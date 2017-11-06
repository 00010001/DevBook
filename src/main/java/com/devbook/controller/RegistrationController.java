package com.devbook.controller;

import com.devbook.model.Role;
import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import com.devbook.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(!registrationService.isEmailTaken(email)){
            User user = new User();
            user.setPassword(password);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.getRoleSet().add(Role.ROLE_USER);
            userRepository.save(user);
        }
        return new RedirectView("/userhome");

    }

}
