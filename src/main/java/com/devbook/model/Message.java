package com.devbook.model;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Message {

    private String senderProfileImageUrl;
    private String senderFirstName;
    private String senderLastName;
    private String messageBody;
    private String senderId;
    private String receiverId;

    private Date date;
    private String dateString;

    public Message() {
        this.date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy ");
        this.dateString = dateFormat.format(date);
    }
}
