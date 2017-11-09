package com.devbook.service;

import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentlyLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        return userRepository.findByEmail(currentPrincipalEmail);
    }

    public void updateCurrentUserProfile(String firstName, String lastName, String summary, String currentstatus, String headerImageUrl, String profileImageUrl) {
        User user = this.getCurrentlyLoggedUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSummary(summary);
        user.setCurrentStatus(currentstatus);
        user.setHeaderImageUrl(headerImageUrl);
        user.setProfileImageUrl(profileImageUrl);
        userRepository.save(user);
    }
}
