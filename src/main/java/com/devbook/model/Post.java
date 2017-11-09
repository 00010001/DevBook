package com.devbook.model;

import com.ocpsoft.pretty.time.PrettyTime;
import lombok.Data;
import org.thymeleaf.util.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class Post implements Comparable<Post>{

    private Date date;
    private String body;
    private String dateString;

    static PrettyTime p = new PrettyTime();

    public Post(String body) {
        this.date = new Date();
        this.body = body;
        this.dateString = date.toString();
    }

    @Override
    public int compareTo(Post post) {
        return this.date.compareTo(post.getDate());
    }

    public String getDateString(){
        return p.format(date);
    }
}
