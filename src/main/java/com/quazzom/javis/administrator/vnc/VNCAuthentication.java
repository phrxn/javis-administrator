package com.quazzom.javis.administrator.vnc;

public class VNCAuthentication implements Cloneable {

  private String password;
  private boolean isToSalveCredential;

  public VNCAuthentication() {
    this("", false);
  }

  public VNCAuthentication(String password, boolean isToSalveCredential) {
    this.password = password;
    this.isToSalveCredential = isToSalveCredential;
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

  public void setToSalveCredential(boolean isToSalveCredential) {
    this.isToSalveCredential = isToSalveCredential;
  }

  @Override
  public VNCAuthentication clone() {
    try {
      return (VNCAuthentication) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}
