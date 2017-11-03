package com.devbook.controller;

import com.devbook.model.Message;
import com.devbook.model.User;
import com.devbook.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/searchBySender")
    public Message searchBySender(User user) {
        return messageService.searchBySender(user);
    }
}
