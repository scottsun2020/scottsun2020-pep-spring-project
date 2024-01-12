package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    
    

    public Account addAccount(Account newAccount) {
        //int[] httpStatus = {200, 400, 409};
        if(newAccount.getUsername().isBlank()){
            return null;
        }
        //password is at least 4 ch 
        if(newAccount.getPassword().length() < 4){
            return null;
        }
        
        return this.accountRepository.save(newAccount);
    }

    public boolean getAccountByUsername(String username) {
        Optional<Account> optionalAccount = this.accountRepository.findByUsername(username);
        //check if account exist
        if(optionalAccount.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
