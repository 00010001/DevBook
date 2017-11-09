package com.devbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Transactional
public class User implements org.springframework.security.core.userdetails.UserDetails{

   //TODO move user security details to another class

   @Id
   private String _id;
   private String firstName;
   private String lastName;
   private String email;
   private String summary;
   private String currentStatus;
   private String userImageUrl;

   private List<Skill> skills = new ArrayList<>();
   private List<Post> postList = new ArrayList<>();
   private List<Message> messages = new ArrayList<>();

   private List<Friend> friendsList = new ArrayList<>();
   private List<FriendRequest> friendRequestsList = new ArrayList<>();

   private String password;
   private Set<Role> roleSet = new HashSet<>();

   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roleSet.stream().map(p-> new SimpleGrantedAuthority(p.getRoleName())).collect(Collectors.toList());
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
