package com.final_project.eduflow.Services;

import com.final_project.eduflow.Data.Entities.StudentRequests;
import com.final_project.eduflow.Services.Interfaces.ICreatePdf;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

public  class CreateRequestPdf implements ICreatePdf{
    
    @Override
    public void createRequestPdf(StudentRequests request) {
        String dest = "./request.pdf"; // specify the destination of the PDF file
        String content = request.getAddition(); // get the content to be written to the PDF

        try {
            PdfWriter writer = new PdfWriter(dest);
            Document document = new Document(writer);
            document.add(new Paragraph(content));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
