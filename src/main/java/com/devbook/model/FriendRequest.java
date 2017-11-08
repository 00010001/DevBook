package com.devbook.model;

import lombok.Data;

import javax.persistence.Id;


@Data
public class FriendRequest {

    @Id
    private String _id;
    private String originUserId;
    private String targetUserId;
    private boolean accepted;

    public FriendRequest(String originUserId, String targetUserId) {
        this.originUserId = originUserId;
        this.targetUserId = targetUserId;
        accepted = false;
    }
}
