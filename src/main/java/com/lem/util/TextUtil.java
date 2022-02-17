package com.lem.util;

public class TextUtil {
  public static boolean isEmpty(String text) {
    if (text == null || text.isEmpty()) {
      return true;
    }
    return false;
  }
}
