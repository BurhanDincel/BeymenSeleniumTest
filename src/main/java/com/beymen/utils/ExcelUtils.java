package com.beymen.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;
    private static String filePath = "C:\\Users\\Administrator\\Desktop\\TestiniumProject\\Beymen.xlsx";

    // Excel dosyasını aç ve belirtilen sayfayı seç
    public static void loadExcel(String filePath, int sheetIndex) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheetAt(sheetIndex);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Belirtilen satır ve sütundaki veriyi oku
    public static String getCellData(int row, int col) {
        Row rowData = sheet.getRow(row);
        return rowData.getCell(col).getStringCellValue();
    }

    // Excel dosyasını kapat
    public static void closeExcel() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
