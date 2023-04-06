package edu.in.mckvie.CampusNexus.services.servicesimpl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import edu.in.mckvie.CampusNexus.services.PdfService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;
@Service
public class PdfServiceImpl implements PdfService {
//    private Logger log= (Logger) LoggerFactory.getLogger(PdfServiceImpl.class);
    public ByteArrayInputStream createPdf(){
//        log.info("create pdf start");
        String title="welcome to demo";
        String content="Lorem500";
        ByteArrayOutputStream out =new ByteArrayOutputStream();
        Document document=new Document();
        try{
            PdfWriter.getInstance(document,out);
            document.open();

            Font titleFont= FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
            Paragraph titleParagraph=new Paragraph(title,titleFont);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);

            Font paraFont= FontFactory.getFont(FontFactory.HELVETICA,18);
            Paragraph paraParagraph=new Paragraph(content,paraFont);
            paraParagraph.add(new Chunk("wow this text"));
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paraParagraph);

            document.close();
        }catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}

