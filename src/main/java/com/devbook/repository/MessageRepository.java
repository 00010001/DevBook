package com.devbook.repository;

import com.devbook.model.Message;
import com.devbook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface  MessageRepository extends MongoRepository<Message, Date>{

    Message findBySender(User sender);
}
