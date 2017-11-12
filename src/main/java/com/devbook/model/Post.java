package com.devbook.model;

import com.ocpsoft.pretty.time.PrettyTime;
import lombok.Data;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Post implements Comparable<Post> {

    @Id
    private  String _id;
    private Date date;
    private String body;
    private String dateString;
    private List<Comment> commentList;

    static PrettyTime p = new PrettyTime();

    public Post(String body) {
        this.date = new Date();
        this.body = body;
        this.dateString = date.toString();
        this.commentList = new ArrayList<>();
    }

    @Override
    public int compareTo(Post post) {
        return this.date.compareTo(post.getDate());
    }

    public String getDateString() {
        return p.format(date);
    }
}
