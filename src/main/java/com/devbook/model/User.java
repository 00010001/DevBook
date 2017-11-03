package com.devbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.List;


@Data
@NoArgsConstructor
public class User {

   @Id
   private String id;

   private String firstName;

   private String lastName;

   private String email;

   private UserDetails userDetails;

   private List<Post> posts;

   private List<Message> messages;


   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public UserDetails getUserDetails() {
      return userDetails;
   }

   public void setUserDetails(UserDetails userDetails) {
      this.userDetails = userDetails;
   }

   public List<Post> getPosts() {
      return posts;
   }

   public void setPosts(List<Post> posts) {
      this.posts = posts;
   }
}
