package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    //add dependency service layer class
    AccountService accountService;
    MessageService messageService;

    //create constructor with service object
    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    //create Javalin Method
    public Javalin startAPI(){
        //create javalin object
        Javalin app = Javalin.create().start(8080);

        //ALL END POINTs
        //requirement #1 register new user : add new user into Account (post api)
        app.post("/register", this::addNewAccount);
        
        //requirement #2 login
        //requirement #3 create new message
        //requrement #4 retrive all the messages
        //requirement #5 retrieve a message by its ID
        //requirement #6 delete a message by id
        //requirement #7 update message by ID
        //requirement #8 retrieve all message by a user
        return app;
    }

        //ALL HANDLERs FOR ENDPOINTs

        
        private void addNewAccount(Context ctx) throws JsonProcessingException{
            //1. get information from context
            Account newAccount = ctx.bodyAsClass(Account.class);
            //2. call service layer
            Account accountAdded = this.accountService.addAccount(newAccount);
            //3. response back from service layer
            if(accountAdded != null){
                ctx.json(accountAdded).status(200);
            }else{
                
            }
        }

}
