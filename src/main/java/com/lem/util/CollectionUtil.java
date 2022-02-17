package com.lem.util;

import java.util.Collection;

public class CollectionUtil {
  public static boolean isEmpty(Collection collection) {
    if (collection == null || collection.size() <= 0) {
      return true;
    }
    return false;
  }

  public static <T> boolean isEmpty(T[] array) {
    if (array == null || array.length <= 0) {
      return true;
    }
    return false;
  }

  public static <T extends Number> double sumNum(Collection<T> list) {
    double sum = 0;
    for (T v : list) {
      sum += v.doubleValue();
    }
    return sum;
  }

  public static <T extends Number> float sumNumF(Collection<T> list) {
    float sum = 0;
    for (T v : list) {
      sum += v.doubleValue();
    }
    return sum;
  }
}
