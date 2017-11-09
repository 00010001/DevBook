package com.devbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class Post implements Comparable<Post>{

    private LocalDateTime localDateTime;
    private String body;
    private String dateString;

    public Post(String body) {
        this.localDateTime = LocalDateTime.now();
        this.body = body;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dateString = localDateTime.format(formatter); // "1986-04-08 12:30"
    }

    @Override
    public int compareTo(Post post) {
        return this.localDateTime.compareTo(post.getLocalDateTime());
    }
}
