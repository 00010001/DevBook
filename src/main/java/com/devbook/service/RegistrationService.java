package com.devbook.service;

import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by RENT on 2017-11-06.
 */
@Service
public class RegistrationService {

    private UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isEmailTaken(String email){
        if(userRepository.findByEmail(email) != null){
            return true;
        }
        return false;
    }



}
