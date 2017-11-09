package com.devbook.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Post implements Comparable<Post>{

    //TODO overide getDateString -> jezeli post byl puszczony do 10 min temu to ma pisac now,
    //TODO                                                      dzisiaj to ma pisac dzisiaj
    //TODO                                                        do 30 dni ma pisac x dni temu
    //TODO                                                       ponad 30 dni no to sama data bez godziny

    private LocalDateTime localDateTime;
    private String body;
    private String dateString;

    public Post(String body) {
        this.localDateTime = LocalDateTime.now();
        this.body = body;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        this.dateString = localDateTime.format(formatter);
    }

    @Override
    public int compareTo(Post post) {
        return this.localDateTime.compareTo(post.getLocalDateTime());
    }
}
