package com.lem.model;

import com.lem.ProjectFile;
import com.lem.bean.StringsFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class TranslateResTask implements Runnable {
  private ProjectFile projectFile;

  private List<StringsResDoc> stringsResDocs;

  public TranslateResTask(ProjectFile projectFile) {
    this.projectFile = projectFile;
  }

  @Override public void run() {

  }
}
