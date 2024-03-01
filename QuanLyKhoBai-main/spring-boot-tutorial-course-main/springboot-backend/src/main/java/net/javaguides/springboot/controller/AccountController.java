package net.javaguides.springboot.controller;
import net.javaguides.springboot.model.Account;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    @GetMapping
//    public List<Account> getAllAccount() {
//        return accountService.getAllAccount();
//    }

    @GetMapping()
    public ResponseEntity<Page<Account>> getAllAccountByPageWithFilters(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name
    ) {
        Page<Account> accounts = accountService.getAllAccountByPageWithFilters(sortBy, sortDir, page, size, id, name);
        return ResponseEntity.ok(accounts);
    }

//    @GetMapping("{id}")        return accountService.getAllAccount();
//    public ResponseEntity<Account> getAccountById(@PathVariable("id") int AccountId) {
//        return new ResponseEntity<Account>(AccountService.getAccountById(AccountId), HttpStatus.OK);
//    }
    @PostMapping()
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.saveAccount(account), HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") int id
            , @RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.updateAccount(account, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") int id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<String>("Account da xoa thanh cong!.", HttpStatus.OK);
    }
}
