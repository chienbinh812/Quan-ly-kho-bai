package net.javaguides.springboot.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    void save(MultipartFile file);
}
