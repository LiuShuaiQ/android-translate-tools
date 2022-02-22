package com.lem.bean;

public class StringItem {
  public String name;
  public String value;
  public boolean translatable;

  public StringItem(String name, String value, boolean translatable) {
    this.name = name;
    this.value = value;
    this.translatable = translatable;
  }

  @Override public String toString() {
    return "StringItem{" +
        "name='" + name + '\'' +
        ", value='" + value + '\'' +
        ", translatable=" + translatable +
        '}';
  }
}
