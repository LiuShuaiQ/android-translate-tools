package com.lem.bean;

import java.io.File;

/**
 * 项目下的strings.xml文件
 */
public class StringsFile {
  private File file;
  private String country;
  private String language;

  public StringsFile(File file, String country, String language) {
    this.file = file;
    this.country = country;
    this.language = language;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override public String toString() {
    return "StringsFile{" +
        "file=" + file +
        ", country='" + country + '\'' +
        ", language='" + language + '\'' +
        '}';
  }
}
