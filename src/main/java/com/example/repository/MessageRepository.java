package com.example.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

    
    @Query("FROM message WHERE message_id = :messageId")
    Optional<Message> getMessageById(@Param("messageId") int id);


    @Query("SELECT * FROM message")
    ArrayList<Message>getAllMessages();


}
