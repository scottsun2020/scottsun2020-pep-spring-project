package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    //dependency
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService (MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message getMessageById(int id) {
        //Optional<Message> optionalMessage = this.messageRepository.getMessageById(id);
        Optional<Message> optionalMessage = this.messageRepository.findById(id);

        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }

    public Message addMessage(Message newMessage) {
        //message is not blank
        if(newMessage.getMessage_text().isBlank()){
            return null;
        }
        //under 255 ch
        if(newMessage.getMessage_text().length()>= 255){
            return null;
        }

        //post by a real existing user account_id exists
        if(!this.accountRepository.existsById(newMessage.getPosted_by())){
            return null;
        }

        return this.messageRepository.save(newMessage);
    }


}
