package com.devbook.service;

import com.devbook.model.Role;
import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

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

    private boolean isEmailTaken(String email){
        if(userRepository.findByEmail(email) != null){
            return true;
        }
        return false;
    }

    public boolean register(String email, String password, String firstName, String lastName) {
        if(!isEmailTaken(email)) {
            User user = new User();
            user.setPassword(password);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.getRoleSet().add(Role.ROLE_USER);
            userRepository.save(user);
            return true;
        }
        else {
            return false;
        }

    }
}
