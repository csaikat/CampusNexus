package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.entities.StudentPayment;
import edu.in.mckvie.CampusNexus.repositories.StudentPaymentRepository;
import edu.in.mckvie.CampusNexus.services.InvoiceService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private StudentPaymentRepository studentPaymentRepository;
    @Autowired
    private ResourceLoader resourceLoader;
    @Override
    public byte[] generateAndServeInvoice() throws FileNotFoundException, JRException {


        //JRBeanCollectionDataSource jrBeanCollectionDataSource=new JRBeanCollectionDataSource();
        JasperReport compileReport=JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/invoice.jrxml"));
        HashMap<String,Object> map = new HashMap<>();
        map.put("session","04/2021 - 03/2022");
        map.put("paymentDate",new Date().toString());
        map.put("paymentFor","Monish Paul");
        map.put("class","3rd Year");
        map.put("latePayCharge",100.00);
        map.put("otherCharge",0.00);
        map.put("razerPayRefNo","RZ-1234");
        map.put("remainingAmount",0.00);
        map.put("feeDescription","Jan-22");
        map.put("modeOfPay","UPI");
        map.put("course","CSE");
        map.put("regNo","1234596");
        map.put("paidAmount",25000.00);
        map.put("paid",25000.00);
        map.put("discountAmount",0.00);

        JasperPrint report=JasperFillManager.fillReport(compileReport,map,new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(report,"invoice.pdf");

        byte[] pdfData=JasperExportManager.exportReportToPdf(report);
//        HttpHeaders headers=new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename=invoice.pdf");
//        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfData);

        return pdfData;
    }
    @Value("${project.image}")
    String basePath;
    @Override
    public String generateInvoice(int userId,int semId) throws IOException, JRException, ParseException {
        //fetch payment details

        StudentPayment studentPayment=this.studentPaymentRepository.findByUserIdAndSemId(userId,semId);
        System.out.println("payment-details: "+studentPayment);
        DateFormat formatter = new SimpleDateFormat("MM-yy");
        Date today = new Date();
        Date d = formatter.parse(formatter.format(today));

        System.out.println("year:"+studentPayment.getSemester().getYear().getName());
        String year=studentPayment.getSemester().getYear().getName();
        String path=resourceLoader.getResource("classpath:invoice.jrxml").getURI().getPath();
//        JasperReport compileReport=JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/invoice.jrxml"));
        System.out.println("path: "+path);
        InputStream employeeReportStream = getClass().getResourceAsStream("/invoice.jrxml");

        JasperReport compileReport=JasperCompileManager.compileReport(employeeReportStream );
        HashMap<String,Object> map = new HashMap<>();
        map.put("session","04/2021 - 03/2022");
        map.put("paymentDate",new Date().toString());
        map.put("paymentFor",studentPayment.getPaymentDetails().getUser().getName());
        map.put("class",year);
        map.put("latePayCharge",0.00);
        map.put("otherCharge",0.00);
        map.put("razerPayRefNo",studentPayment.getPaymentDetails().getPaymentId());
        map.put("remainingAmount",studentPayment.getDueFees());
        map.put("feeDescription",d.toString());
        map.put("modeOfPay","UPI");
        map.put("course",studentPayment.getPaymentDetails().getUser().getDepartment().getDeptName());
        map.put("regNo",studentPayment.getPaymentDetails().getUser().getUniversityRollNumber());
        map.put("paidAmount",studentPayment.getSemester().getFees().getFees());
        Double paid=Double.parseDouble(studentPayment.getPaymentDetails().getAmount())/100;
        map.put("paid",paid);
        map.put("discountAmount",0.00);
        map.put("currency",studentPayment.getPaymentDetails().getCurrency());


        String pdfName="RAZORPAY-PAYMENT-RECEIPT-"+UUID.randomUUID()+".pdf";
        JasperPrint report=JasperFillManager.fillReport(compileReport,map,new JREmptyDataSource());
        File file=new File(basePath);

        if(!file.exists()){
            file.mkdir();
        }
        String upload_path=basePath+pdfName;
        System.out.println("basepath: "+upload_path);
        JasperExportManager.exportReportToPdfFile(report, upload_path);
        System.out.println("exported");
        return pdfName;
    }
}
