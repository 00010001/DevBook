package com.devbook.service;

import com.devbook.model.Message;
import com.devbook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private UserService userService;

    @Autowired
    public MessageService(UserService userService) {
        this.userService = userService;
    }

    public Message createMessage(String targetUserId, String messageBody) {
        User sender = userService.getCurrentlyLoggedUser();

        Message message = new Message();
        message.setSenderFirstName(sender.getFirstName());
        message.setSenderLastName(sender.getLastName());
        message.setMessageBody(messageBody);
        message.setSenderProfileImageUrl(sender.getProfileImageUrl());
        message.setSenderId(sender.get_id());
        message.setReceiverId(targetUserId);
        return message;
    }

    public void addMessageToSenderUserAndSave(Message myMessage) {
        User currentUser = userService.getCurrentlyLoggedUser();
        currentUser.getMessages().add(myMessage);
        userService.saveUser(currentUser);
    }

    public void addMessageToReceiverUserAndSave(Message myMessage) {
        User receiver = userService.getUserById(myMessage.getReceiverId());
        receiver.getMessages().add(myMessage);
        userService.saveUser(receiver);
    }

    public List<Message> getCurrentUserMessages() {
        return userService.getUserById(userService.getCurrentlyLoggedUser().get_id()).getMessages();
    }
}
