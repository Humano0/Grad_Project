package com.final_project.eduflow.Services;

import org.springframework.stereotype.Service;

import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Services.Interfaces.ICreatePdf;

import java.util.List;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public  class CreateRequestPdf implements ICreatePdf{
    
/*     @Override
    public void createRequestPdf(StudentRequests request) {

        try  {
            File file = new File("src/main/resources/ogrenci_dilekce_sablon.pdf");//C:\Users\BORAN\Documents\WebDevelopt\Grad\Grad_Project\eduflow\src\main\resources\ogrenci_dilekce_sablon.pdf
            PDDocument document = Loader.loadPDF(file);
            String searchString = "Öğrenci tarafından doldurulacaktır. Öğrenci almak istediği dersin kodunu, adını ve şubesini tam olarak yazmalıdır.";
            String replacement = request.getAddition();


            PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(0), AppendMode.PREPEND, true, true);
                contentStream.beginText();
                contentStream.setFont( new PDType1Font( Standard14Fonts.FontName.TIMES_ROMAN), 12 );
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(replacement);
                contentStream.endText();
                contentStream.close();
            
            document.save("./updated_ogrenci_dilekce_sablon.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }


    } */

    @Override
    public void createRequestPdf(StudentRequests request) {
        try {
/*             PdfReader reader = new PdfReader("src/main/resources/ogrenci_dilekce_sablon.pdf");
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("./updated_ogrenci_dilekce_sablon.pdf"));
            BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.EMBEDDED);
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(bf, 12);
            Paragraph paragraph = new Paragraph(request.getAddition(), font);
            paragraph.setSpacingBefore(700);
            Document document = new Document(reader.getPageSizeWithRotation(1));
            
            document.open();
            document.add(paragraph);
            document.close();
            stamper.close();
            reader.close(); */

            PdfReader reader = new PdfReader("src/main/resources/ogrenci_dilekce_sablon.pdf");
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("./updated_ogrenci_dilekce_sablon.pdf"));
            BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.EMBEDDED);
            PdfContentByte over = stamper.getOverContent(1);
            over.beginText();
            over.setFontAndSize(bf, 12);
            over.setTextMatrix(100, 700); // set x,y position (0,0 is at the bottom left)
            over.showText(request.getAddition());
            over.endText();
            stamper.close();
            reader.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }


}
