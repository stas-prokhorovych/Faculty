package com.example.model.utils;

import com.example.model.entity.User;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to generate pdf report
 */
public class Pdf {
    public static void export(HttpServletResponse response, List<User> userToPrint, String role) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        // setting column widths
        table.setWidths(new float[] {6.0f, 11.0f, 5.0f, 5.0f, 3.0f});
        Font font = new Font(Font.HELVETICA, 12, Font.BOLDITALIC);
        PdfPCell cell = new PdfPCell();

        // table headers
        cell.setPhrase(new Phrase("Login", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("First name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Last name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Access", font));
        table.addCell(cell);

        for(User user : userToPrint) {
            table.addCell(user.getLogin());
            table.addCell(user.getEmail());
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(String.valueOf(user.isUserAccess()));
        }

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph(role + "s", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        document.add(table);
        document.close();
    }
}
