package com.lem.util;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectionUtilTest {

  @Test
  public void isEmpty() {
    assertTrue(CollectionUtil.isEmpty((Collection) null));
    assertTrue(CollectionUtil.isEmpty(new ArrayList()));
    assertFalse(CollectionUtil.isEmpty(Arrays.asList(1, 2, 3)));
  }

  @Test
  public void testIsEmpty() {
    Integer[] datas = new Integer[]{1,2,3,4};
    assertTrue(CollectionUtil.isEmpty(new Object[0]));
    assertFalse(CollectionUtil.isEmpty(new Object[3]));
    assertFalse(CollectionUtil.isEmpty(datas));
  }

  @Test
  public void sumNum() {
    Integer[] datas = new Integer[]{1,2,3,4};
    assertEquals(10, CollectionUtil.sumNum(Arrays.asList(datas)), 0);
  }

  @Test
  public void sumNumF() {
    Double[] datas = new Double[]{1.0,2.9,3.1,4.2};
    assertEquals(11.2, CollectionUtil.sumNum(Arrays.asList(datas)), 0);
  }
}