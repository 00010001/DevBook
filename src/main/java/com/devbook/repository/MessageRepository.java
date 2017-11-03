package com.devbook.repository;

import com.devbook.model.Message;
import com.devbook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;


public interface  MessageRepository extends MongoRepository<Message, Date>{

    Message findBySender(User sender);
}
