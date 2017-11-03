package com.devbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class User implements org.springframework.security.core.userdetails.UserDetails{

   @Id
   private String _id;
   private String firstName;
   private String lastName;
   private String email;
  
   private UserDetails userDetails;
   private List<Post> posts;
   private List<Message> messages;

   private String password;
   private Set<Role> roleSet;

   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }


   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roleSet.stream().map(p-> new SimpleGrantedAuthority("ROLE_" + p.getName())).collect(Collectors.toList());
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }


}
