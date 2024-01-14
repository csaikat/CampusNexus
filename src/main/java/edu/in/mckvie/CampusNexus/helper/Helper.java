package edu.in.mckvie.CampusNexus.helper;

import edu.in.mckvie.CampusNexus.entities.User;
import edu.in.mckvie.CampusNexus.services.servicesimpl.StudentServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Helper {


    private StudentServiceImpl st=new StudentServiceImpl();
    public  boolean checkExcelFormat(MultipartFile file){
        String contentType=file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public  List<User> convertExcelToListOfStudentDetails(InputStream is){
        List<User> list=new ArrayList<>();
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
                User studentDetails=new User();
                while(cells.hasNext()){
                    Cell cell=cells.next();
                    switch (cid){
                        case 0:
                            studentDetails.setCollageRollNumber(cell.getStringCellValue());
                            break;
                        case 1:
                            studentDetails.setExamRollNumber(cell.getStringCellValue());
                            break;
                        case 2:
                            studentDetails.setName(cell.getStringCellValue());
                            break;
                        case 3:
                            studentDetails.setUniversityRollNumber(cell.getStringCellValue());
                            break;
                        case 4:
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            Date date = formatter.parse(cell.getStringCellValue());
                            studentDetails.setDob(date);
                            break;
                        case 5:
                            //Optional<Department> d= departmentRepositories.findById((int)cell.getNumericCellValue());

//                            //studentDetails.setDepartment();
                            break;
                        case 6:
                            studentDetails.setLateral(Boolean.parseBoolean(String.valueOf(cell.getNumericCellValue())));
                            break;
                        case 7:
                            studentDetails.setStreamChanger(Boolean.parseBoolean(String.valueOf(cell.getNumericCellValue())));
                            break;
                        case 8:
                            studentDetails.setEmail(cell.getStringCellValue());
                            break;
                        case 9:
                            studentDetails.setContactNumber(cell.getStringCellValue());
                            break;
                        case 10:
                            studentDetails.setPassword("1234");
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
