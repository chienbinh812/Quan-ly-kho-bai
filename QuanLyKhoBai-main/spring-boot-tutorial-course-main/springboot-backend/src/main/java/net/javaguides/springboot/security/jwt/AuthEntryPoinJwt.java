package net.javaguides.springboot.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPoinJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPoinJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Ghi log lỗi xác thực không được ủy quyền
        logger.error("Unauthorized error: {}", authException.getMessage());

        // Đặt loại nội dung của phản hồi là JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Đặt mã trạng thái của phản hồi thành "Unauthorized" (401)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Tạo một bản đồ chứa thông tin lỗi để trả về trong phản hồi JSON
        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        // Sử dụng ObjectMapper để viết dữ liệu từ bản đồ vào luồng phản hồi
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
