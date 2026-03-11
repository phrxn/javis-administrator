package com.quazzom.javis.administrator;

public class Main {

  private Start startTheProgram;

  public Main() {
    startTheProgram = new Start();
    startTheProgram.startProgram();
  }

  public static void main(String[] args) {
    new Main();
  }
}
