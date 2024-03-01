package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.request.LoginRequest;
import net.javaguides.springboot.dto.request.RegisterRequest;
import net.javaguides.springboot.dto.response.JwtResponse;
import net.javaguides.springboot.dto.response.MessageResponse;
import net.javaguides.springboot.entity.Accounts;
import net.javaguides.springboot.repository.AccountsRepository;
import net.javaguides.springboot.repository.IAccountRepository;
import net.javaguides.springboot.security.jwt.JwtUtils;
import net.javaguides.springboot.security.services.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    IAccountRepository iAccountRepository;

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    private AccountsRepository accountsRepository;

    // Xác thực người dùng và trả về JWT Token
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        // Tạo một đối tượng UsernamePasswordAuthenticationToken với thông tin xác thực từ yêu cầu đăng nhập
        UsernamePasswordAuthenticationToken obj = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        // Xác thực đối tượng
        Authentication authentication = authenticationManager.authenticate(obj);
        // Lưu thông tin xác thực vào SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Tạo JWT Token từ thông tin xác thực
        String jwt = jwtUtils.generateJwToken(authentication);

        // Trích xuất chi tiết người dùng từ thông tin xác thực
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Trả về phản hồi chứa JWT Token và thông tin người dùng
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.isAdmin(),
                userDetails.getCreateAt(),
                userDetails.getUpdateAt()
        ));
    }

    // Đăng ký tài khoản mới
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        // Kiểm tra xem tên người dùng đã tồn tại chưa
        if (iAccountRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("User name is already taken"));
        }

        // Tạo đối tượng Account và gán thông tin từ yêu cầu đăng ký
        Accounts account = new Accounts();
        account.setEmail(registerRequest.getEmail());
        account.setUsername(registerRequest.getUsername());
        account.setPassword(registerRequest.getPassword());

        // Kiểm tra và xử lý yêu cầu về độ dài và khoảng trắng trong tên người dùng
        if (account.getUsername().trim().length() >= 5 && (account.getUsername().contains(" "))){
            return ResponseEntity.badRequest().body(new MessageResponse("username must greater than or equal to 5 and no space"));
        }

        // Mã hóa mật khẩu và lưu tài khoản vào cơ sở dữ liệu
        account.setPassword(encoder.encode(registerRequest.getPassword()));
        iAccountRepository.save(account);

        // Trả về phản hồi thành công
        return ResponseEntity.ok(new MessageResponse("registered successfully!"));
    }
    
    public List<Accounts> getAllAccounts() {
        return accountsRepository.findAll();
    }

    public Optional<Accounts> getAccountById(Long id) {
        return accountsRepository.findById(id);
    }

    public void deleteAccountById(Long id) {
        accountsRepository.deleteById(id);
    }
}
