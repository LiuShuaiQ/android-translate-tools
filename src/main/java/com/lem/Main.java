package com.lem;

import com.lem.model.TranslateResTask;
import com.lem.util.TextUtil;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

class Main {

  public static void main(String[] args) throws ParseException {
    CommandLineParser clParser = new BasicParser();
    Options options = new Options();
    options.addOption("h", "help", false, "Print this usage information.");
    options.addOption("v", "version", false, "Print version information.");
    options.addOption("d", "dir", true, "Set translate resource dir");

    CommandLine commandLine = clParser.parse(options, args);

    if (commandLine.hasOption("h") || commandLine.getOptions() == null || commandLine.getOptions().length <= 0) {
      // print usage
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("TranslateTool", options);
      System.out.println();
      return;
    }

    if (commandLine.hasOption("v")) {
      System.out.println("v0.1");
      return;
    }

    // file path
    String dir = commandLine.getOptionValue("d");
    if (TextUtil.isEmpty(dir)) {
      System.out.println("Option '-d.--dir' is not have");
      return;
    }
    File dirFile = new File(dir);
    if (!dirFile.isDirectory()) {
      System.out.println("dir path " + dir + " is not directory");
    }
    ProjectFile projectFile = new ProjectFile(dirFile);
    projectFile.loadFile();
    // 处理project文件
    List<Runnable> tasks = new ArrayList<>();
    tasks.add(new TranslateResTask(projectFile));
    for (Runnable task : tasks) {
      System.out.println(":" + task.getClass().getSimpleName());
      task.run();
    }
  }
}
