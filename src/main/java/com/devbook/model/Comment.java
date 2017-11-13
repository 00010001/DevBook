package com.devbook.model;

import com.ocpsoft.pretty.time.PrettyTime;
import lombok.Data;
import java.util.Date;

@Data
public class Comment implements Comparable<Comment> {

    private Date date;
    private String body;
    private String dateString;
    private String commentPoster;
    private String commentReceiver;

    static PrettyTime p = new PrettyTime();

    public Comment(String body, String commentPoster, String commentReceiver) {
        this.commentPoster = commentPoster;
        this.commentReceiver = commentReceiver;
        this.date = new Date();
        this.body = body;
        this.dateString = date.toString();
    }

    @Override
    public int compareTo(Comment comment) {
        return this.date.compareTo(comment.getDate());
    }

    public String getDateString() {
        return p.format(date);
    }
}
