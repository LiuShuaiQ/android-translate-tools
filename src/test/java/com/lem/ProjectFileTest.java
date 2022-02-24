package com.lem;

import com.lem.bean.StringsFile;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProjectFileTest {

  @Test
  public void loadFile() {
    File file = new File("src/test/resources/res");

    ProjectFile projectFile = new ProjectFile();
    projectFile.loadFile(file);

    assertEquals(file, projectFile.getProjectDir());

    List<StringsFile> files = projectFile.getStringsFiles();
    assertEquals(5, files.size());
  }
}