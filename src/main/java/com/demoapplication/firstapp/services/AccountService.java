package com.demoapplication.firstapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demoapplication.firstapp.models.Account;
import com.demoapplication.firstapp.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void save(Account account){
        accountRepository.save(account);
    }
    
}
