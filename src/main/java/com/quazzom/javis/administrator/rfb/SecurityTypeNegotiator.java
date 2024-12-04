package com.quazzom.javis.administrator.rfb;

import java.util.List;

public class SecurityTypeNegotiator {

  public List<RFBAuthenticationTypes> negotiate(RFBSession session) throws RFBProtocolException {

    ServerSecurityType sst;

    if (session.getProtocolVersion().equals(3, 3)) {
      sst = new ServerSecurityTypeProtocolVersion3_3();
    } else {
      sst = new ServerSecurityTypeProtocolVersions();
    }
    return sst.decode(session.getInputStream());
  }
}
