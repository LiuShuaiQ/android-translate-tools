package com.lem;

import java.awt.*;

/**
 * 屏幕工具类
 */
public class ScreenUtil {

  public static int getScreenWidth() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    return screenWidth;
  }

  public static int getScreenHeight() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = (int) screenSize.getHeight();
    return screenHeight;
  }
}
