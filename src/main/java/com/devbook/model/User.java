package com.devbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;


@Data
@NoArgsConstructor
public class User {

   @Id
   private String id;

   private String firstName;

   private String lastName;

   private String email;


   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }
}
