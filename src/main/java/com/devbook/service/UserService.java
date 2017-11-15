package com.devbook.service;

import com.devbook.model.Skill;
import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
  
  public String getCurrentlyLoggedUserId() {
       return this.getCurrentlyLoggedUser().get_id();
    }

    public void updateCurrentUserProfile(String firstName, String lastName, String summary, String currentStatus, String headerImageUrl, String profileImageUrl) {
        User user = this.getCurrentlyLoggedUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSummary(summary);
        user.setCurrentStatus(currentStatus);
        user.setHeaderImageUrl(headerImageUrl);
        user.setProfileImageUrl(profileImageUrl);
        userRepository.save(user);
    }

    private List<User> getCurrentlyLoggedUserFriendList() {
        User currentlyLoggedUser = userRepository.findBy_id(this.getCurrentlyLoggedUser().get_id());
        return userRepository.findBy_id(currentlyLoggedUser.getFriendsList());
    }

    public List<String> getProfileImageUrlsListFromCurrentlyLoggedUserFriends(){
        List<String> friendsProfileImagesUrlsList = new ArrayList<>();

        for (User user : this.getCurrentlyLoggedUserFriendList()) {
            friendsProfileImagesUrlsList.add(user.getProfileImageUrl());
        }
        return friendsProfileImagesUrlsList;
    }

    public void addSkill(User user, Skill skill){
        user.getSkills().add(skill);
        saveUser(user);
    }
  
    public User getUserById(String id){
        return userRepository.findBy_id(id);
    }

    void saveUser(User user){
        userRepository.save(user);
    }
}
