package com.quazzom.javis.administrator.vnc;

public class ComputerFilter {

  public boolean computerMatchWithString(Computer c, String filter, boolean isCaseSensitive) {
    if (theStringsMatch(c.getPowerStatusStr(), filter, isCaseSensitive)) return true;
    if (theStringsMatch(c.getSessionTypeStr(), filter, isCaseSensitive)) return true;
    if (theStringsMatch(c.getUsername(), filter, isCaseSensitive)) return true;
    if (theStringsMatch(c.getHostname(), filter, isCaseSensitive)) return true;
    if (theStringsMatch(c.getIPStr(), filter, isCaseSensitive)) return true;
    if (theStringsMatch(c.isManualStr(), filter, isCaseSensitive)) return true;
    if (theStringsMatch(c.getLoginDateStr(), filter, isCaseSensitive)) return true;
    if (theStringsMatch(c.getJavisClientVersion(), filter, isCaseSensitive)) return true;
    if (theStringsMatch(c.getDetails(), filter, isCaseSensitive)) return true;
    return false;
  }

  public boolean theStringsMatch(String source, String filter, boolean isCaseSensitive) {
    if (isCaseSensitive) return source.contains(filter);
    return source.toLowerCase().contains(filter.toLowerCase());
  }
}
