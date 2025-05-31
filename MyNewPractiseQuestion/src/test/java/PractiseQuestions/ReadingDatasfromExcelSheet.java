package PractiseQuestions;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingDatasfromExcelSheet {

    public static void main(String[] args) throws IOException {

        // ✅ Correct file path — update with your actual Excel file path
        FileInputStream fis = new FileInputStream("F:\\NewHRlist.xlsx");

        // ✅ Open workbook
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        // ✅ Select the sheet
        XSSFSheet sheet = wb.getSheet("Sheet1");

        // ✅ Get the total number of rows (excluding header row)
        int rowCount = sheet.getLastRowNum();
        System.out.println("Total number of rows: " + rowCount);

        // ✅ Get the number of cells (columns) in the first row
        int cellCount = sheet.getRow(0).getLastCellNum();
        System.out.println("Total number of columns: " + cellCount);

        // ✅ Loop through rows and columns
        for (int r = 0; r <= rowCount; r++) {
            XSSFRow currentRow = sheet.getRow(r);
            for (int c = 0; c < cellCount; c++) {
                XSSFCell currentCell = currentRow.getCell(c);
                String value = "";

                // Handle null cells
                if (currentCell != null) {
                    value = currentCell.toString();
                }
                System.out.print(value + "\t");
            }
            System.out.println();
        }

        // ✅ Close workbook and file stream
        wb.close();
        fis.close();
    }
}
