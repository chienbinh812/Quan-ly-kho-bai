package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Account;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.AccountRepository;
//import net.javaguides.springboot.response.LoginMessage;
import net.javaguides.springboot.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
//    @Autowired

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }


    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Page<Account> getAllAccountByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size, Integer id, String username) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Account> accounts = accountRepository.findAll(pageable);
//        return userRepository.findUsersByPageWithFilters(pageable,id,name);
        return accounts;
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", id));
    }

    @Override
    public Account updateAccount(Account account, int id) {
        Account existingAccount = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Id", id));

        existingAccount.setUsername(account.getUsername());
        existingAccount.setPassword(account.getPassword());
        accountRepository.save(existingAccount);
        return existingAccount;
    }

    @Override
    public void deleteAccount(int AccountId) {
        accountRepository.findById(AccountId).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", AccountId));
        accountRepository.deleteById(AccountId);
    }
}
