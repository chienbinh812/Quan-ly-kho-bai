package net.javaguides.springboot.service;

import net.javaguides.springboot.login.LoginMessage;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.dto.AccountDto;
import net.javaguides.springboot.model.Account;
import org.springframework.data.domain.Page;
//import net.javaguides.springboot.response.LoginMessage;

import java.util.List;
public interface AccountService {
    Account saveAccount(Account account);

//    LoginMessage loginAccount(Account account);
    List<Account> getAllAccount();
    Account getAccountById(int id);
    Account updateAccount(Account account, int id);
    void deleteAccount(int id);
    Page<Account> getAllAccountByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size
            , Integer id, String username);

}
