package com.lem;

import java.io.File;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectFileTest {

  @Test
  public void loadFile() {
    File file = new File("./src/test/resources/dir");

    ProjectFile projectFile = new ProjectFile();
    projectFile.loadFile(file);

    assertEquals(file, projectFile.getProjectDir());
  }
}