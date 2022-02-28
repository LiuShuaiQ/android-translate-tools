package com.lem.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ExcelDataExportTest {

  @Test
  public void exportDataToFile() {
    List<List<String>> datas = new ArrayList<>();
    datas.add(Arrays.asList("1", "2", "3", "4"));
    ExcelWriter.exportDataToFile(datas, new File("test.xlsx"), "datas");
  }
}