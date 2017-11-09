package com.devbook.service;

import com.devbook.model.Role;
import com.devbook.model.User;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class RegistrationService {

    private UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public RegistrationService(UserRepository userRepository, @Qualifier("authenticationManager") AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    private boolean isEmailTaken(String email){
        return userRepository.findByEmail(email) != null;
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

    public void autoLogin(String username, String password, HttpServletRequest request){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));

        authenticateUser(token);
        setDetailsInSession(username, request, token);

    }

    private void authenticateUser(UsernamePasswordAuthenticationToken token){
        Authentication authenticatedUser = authenticationManager.authenticate(token);// authenticates the token
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

    private void setDetailsInSession(String username, HttpServletRequest request, UsernamePasswordAuthenticationToken token){
        request.getSession();
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());// creates context for that session.
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("authorities", token.getAuthorities());
    }
}
