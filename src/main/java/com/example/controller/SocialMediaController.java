package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


import com.fasterxml.jackson.core.JsonProcessingException;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    //add dependency service layer class
    AccountService accountService;
    MessageService messageService;

    //constructor injection
    @Autowired
    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;

    }

  
    //requirement #1 register new user : add new user into Account (post api)
    //requirement #2 login

    //requirement #3 create new message
    @PostMapping(value = "/messages")
    public ResponseEntity<Object> addMessage(@RequestBody Message message){
        Message messageAdded = this.messageService.addMessage(message);
        //response back from service layer
        if(messageAdded == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(messageAdded, HttpStatus.OK);
        }

    }

    //requrement #4 retrive all the messages
    @GetMapping("/messages")
    public ResponseEntity<Object> getAllMessages(){
        List<Message> messages = this.messageService.getAllMessages();
        //response back from service layer
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    
    //requirement #5 retrieve a message by its ID
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Object> getMessageById(@PathVariable int message_id){
        //1. get information from context 
        //2. call service layer
        Message messageGetById = this.messageService.getMessageById(message_id);
        //3. response back from service layer
        if(messageGetById == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(messageGetById, HttpStatus.OK);
        } 
    }

    //requirement #6 delete a message by id
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Object> deleteMessageById(@PathVariable int message_id) {
        
        boolean result = this.messageService.deleteMessageById(message_id);
        //response back from server
        if(!result){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }else{           
            return ResponseEntity.status(HttpStatus.OK).body("1");
        }
        
    }

    //requirement #7 update message by ID
    //requirement #8 retrieve all message by a user
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<Object> getMessagesByAccountId(@PathVariable int account_id){
            //1.get info from context
            
            //2. call service layer
            List<Message> messages = this.messageService.getMessageByAccountId(account_id);
            //3. get response from service layer
            return new ResponseEntity<>(messages, HttpStatus.OK);


    }





    

}
