package com.project.qa.ui.readers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    static final Logger LOGGER = LoggerFactory.getLogger(ExcelReader.class);
    private String excelFilePath;

    public ExcelReader(String excelFileName) {
        LOGGER.info("reading excel file from given location {}", excelFileName);
        excelFilePath = this.getClass().getClassLoader().getResource(excelFileName).getPath();
    }

    public ExcelReader() {
        String excelFileName = new ConfigFileReader().getDefaultExcelFilePath();
        LOGGER.info("reading excel file from default file location {}", excelFileName);
        excelFilePath = this.getClass().getClassLoader().getResource(excelFileName).getPath();
    }

    /**
     * Method to read excel test data file and return it into two dimensional array
     *
     * @param sheetName
     * @return two dimensional array
     */
    public String[][] getExcelFileData(String sheetName) {
        String[][] excelData = null;

        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook;
            if (excelFilePath.endsWith(".xls") || excelFilePath.endsWith(".xlsx")) workbook = new HSSFWorkbook(fileInputStream);
            else workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            int totalRows = sheet.getLastRowNum() + 1;
            int totalColumns = sheet.getRow(0).getLastCellNum();
            excelData = new String[totalRows][totalColumns];
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < totalColumns; j++) {
                    Cell cell = row.getCell(j);
                    switch (cell.getCellType()) {
                        case STRING:
                            excelData[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            excelData[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            excelData[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
                            break;
                        default:
                            LOGGER.error("can't read cell value from excel file");
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("error while reading excel file {}", e.getMessage());
        }
        return excelData;
    }
}
