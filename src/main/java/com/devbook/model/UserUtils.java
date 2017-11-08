package com.devbook.model;

import java.util.ArrayList;

public class UserUtils {

    public static void checkIfUserFriendListIsNull(User user){
        if(user.getFriendsList() == null){
            user.setFriendsList(new ArrayList<>());
        }
    }

    public static void checkIfUserFriendRequestsListIsNull(User user){
        if(user.getFriendRequestsList() == null){
            System.out.println("ustawiam liste na nie nullowa");
            user.setFriendRequestsList(new ArrayList<>());
        }
    }
}
