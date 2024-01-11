package com.example.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    //dependency
    MessageRepository messageRepository;

    @Autowired
    public MessageService (MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public ArrayList<Message> getAllMessages() {
        return this.messageRepository.getAllMessages();
    }

    public Message getMessageById(int id) {
        Optional<Message> optionalMessage = this.messageRepository.getMessageById(id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }

    public Message addMessage(Message message) {
        return null;
    }


}
