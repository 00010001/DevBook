package com.devbook.controller;

import com.devbook.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView registerUser(HttpServletRequest request,
                             @RequestParam("inputFirstName6") String firstName,
                             @RequestParam("inputLastName6") String lastName,
                             @RequestParam("inputEmail6") String email,
                             @RequestParam("inputPassword6") String password){

        if(registrationService.register(email,password,firstName,lastName)){
            registrationService.autoLogin(email,password,request);
            return new RedirectView("/user");
        }
        else
            return new RedirectView("/login?emailtaken");

    }

}
