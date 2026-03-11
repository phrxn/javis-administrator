package com.quazzom.javis.administrator.vnc;

public class UltraVNCMslogonAuthentication implements Cloneable {

  private String username;
  private String password;
  private boolean isToSalveCredential;

  public UltraVNCMslogonAuthentication() {
    this("", "", false);
  }

  public UltraVNCMslogonAuthentication(
      String username, String password, boolean isToSalveCredential) {
    this.username = username;
    this.password = password;
    this.isToSalveCredential = isToSalveCredential;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isToSalveCredential() {
    return isToSalveCredential;
  }

  public void setIsToSalveCredential(boolean isToSalveCredential) {
    this.isToSalveCredential = isToSalveCredential;
  }

  @Override
  public UltraVNCMslogonAuthentication clone() {
    try {
      return (UltraVNCMslogonAuthentication) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}
