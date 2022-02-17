package com.lem.model;

import com.lem.ProjectFile;
import com.lem.bean.StringsFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class TranslateResTask implements Runnable {
  private ProjectFile projectFile;

  private Map<StringsFile, StringsResDoc> fileResMap;

  public TranslateResTask(ProjectFile projectFile) {
    this.projectFile = projectFile;
  }

  @Override public void run() {
    fileResMap = new HashMap<>();
    projectFile.getStringsFiles().forEach(stringsFile -> {
      StringsResDoc resDoc = new StringsResDoc(stringsFile.getFile());
      try {
        resDoc.load();
        fileResMap.put(stringsFile, resDoc);
        System.out.println(fileResMap);
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (SAXException e) {
        e.printStackTrace();
      }
    });
  }
}
