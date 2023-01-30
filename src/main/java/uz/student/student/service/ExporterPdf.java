package uz.student.student.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import uz.student.student.model.StudentModel;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

import static uz.student.student.utils.EndPoint.pdfPath;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 30.01.2023
 **/
public class ExporterPdf {

    private final StudentModel studentModel;

    @Value("${upload.file.folder}")
    private String url;
    public ExporterPdf(StudentModel studentModel) {
        this.studentModel = studentModel;
    }


    //

    private void writeToTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(10);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("User ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Middle Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("University", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Field of study", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Study started date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Study end date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Created at", font));
        table.addCell(cell);
    }
    private void setImageToTabel(Document document, String url) throws IOException {
        Image image = Image.getInstance((url));
        image.scaleAbsolute(150, 150);
        image.setAlignment(Image.RIGHT);
        document.add(image);
    }
    private void writeToTableData(PdfPTable table){
        table.addCell(String.valueOf(studentModel.getId()));
        table.addCell(studentModel.getFirstName());
        table.addCell(studentModel.getLastName());
        table.addCell(studentModel.getMiddleName());
        table.addCell(studentModel.getDescription());
        table.addCell(studentModel.getUnevrsityModel().getUniversity());
        table.addCell(studentModel.getUnevrsityModel().getFieldsOfStudyModel().getFieldOfStudy());
        table.addCell(studentModel.getStudyStatDate());
        table.addCell(studentModel.getStudyEndDate());
        table.addCell(studentModel.getCreatedAt());

    }
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.RED);

        Paragraph paragraph = new Paragraph("Resume", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(10);
        PdfPTable table1 = new PdfPTable(1);
        table.setWidthPercentage(100f);
        table1.setWidthPercentage(100f);
        table.setWidths(new float[]{0.3f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f});
        table1.setWidths(new float[]{2f});
        table.setSpacingBefore(10);
        table1.setSpacingBefore(10);


        writeToTableHeader(table);
        writeToTableData(table);

        document.add(table);
        document.add(table1);
        String url1 = pdfPath + studentModel.getFileStorageModel().getUploadFolder();
        setImageToTabel(document, url1);
        document.close();
    }



}
