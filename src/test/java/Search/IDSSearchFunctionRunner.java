/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

/**
 *
 * @author prabakar
 */
public class IDSSearchFunctionRunner extends DriverSetup {

    XSSFWorkbook workbook = new XSSFWorkbook();

    @Test
    public void searchAction() throws FileNotFoundException, IOException, InterruptedException {

        FileInputStream file = new FileInputStream(new File("D:\\Book13.xlsx"));
        workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        CellStyle style = workbook.createCellStyle();
        Cell cell;
        Row row;

        navigateToLogin();
        LoginAction loginAction = new LoginAction(driver);
        loginAction.enterUserName("maxval");
        loginAction.enterPassword("Qcom2015*");
        TermsAndCondition termsAndConditions = loginAction.loginSubmit();
        Dashboard dashboard = termsAndConditions.Accept();

        int rowStart = Math.min(15, sheet.getFirstRowNum());
        int rowEnd = Math.max(1400, sheet.getLastRowNum());

        for (int rowNum = rowStart + 1; rowNum < rowEnd; rowNum++) {
            row = sheet.getRow(rowNum);
            if (row != null) {
                int columnNumber = 0;
                cell = row.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);
                try {
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        RichTextString fullTextQuery = cell.getRichStringCellValue();
                        String myQuery = fullTextQuery.toString();
                        System.out.println(myQuery);
                        SearchList searchList = new SearchList(driver);
                        if (rowNum == 1) {
                            dashboard.enterFullTextSearchQuery(myQuery);
                            searchList = dashboard.submitFullTextSearchQueryFromDashboard();
                        } else {
                            searchList.enterFullTextSearchQuery(myQuery);
                            searchList.submitFullTextSearchQueryFromSearchList();
                        }
                        String searchRecordList = searchList.getRecordIdInSearchList();
                        cell = row.createCell(2);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        style.setWrapText(true);
                        cell.setCellStyle(style);
                        cell.setCellValue(searchRecordList);
                        cell = row.getCell(1);
                        String expectedResult = cell.getStringCellValue();
                        if (expectedResult.equals(searchRecordList)) {
                            cell = row.createCell(3);
                            cell.setCellValue("Seach result matched with Expected");
                        } else {
                            cell = row.createCell(3);
                            cell.setCellValue("Seach result NOT matched with Expected");
                        }
                        try (FileOutputStream fileOut = new FileOutputStream(
                                "D:\\result.xlsx")) {
                            workbook.write(fileOut);
                        }
                    }
                } catch (AssertionError Ae) {
                }
            }
        }
    }

}
