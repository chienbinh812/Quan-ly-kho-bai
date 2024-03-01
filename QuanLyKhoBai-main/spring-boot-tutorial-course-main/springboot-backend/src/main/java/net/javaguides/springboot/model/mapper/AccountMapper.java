package net.javaguides.springboot.model.mapper;

import net.javaguides.springboot.model.Account;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.dto.AccountDto;


public class AccountMapper {


    public AccountDto toAccountDto(Account account) {
        AccountDto temp = new AccountDto();
        temp.setId(account.getId());
        temp.setUsername(account.getUsername());
        temp.setUserId(account.getUser().getId());
        return temp;
    }
}