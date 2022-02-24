package com.lem.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextUtilTest {

  @Test
  public void testTextEmpty() {
    assertTrue(TextUtil.isEmpty(null));
    assertTrue(TextUtil.isEmpty(""));
    assertTrue(TextUtil.isEmpty(new String()));
  }

  @Test
  public void testTextNotEmpty() {
    assertFalse(TextUtil.isEmpty("1"));
    assertFalse(TextUtil.isEmpty("null"));
    assertFalse(TextUtil.isEmpty("0"));
    assertFalse(TextUtil.isEmpty(" "));
    assertFalse(TextUtil.isEmpty("    "));
    assertFalse(TextUtil.isEmpty("123abc"));
  }

}