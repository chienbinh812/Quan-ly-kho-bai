package net.javaguides.springboot.security.jwt;

import net.javaguides.springboot.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    //Mã nguồn này thực hiện quá trình xác thực người dùng bằng cách kiểm tra và xác minh JWT trong yêu cầu,
    // sau đó cung cấp thông tin xác thực cho Spring Security để sử dụng trong việc ủy quyền và kiểm tra quyền truy cập.

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Bước 1: Trích xuất JWT từ tiêu đề yêu cầu
            String jwt = parseJwt(request);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // Bước 2: Trích xuất email từ JWT và tải chi tiết người dùng
                String email = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // Bước 3: Xác thực người dùng và cài đặt thông tin xác thực vào SecurityContextHolder
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // Ghi log nếu có lỗi xác thực
            logger.error("Cannot set user authentication: {}", e);
        }

        // Tiếp tục chuỗi bộ lọc
        filterChain.doFilter(request, response);
    }

    // Phân tích JWT từ tiêu đề Authorization của yêu cầu
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); // Bỏ đi "Bearer " để lấy phần token
        }

        return null;
    }
}
