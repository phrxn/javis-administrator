package com.quazzom.javis.administrator.uncaught_exceptions;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.quazzom.javis.administrator.lang.LanguageInMemory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class OutputStreamUncaughtException implements UncaughtExceptionListener {

  private OutputStream outputStream;

  private Text textLanguage;

  public OutputStreamUncaughtException(OutputStream outputStream) {
    this(outputStream, new LanguageInMemory(LanguagePathToFile.OUTPUT_STREAM_UNCAUGHT_EXCEPTION));
  }

  public OutputStreamUncaughtException(OutputStream outputStream, Text textLanguage) {
    this.outputStream = outputStream;
    this.textLanguage = textLanguage;
  }

  @Override
  public void showUncaughtException(InformationAboutUncaughtException iaue) {

    OutputStreamWriter osw = new OutputStreamWriter(outputStream);
    BufferedWriter bw = new BufferedWriter(osw);

    try {
      bw.write(createExceptionMessageHeader(iaue));
      bw.flush();
    } catch (Exception e) {
      e.printStackTrace();
      /** don't call e.printStackTrace() or anything else */
    }
  }

  public String createExceptionMessageHeader(InformationAboutUncaughtException iaue) {

    StringBuilder sbTmpMessage = new StringBuilder();
    sbTmpMessage
        .append("\n")
        .append(textLanguage.getText("PROGRAM").replaceAll(":$", ": "))
        .append(iaue.getStrProgramName())
        .append("\n")
        .append(textLanguage.getText("VERSION").replaceAll(":$", ": "))
        .append(iaue.getStrProgramVersion())
        .append("\n")
        .append(textLanguage.getText("THREAD_ID").replaceAll(":$", ": "))
        .append(iaue.getStrThreadID())
        .append("\n")
        .append(textLanguage.getText("THREAD_NAME").replaceAll(":$", ": "))
        .append(iaue.getStrThreadName())
        .append("\n")
        .append(textLanguage.getText("STACK_TRACE").replaceAll(":$", ": "))
        .append("\n")
        .append(iaue.getStrStackTrace().replaceAll("^", "\t").replaceAll("\n", "\n\t"))
        .append("\n\n");
    return sbTmpMessage.toString();
  }
}
