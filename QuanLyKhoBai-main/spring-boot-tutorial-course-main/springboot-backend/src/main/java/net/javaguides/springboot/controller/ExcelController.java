package net.javaguides.springboot.controller;


import net.javaguides.springboot.service.ExcelService;
import net.javaguides.springboot.service.impl.ExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import net.javaguides.springboot.helper.ExcelUpload;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelServiceImpl excelService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        this.excelService.save(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Customers data uploaded and saved to database successfully"));
    }
}
