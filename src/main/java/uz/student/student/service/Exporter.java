package uz.student.student.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.student.student.model.StudentModel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Asadbek
 * @project IntelliJ IDEA
 * @Email asadbek9805@gmail.com
 * @Date 29.01.2023
 **/
public class Exporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<StudentModel> studentModelList;

    public Exporter(List<StudentModel> studentModels) {
        this.studentModelList = studentModels;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "User ID", style);
        createCell(row, 1, "First Name", style);
        createCell(row, 2, "Last Name", style);
        createCell(row, 3, "Middle Name", style);
        createCell(row, 4, "Description", style);
        createCell(row, 5, "University", style);
        createCell(row, 6, "Field of study", style);
        createCell(row, 7, "Study started date", style);
        createCell(row, 8, "Study end date", style);
        createCell(row, 9, "Created at", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else {
            cell.setCellValue((Boolean) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (StudentModel studentModel : studentModelList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, studentModel.getId(), style);
            createCell(row, columnCount++, studentModel.getFirstName(), style);
            createCell(row, columnCount++, studentModel.getLastName(), style);
            createCell(row, columnCount++, studentModel.getMiddleName(), style);
            createCell(row, columnCount++, studentModel.getDescription(), style);
            createCell(row, columnCount++, studentModel.getUnevrsityModel().getUniversity(), style);
            createCell(row, columnCount++, studentModel.getUnevrsityModel().getFieldsOfStudyModel()
                    .getFieldOfStudy(), style);
            createCell(row, columnCount++, studentModel.getStudyStatDate(), style);
            createCell(row, columnCount++, studentModel.getStudyEndDate(), style);
            createCell(row, columnCount++, studentModel.getCreatedAt(), style);
        }

        }


    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }
}


