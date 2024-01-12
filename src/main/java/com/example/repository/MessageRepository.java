package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query("FROM Message WHERE posted_by = :accountId")
    Optional<List<Message>> findByAccountId(int accountId);

    
    //Optional<Message> findById(@Param("messageId") int messageId);
    // @Query("FROM Message WHERE Message_id = :messageId")
    // Optional<Message> getMessageById(@Param("messageId") int messageId);

    //@Query("SELECT FROM message")
    //List<Message> findAll();

    


}
