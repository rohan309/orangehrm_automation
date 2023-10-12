package com.orangehrm_automation;

import java.util.ArrayList;
import java.util.Arrays;

public class Sample {
    public static void main(String[] args) {
       /* String filePath = "D:\\DataBook.xlsx";
        String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
        System.out.println("File Extension: " + fileExtension);
        Workbook workbook = null;
        try {

            FileInputStream inputStream = new FileInputStream(filePath);
            if (fileExtension.equals("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (fileExtension.equals("xls")) {
                workbook = new HSSFWorkbook(inputStream);

            }
            Sheet sheet = workbook.getSheet("Sheet1"); // Get the first sheet
            int rows = sheet.getPhysicalNumberOfRows();
            int columns = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println(rows + "  " + columns);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    CellType type = cell.getCellType();
                    if (type.equals(CellType.NUMERIC)) {
                        System.out.println(cell.getNumericCellValue());
                    } else if (type.equals(CellType.STRING)) {
                        System.out.println(cell.getStringCellValue());
                    }
                }
            }
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }
}
