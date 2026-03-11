package com.quazzom.javis.administrator.rfb;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ServerMessageError {

  public String decodeMessageError(InputStream input) throws RFBProtocolException {
    try {
      DataInputStream dataInput = new DataInputStream(input);
      int errorMessageLength = dataInput.readInt();
      byte[] errorMessageBytes = new byte[errorMessageLength];
      dataInput.readFully(errorMessageBytes);
      return new String(errorMessageBytes, Charset.forName("US-ASCII"));
    } catch (IOException e) {
      throw new RFBProtocolException(e.getMessage());
    }
  }
}
