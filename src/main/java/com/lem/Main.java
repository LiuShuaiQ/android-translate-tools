package com.lem;

import com.lem.model.TranslateResTask;
import com.lem.util.TextUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

  public static void main(String[] args) throws ParseException {
    CommandLineContext context = new CommandLineContext();
    context.parse(args);
    if (context.exec()) {
      new Main().launchTask(context);
    }
  }

  private void launchTask(CommandLineContext ctx) {
    ProjectFile projectFile = new ProjectFile();
    projectFile.loadFile(ctx.dir);

    // 处理project文件
    List<Runnable> tasks = new ArrayList<>();
    tasks.add(new TranslateResTask(ctx, projectFile));
    for (Runnable task : tasks) {
      System.out.println(":" + task.getClass().getSimpleName());
      task.run();
    }
  }
}
