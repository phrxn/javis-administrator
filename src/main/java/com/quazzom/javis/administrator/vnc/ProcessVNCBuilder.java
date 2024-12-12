package com.quazzom.javis.administrator.vnc;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class ProcessVNCBuilder {

  private VNCProgram vNCProgram;

  public ProcessVNCBuilder(VNCProgram vNCProgram) {
    this.vNCProgram = vNCProgram;
  }

  public void lockProgramVNCExecutableFile() {

    try {

      // Get a file channel for the file
      File file = new File(vNCProgram.pathToExecutable());
      FileChannel channel = new RandomAccessFile(file, "rw").getChannel();

      // Use the file channel to create a lock on the file.
      // This method blocks until it can retrieve the lock.
      FileLock lock = channel.lock();

      // Release the lock - if it is not null!
      if (lock != null) {
        lock.release();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void executeVNCProgram() {

    // Cria o ProcessBuilder
    ProcessBuilder processBuilder = new ProcessBuilder(vNCProgram.pathToExecutable());

    try {
      // Inicia o processo
      Process process = processBuilder.start();

      // Espera o processo terminar (opcional)
      process.waitFor();
      System.out.println("O processo foi concluído.");
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
