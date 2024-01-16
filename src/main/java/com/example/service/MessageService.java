package com.example.service;

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

        //requirement 6: delete message by Id
    public boolean deleteMessageById(int messageId){
        //check if message id not exist 
        Optional<Message> optionalMessage = this.messageRepository.findById(messageId);
        //delete the message through repository
        if(optionalMessage.isPresent()){            
            this.messageRepository.deleteById(messageId);
            return true;
            
        }else{
            return false;
        }
    }

    public List<Message> getMessageByAccountId(int account_id) {
        Optional<List<Message>> optionalMessages = this.messageRepository.findByAccountId(account_id);

        if(optionalMessages.isPresent()){
            return optionalMessages.get();
        }else{
            return null;
        }
    }

    //requirement 7: update 
    public Message updateMessageById(int messageId, String messageText) {

        //the message length is less and equal than 255 characters 
        if(messageText.length() >= 255){
            return null;
        }
        //message string is empty
        if(messageText.trim().isBlank()){
            return null;
        }   

        //check if the message does not exist 
        Optional<Message> optionalMessage = this.messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            message.setMessage_text(messageText);
            this.messageRepository.save(message);
            return message;

        } else{
            return null;
        }
    }           
}
