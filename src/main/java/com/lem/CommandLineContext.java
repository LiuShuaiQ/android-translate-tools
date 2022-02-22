package com.lem;

import com.lem.util.TextUtil;
import java.io.File;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineContext implements ArgsConfig {
  private CommandLineParser cmdParser;
  private Options cmdLineOps;
  private CommandLine cmd;

  public File dir;
  public File output;

  public CommandLineContext() {
    cmdParser = new BasicParser();
    cmdLineOps = loadOptions();
  }

  public CommandLine parse(String[] arguments) throws ParseException {
    cmd = cmdParser.parse(cmdLineOps, arguments);
    return cmd;
  }

  public boolean exec() {
    return exec(this.cmd);
  }

  public boolean exec(CommandLine cmd) {
    if (cmd == null) {
      return false;
    }
    if (cmd.hasOption("h") || cmd.getOptions() == null || cmd.getOptions().length <= 0) {
      // print usage
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("TranslateTool", loadOptions());
      System.out.println();
      return false;
    }

    if (cmd.hasOption("v")) {
      System.out.println("v0.1");
      return false;
    }
    return loadCommand();
  }

  public boolean loadCommand() {
    // project file path
    String dirPath = cmd.getOptionValue("d");
    if (TextUtil.isEmpty(dirPath)) {
      System.out.println("Option '-d.--dir' is not have");
      return false;
    }
    dir = new File(dirPath);
    if (!dir.isDirectory()) {
      System.out.println("dir path " + dir + " is not directory");
    }
    // output file
    String outputPath = cmd.getOptionValue("o");
    if (TextUtil.isEmpty(outputPath)) {
      output = null;
    } else {
      output = new File(outputPath);
    }
    return true;
  }

  public Options loadOptions() {
    Options options = new Options();
    options.addOption("h", "help", false, "Print this usage information.");
    options.addOption("v", "version", false, "Print version information.");
    options.addOption("d", "dir", true, "Set translate resource dir");
    options.addOption("o", "output", true, "Set output file path");
    return options;
  }

  @Override public File getProjectDir() {
    return dir;
  }

  @Override public File getOutputFile() {
    return output;
  }
}
