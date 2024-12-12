package com.quazzom.javis.administrator.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {

  public static final String CHECK_SUM_ALGORITHM = "SHA-256";

  public String getFileChecksum(File file, String algorithm)
      throws NoSuchAlgorithmException, IOException {
    MessageDigest digest = MessageDigest.getInstance(algorithm);
    try (InputStream is = new FileInputStream(file);
        DigestInputStream dis = new DigestInputStream(is, digest)) {

      byte[] buffer = new byte[1024];
      while (dis.read(buffer) != -1) {}
    }

    byte[] hashBytes = digest.digest();
    StringBuilder sb = new StringBuilder();
    for (byte b : hashBytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }

  public boolean isChecksumValid(String fileChecksum, String configurationChecksum) {
    return fileChecksum.toLowerCase().equals(configurationChecksum.toLowerCase());
  }
}
