package com.devbook.model;

import com.devbook.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Data
public class FriendRequest {

    @Id
    private String _id;
    private String originUserId;
    private String targetUserId;
    private String originUserProfileImageUrl;
    private String dateString;

    private boolean accepted;
    private LocalDateTime timestamp;

    public FriendRequest(String originUserId, String targetUserId) {
        this.originUserId = originUserId;
        this.targetUserId = targetUserId;
        accepted = false;
        this.timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateString = timestamp.format(formatter);
    }
}
