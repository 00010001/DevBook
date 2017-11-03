package com.devbook.controller;

import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by RENT on 2017-11-03.
 */
@RestController
public class AuthenticationController {

    @GetMapping("/getAuthorities")
    public List<String> getAllRoles(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
