package com.project.qa.ui.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * SpreadSheetUtil for only single row assertion checking
 */
public class SpreadSheetUtil {

    private File spreadsheet;
    private Sheet currentSheet;
    private Map<String, Integer> columns;
    static final Logger LOGGER = LoggerFactory.getLogger(SpreadSheetUtil.class);

    /**
     * Method to given file name
     *
     * @param file
     */
    public SpreadSheetUtil(File file){
        LOGGER.info("excel file from given path: {}", file);
        spreadsheet = file;
        columns = new HashMap();
    }

    /**
     * Method to switch one sheet to another sheet
     *
     * @param name
     */
    public void switchToSheet(String name){
        try(var workbooks = WorkbookFactory.create(spreadsheet)){
            currentSheet = workbooks.getSheet(name);
            currentSheet.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to read excel test data file and return it into two dimensional array
     *
     * @param column
     * @param row
     * @return get cell data as string value
     */
    public String getCellData(String column, int row){
        var dataRow = currentSheet.getRow(row);
        return getCellDataAsString(dataRow.getCell(columns.get(column)));
    }

    /**
     * Method to read excel test data file and return it into two dimensional array
     *
     * @param cell
     * @return cell type wise data return
     */
    private String getCellDataAsString(Cell cell){
        if (cell.getCellType() == CellType.STRING){
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC){
            return String.valueOf((int)cell.getNumericCellValue());
        } else if(cell.getCellType() == CellType.BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }
        return "";
    }
}
