package com.autotest.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    public static String[][] getExpectationData(String file, String sheetName) {
        try {
            FileInputStream ExcelFile = new FileInputStream(file);//获取Excel文件
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            // 得到工作表名称
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            System.out.println("工作表名称"+sheetName);
            // 得到总行数
            int rowNum = ExcelWSheet.getLastRowNum();
            System.out.println("工作表行数"+rowNum);
            List<String[]> results = new ArrayList<String[]>();
            for (int i = 1; i <= rowNum; i++) {
                // 当前行
                XSSFRow row = ExcelWSheet.getRow(i);
                int colNum = row.getLastCellNum();
                String[] data = new String[colNum];
                // 当前行所有列
                for (int j = 0; j < colNum; j++) {
                    try {
                        data[j] = getCellValue(row.getCell(j));
                        System.out.println("内容"+data[j]);
                    } catch (NullPointerException e) { // 如果单元格为空的时候，则用这个来处理
                        data[j] = "";
                    }
                }
                // 把data[]数组的数据存在list<[]>中
                results.add(data);
            }

            String[][] returnArray = new String[results.size()][rowNum];
            for (int i = 0; i < returnArray.length; i++) {
                returnArray[i] = (String[]) results.get(i);
            }
            return returnArray;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对Excel的各个单元格的格式进行判断并转换
     */
    public static String getCellValue(XSSFCell xssfCell) {
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("#");
        System.out.println("xssfCell.getCellType()"+xssfCell.getCellType());
        switch (xssfCell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = xssfCell.getRichStringCellValue().getString().trim();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = df.format(xssfCell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(xssfCell.getBooleanCellValue()).trim();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = xssfCell.getCellFormula();
                break;

            default:
                cellValue = "";
        }
        return cellValue;
    }




    public static void main(String[] args){
        String file = "/Users/denglulu/git/UITest/resources/testData.xlsx";

        Object[][] records = getExpectationData(file,"transferData");
        //System.out.println(records.length);
    }
}
