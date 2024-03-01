package net.javaguides.springboot.service;

import net.javaguides.springboot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {

    private final AccountRepository accountRepository;

    @Autowired
    public LoginServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


}
