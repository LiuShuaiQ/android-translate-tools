package com.lem.model;

import com.lem.ArgsConfig;
import com.lem.ProjectFile;
import com.lem.bean.StringItem;
import com.lem.bean.StringsFile;
import com.lem.util.TextUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class TranslateResTask implements Runnable {
  private ArgsConfig config;

  private ProjectFile projectFile;

  /**
   * res/values/strings.xml value item name list
   */
  private List<String> valueNames = new ArrayList<>();

  public TranslateResTask(ArgsConfig config, ProjectFile projectFile) {
    this.config = config;
    this.projectFile = projectFile;
  }

  @Override public void run() {
    loadStringsFile();

    if (valueNames.isEmpty()) {
      System.out.println("project is not have default values/strings.xml file");
      return;
    }

    System.out.println("start write data to excel ...");
    File file = config.getOutputFile();
    if (file == null) {
      file = new File("translate.xlsx");
    }
    ExcelWriter.exportDataToFile(transData(), file, "datas");
    System.out.println("start write data to excel finish!");
  }

  private void loadStringsFile() {
    projectFile.getStringsFiles().forEach(stringsFile -> {
      StringsContentLoader resDoc = new StringsContentLoader(stringsFile);
      try {
        resDoc.load();
        if (TextUtil.isEmpty(stringsFile.getLanguage()) && TextUtil.isEmpty(stringsFile.getCountry())) {
          valueNames.clear();
          for (StringItem stringItem : stringsFile.getItemList()) {
            if (stringItem.translatable) {
              valueNames.add(stringItem.name);
            }
          }
        }
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (SAXException e) {
        e.printStackTrace();
      }
    });
  }

  private List<List<String>> transData() {
    List<List<String>> datas = new ArrayList<>();
    for (int i = 0; i < valueNames.size() + 1; i++) {
      List<String> row = new ArrayList<>();
      if (i == 0) {
        // first row set file country and language name
        row.add("");
      } else {
        // other row set strings value name
        row.add(valueNames.get(i - 1));
      }
      for (StringsFile stringsFile : projectFile.getStringsFiles()) {
        if (i == 0) {
          // first row set file country and language name
          StringBuilder lanAndCountry = new StringBuilder();
          if (!TextUtil.isEmpty(stringsFile.getLanguage())) {
            lanAndCountry.append(stringsFile.getLanguage());
          }
          if (!TextUtil.isEmpty(stringsFile.getCountry())) {
            lanAndCountry.append(stringsFile.getCountry());
          }
          if (lanAndCountry.length() <= 0) {
            row.add("default");
          } else {
            row.add(lanAndCountry.toString());
          }
          continue;
        }
        // other row set strings value name
        StringItem item = stringsFile.getItemMap().get(valueNames.get(i - 1));
        if (item == null) {
          row.add(" ");
        } else {
          row.add(item.value);
        }
      }
      datas.add(row);
    }
    return datas;
  }
}
