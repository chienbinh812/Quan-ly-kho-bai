package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.request.LoginRequest;
import net.javaguides.springboot.dto.request.RegisterRequest;
import net.javaguides.springboot.service.AuthService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import net.javaguides.springboot.entity.Accounts;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUse(@RequestBody LoginRequest loginRequest){
        return authService.authenticateUser(loginRequest);
    }

    // teacher or admin
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@Validated @RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }
    
    @GetMapping("/accounts")
    public ResponseEntity<List<Accounts>> getAllAccounts() {
        List<Accounts> accounts = authService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Accounts> getAccountById(@PathVariable Long id) {
        Optional<Accounts> account = authService.getAccountById(id);
        return account.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable Long id) {
        authService.deleteAccountById(id);
        return ResponseEntity.ok("Tài khoản đã được xóa thành công");
    }
}
