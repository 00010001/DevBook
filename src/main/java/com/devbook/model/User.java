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
   private String _id;
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

}
