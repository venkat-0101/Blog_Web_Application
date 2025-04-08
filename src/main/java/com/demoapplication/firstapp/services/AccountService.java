package com.demoapplication.firstapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demoapplication.firstapp.config.SecurityConfig;
import com.demoapplication.firstapp.models.Account;
import com.demoapplication.firstapp.repositories.AccountRepository;

@Service
public class AccountService implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SecurityConfig securityConfig;

    public void save(Account account){
        //encrypting the password before saving in the DB
        account.setPassword(securityConfig.passwordEncoder().encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account;
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);
        if(!(optionalAccount.isPresent())){
                throw new UsernameNotFoundException("User Not Found!");
        }
        else{
           account = optionalAccount.get();
        }

        //Creating an Granted authority list and passing it as a parameter while returning the user
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER"));
        return new User(account.getEmail(), account.getPassword(), authorityList);  
    }

   



    
}
