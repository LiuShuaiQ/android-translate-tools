package com.lem;

import com.lem.bean.StringsFile;
import com.lem.util.CollectionUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjectFile {
  public static final String PREFIX_VALUE_DIR = "values-";

  private File projectDir;

  private List<StringsFile> stringsFiles;

  public ProjectFile() {
  }

  public void loadFile(File projectDir) {
    this.projectDir = projectDir;
    if (this.projectDir == null) {
      throw new IllegalArgumentException("project dir is null");
    }
    if (!this.projectDir.isDirectory()) {
      throw new IllegalArgumentException("project dir is illegal");
    }

    String fileName;
    stringsFiles = new ArrayList<>();
    for (File file : projectDir.listFiles()) {
      fileName = file.getName();
      if ("values".equals(fileName) || fileName.startsWith(PREFIX_VALUE_DIR)) {
        String language = null, country = null;
        if (!"values".equals(fileName)) {
          String fileDes = fileName.substring(PREFIX_VALUE_DIR.length());

          String[] names = fileDes.split("-");
          if (CollectionUtil.isEmpty(names)) {
            continue;
          } else if (names.length == 1) {
            language = names[0];
            country = null;
          } else if (names.length == 2) {
            language = names[0];
            if (names[1].startsWith("r")) {
              country = names[1].substring(1);
            } else {
              country = null;
            }
          } else {
            continue;
          }
        } else {

        }

        if (file.isDirectory()) {
          for (File subFile : file.listFiles()) {
            if (subFile.isFile() && "strings.xml".equals(subFile.getName())) {
              //找到strings.xml
              stringsFiles.add(new StringsFile(subFile, country, language));
            }
          }
        }
      }
    }
  }

  public File getProjectDir() {
    return projectDir;
  }

  public List<StringsFile> getStringsFiles() {
    return stringsFiles;
  }
}
