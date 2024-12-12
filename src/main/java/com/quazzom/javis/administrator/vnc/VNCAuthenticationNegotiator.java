package com.quazzom.javis.administrator.vnc;

import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public interface VNCAuthenticationNegotiator {

  /*return an empty optional if any error occurs*/
  Optional<List<RFBAuthenticationTypes>> searchListOfAuthenticationTypesInVNCClient();
}
