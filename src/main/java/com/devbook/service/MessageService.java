package com.devbook.service;

import com.devbook.model.Message;
import com.devbook.model.User;
import com.devbook.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {


    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message searchBySender(User user) {
        return messageRepository.findBySender(user);
    }
}
