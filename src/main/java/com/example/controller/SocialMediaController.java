package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public SocialMediaController(MessageService messageService){
        this.messageService = new MessageService();
    }


  
        //requirement #1 register new user : add new user into Account (post api)
        //requirement #2 login
    //requirement #3 create new message
    @PostMapping(value = "/requestbody")
    public Message addMessage(@RequestBody Message message){
        return this.messageService.addMessage(message);
    }


    //requrement #4 retrive all the messages
    @GetMapping("/messages")
    public ArrayList<Message> getAllMessages(){
        return this.messageService.getAllMessages();
    }
    
    //requirement #5 retrieve a message by its ID
    @GetMapping("/messages/{message_id}")
    public Message getMessageById(@PathVariable int messageId){
        return this.messageService.getMessageById();
    }
    
        //requirement #6 delete a message by id
        //requirement #7 update message by ID
        //requirement #8 retrieve all message by a user





    private void getAllMessages(Context ctx) {
        //- The response body should contain a JSON representation of a list containing all messages 
        //retrieved from the database. It is expected for the list to simply be empty if there are no messages. 
        //The response status should always be 200, which is the default.
        //1.get information from context
        ArrayList<Message> messages = new ArrayList<Message>();
        //2. call service layer
        messages = this.messageService.getAllMessages();
        //3. resposne back from sercie layer
        ctx.json(messages).status(200);
    }




    

}
