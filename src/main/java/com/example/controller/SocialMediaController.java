package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


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
    @PostMapping("/register")
    public ResponseEntity<Object> registerAccount(@RequestBody Account account){
        //get info from request body
        String username = account.getUsername();
        // String password = account.getPassword();
        //call service layer
        //first check if username exists
        boolean usernameDuplication = this.accountService.getAccountByUsername(username);
        if(usernameDuplication){
            return new ResponseEntity<Object>(HttpStatus.CONFLICT);
        }
        //check username and password requirement
        Account accountAdded = this.accountService.addAccount(account);
        if(accountAdded == null){
            return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<Object>(accountAdded, HttpStatus.OK);
        }
    }
    //requirement #2 login
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Account account){
        Account accountLogined = this.accountService.getAccountByUsernameAndPassword(account);
        if(accountLogined == null){
            return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<Object>(accountLogined, HttpStatus.OK);
        }      
    }

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
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Object> updateMessageById(@PathVariable int message_id, @RequestBody Message messageUpdate){
        //1.get info from request body
        String textUpdated = messageUpdate.getMessage_text();
        //2. call service layer
        Message messageUpdated = this.messageService.updateMessageById(message_id, textUpdated);
        //3.get response from service layer
        if(messageUpdated == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>("1", HttpStatus.OK);
        }
    }

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
