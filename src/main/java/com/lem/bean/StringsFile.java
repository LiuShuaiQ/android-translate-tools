package com.lem.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 项目下的strings.xml文件
 */
public class StringsFile {
  private File file;
  private String country;
  private String language;
  /**
   * strings.xml中的item
   */
  private List<StringItem> itemList = new ArrayList<>();

  /**
   * strings.xml中item的哈希表，用于快速查找
   */
  private Map<String, StringItem> itemMap = new HashMap<>();

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

  public List<StringItem> getItemList() {
    return itemList;
  }

  public void setItemList(List<StringItem> itemList) {
    this.itemList = itemList;
  }

  public Map<String, StringItem> getItemMap() {
    return itemMap;
  }

  public void setItemMap(List<StringItem> itemList) {
    if (this.itemList != null) {
      this.itemList.forEach(item -> {
        itemMap.put(item.name, item);
      });
    }
  }

  public void setItemMap(Map<String, StringItem> itemMap) {
    this.itemMap = itemMap;
  }

  @Override public String toString() {
    return "StringsFile{" +
        "file=" + file +
        ", country='" + country + '\'' +
        ", language='" + language + '\'' +
        ", itemList=" + itemList +
        '}';
  }
}
