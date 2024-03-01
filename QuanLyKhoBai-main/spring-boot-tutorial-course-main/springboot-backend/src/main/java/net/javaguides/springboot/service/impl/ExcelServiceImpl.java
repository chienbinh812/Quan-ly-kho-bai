package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.helper.ExcelUpload;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private UserRepository userRepository;

    @Override
    public void save(MultipartFile file) {
        if(ExcelUpload.isValidExcelFile(file)) {
            try {
                List<User> users = ExcelUpload.excelToUsers(file.getInputStream());
                users.forEach(user -> {
                    if(userRepository.existsById(user.getId()))  throw new RuntimeException("User Id is exist, re-check file upload");
                });
                this.userRepository.saveAll(users);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
}
