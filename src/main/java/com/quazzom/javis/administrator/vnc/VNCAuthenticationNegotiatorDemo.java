package com.quazzom.javis.administrator.vnc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;

public class VNCAuthenticationNegotiatorDemo implements VNCAuthenticationNegotiator {

  @Override
  public Optional<List<RFBAuthenticationTypes>> searchListOfAuthenticationTypesInVNCClient() {

    List<RFBAuthenticationTypes> listOfAuthenticationTypes =
        new ArrayList<RFBAuthenticationTypes>();

    int randomAuthenticationType = new Random().nextInt(RFBAuthenticationTypes.values().length);

    listOfAuthenticationTypes.add(RFBAuthenticationTypes.values()[randomAuthenticationType]);

    return Optional.of(listOfAuthenticationTypes);
  }
}
