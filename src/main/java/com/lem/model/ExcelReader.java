package com.lem.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
  private static Logger logger = Logger.getLogger(ExcelReader.class.getName());
  private static final String XLS = "xls";
  private static final String XLSX = "xlsx";

  public static List<List<String>> importFileToData(File excel, String sheet) {
    List<List<String>> result = new ArrayList<>();
    if (excel == null || !excel.exists()) {
      return result;
    }
    if (!excel.isFile()) {
      return result;
    }
    Workbook workbook = null;
    FileInputStream inputStream = null;
    String fileName = excel.getName();

    try {
      String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

      inputStream = new FileInputStream(excel);
      workbook = getWorkbook(inputStream, fileType);

      // 读取excel中的数据
      List<List<String>> resultDataList = parseExcel(workbook, sheet);

      return resultDataList;
    } catch (Exception e) {
      logger.warning("parse excel file error, file name: " + fileName + " error info:" + e.getMessage());
      return null;
    } finally {
      try {
        if (null != workbook) {
          workbook.close();
        }
        if (null != inputStream) {
          inputStream.close();
        }
      } catch (Exception e) {
        logger.warning("close stream error: " + e.getMessage());
        return null;
      }
    }
  }

  private static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
    Workbook workbook = null;
    if (fileType.equalsIgnoreCase(XLS)) {
      workbook = new HSSFWorkbook(inputStream);
    } else if (fileType.equalsIgnoreCase(XLSX)) {
      workbook = new XSSFWorkbook(inputStream);
    }
    return workbook;
  }

  private static List<List<String>> parseExcel(Workbook workbook, String sheetName) {
    List<List<String>> result = new ArrayList<>();
    for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
      Sheet sheet = workbook.getSheetAt(sheetNum);
      if (sheet == null) {
        continue;
      }
      if (sheetName != null && !sheetName.equals(sheet.getSheetName())) {
        continue;
      }

      int firstRowNum = sheet.getFirstRowNum();
      Row firstRow = sheet.getRow(firstRowNum);
      if (null == firstRow) {
        continue;
      }

      int rowStart = firstRowNum;
      int rowEnd = sheet.getPhysicalNumberOfRows();
      for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
        Row row = sheet.getRow(rowNum);

        if (null == row) {
          continue;
        }
        List<String> rowListData = new ArrayList<>();

        int first = row.getFirstCellNum();
        int end = row.getPhysicalNumberOfCells();
        for (int i = first; i < end; i++) {
          rowListData.add(row.getCell(i).getStringCellValue());
        }

        result.add(rowListData);
      }
    }
    return result;
  }
}
