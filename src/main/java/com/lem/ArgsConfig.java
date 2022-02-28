package com.lem;

import java.io.File;

public interface ArgsConfig {
  boolean isDecode();
  File getProjectDir();
  File getOutputFile();
}
