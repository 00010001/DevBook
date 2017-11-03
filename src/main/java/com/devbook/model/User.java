package com.devbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Id;
import java.util.List;


@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Email
    @NonNull
    private String email;

    private UserDetails userDetails;

    private List<Post> posts;


    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
