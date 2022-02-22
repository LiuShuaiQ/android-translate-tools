package com.lem.model;

import com.lem.bean.StringsFile;
import com.lem.util.CollectionUtil;
import com.lem.util.TextUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelDataExport {

  public static void exportDataToFile(List<List<String>> datas, File file, String sheetName) {
    SXSSFWorkbook workbook = new SXSSFWorkbook();
    Sheet sheet = workbook.createSheet(sheetName);

    for (int i = 0; i < datas.size(); i++) {
      Row row = sheet.createRow(i);
      for (int j = 0; j < datas.get(i).size(); j++) {
        Cell cell = row.createCell(j);
        cell.setCellValue(datas.get(i).get(j));
      }
    }

    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(file);
      workbook.write(fos);
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
