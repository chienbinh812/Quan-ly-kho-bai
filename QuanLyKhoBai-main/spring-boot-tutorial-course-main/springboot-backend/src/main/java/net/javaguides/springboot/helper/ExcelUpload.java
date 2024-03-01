package net.javaguides.springboot.helper;

import net.javaguides.springboot.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUpload {
    public static boolean isValidExcelFile(MultipartFile file){

        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );

    }
    public static List<User> excelToUsers(InputStream inputStream) {

        List<User> users = new ArrayList<User>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("users");
            DataFormatter formatter = new DataFormatter();
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                User user = new User();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String strValue = formatter.formatCellValue(cell);
                    switch (cellIndex) {
                        case 0:

                            user.setId(Integer.parseInt(strValue));
                            break;
                        case 1:
                            user.setName(strValue);
                            break;
                        case 2:
                            user.setRole(Integer.parseInt(strValue));
                            break;

                        default:
                            break;
                    }
                    cellIndex++;
                }
                users.add(user);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return users;
    }
}
