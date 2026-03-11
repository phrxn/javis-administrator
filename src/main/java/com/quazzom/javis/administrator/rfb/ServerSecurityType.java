package com.quazzom.javis.administrator.rfb;

import java.io.InputStream;
import java.util.List;

public interface ServerSecurityType {
  public List<RFBAuthenticationTypes> decode(InputStream in) throws RFBProtocolException;
}
