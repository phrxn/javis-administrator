package com.quazzom.javis.administrator.rfb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class ProtocolVersion {

  private final int major;
  private final int minor;
  private Text theLanguage;
  private BufferedReader br = null;

  public ProtocolVersion() {
    this(-1, -1);
  }

  public ProtocolVersion(int major, int minor) {
    this(major, minor, LanguageFactory.getLanguage(LanguagePathToFile.PROTOCOL_VERSION));
  }

  public ProtocolVersion(int major, int minor, Text theLanguage) {
    this.major = major;
    this.minor = minor;
    this.theLanguage = theLanguage;
  }

  public int getMajor() {
    return major;
  }

  public int getMinor() {
    return minor;
  }

  public void encode(OutputStream out) throws IOException {
    out.write(String.format("RFB %03d.%03d\n", major, minor).getBytes(Charset.forName("US-ASCII")));
  }

  public boolean equals(int major, int minor) {
    return this.major == major && this.minor == minor;
  }

  public boolean atLeast(int major, int minor) {
    return this.major >= major && this.minor >= minor;
  }

  public ProtocolVersion decode(InputStream in) throws RFBProtocolException {

    String theProtolString = null;

    if (br == null) br = new BufferedReader(new InputStreamReader(in));

    try {
      theProtolString = br.readLine();
    } catch (SocketTimeoutException ex) {
      throw new RFBProtocolException(theLanguage.getText("SOCKET_TIMEOUT_EXCEPTION"));
    } catch (IOException ex) {
      throw new RFBProtocolException(
          theLanguage.getText("PROTOCOL_DECODE_EXCEPTION", ex.getMessage()));
    }

    Pattern protocolVersionPatter = Pattern.compile("RFB (\\d{3})\\.(\\d{3})");

    Matcher matcher = protocolVersionPatter.matcher(theProtolString);
    if (matcher.matches()) {
      String major = matcher.group(1);
      String minor = matcher.group(2);
      return new ProtocolVersion(Integer.parseInt(major), Integer.parseInt(minor));
    } else {
      throw new RFBProtocolException(
          theLanguage.getText("PROTOCOL_SERVER_EXCEPTION", theProtolString));
    }
  }

  public String getVersion() {
    return String.format("%03d.%03d", major, minor);
  }

  public void setBufferReader(BufferedReader br) {
    this.br = br;
  }
}
