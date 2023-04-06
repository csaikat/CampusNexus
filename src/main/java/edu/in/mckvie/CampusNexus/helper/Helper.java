package edu.in.mckvie.CampusNexus.helper;

import edu.in.mckvie.CampusNexus.entities.StudentDetails;
import jakarta.mail.Multipart;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType=file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<StudentDetails> convertExcelToListOfStudentDetails(InputStream is){
        List<StudentDetails> list=new ArrayList<>();
        try{
            XSSFWorkbook sheets = new XSSFWorkbook(is);
            XSSFSheet sheet=sheets.getSheet("Sheet1");
            int rowNumber=0;
            Iterator<Row> iter = sheet.iterator();
            while(iter.hasNext()){
                Row row=iter.next();
                if(rowNumber==0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells= row.iterator();
                int cid=0;
                StudentDetails studentDetails=new StudentDetails();
                while(cells.hasNext()){
                    Cell cell=cells.next();
                    switch (cid){
                        case 0:
                            studentDetails.setName(cell.getStringCellValue());
                            break;
                        case 1:
                            studentDetails.setMailID(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(studentDetails);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
