package com.quazzom.javis.administrator.configuration;

import java.util.Properties;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.lang.TextNotFoundException;

public class SQLServerConnectionInformations {

  private String serverKey = "SERVER";
  private String portKey = "PORT";
  private String databaseNameKey = "DABASE_NAME";
  private String userKey = "USER";
  private String passWordKey = "PASSWORD";
  private String encryptKey = "ENCRYPT_KEY";
  private String trustServerCertificateKey = "TRUST_SERVER_CERTIFICATE_KEY";

  private String userHardCode = "javis";
  private String passwordHardCode = "9uwroP2lx!p";

  private Properties properties;

  private Text theLanguage;

  public SQLServerConnectionInformations(Properties properties) {
    this(
        properties,
        LanguageFactory.getLanguage(LanguagePathToFile.SQL_SERVER_CONNECTION_INFORMATIONS));
  }

  public SQLServerConnectionInformations(Properties properties, Text theLanguage) {
    this.properties = properties;
    this.theLanguage = theLanguage;
  }

  public String getServer() throws PropertyNotFoundException {

    String value = properties.getProperty(serverKey);

    if (value == null) propertyNotFoundException(serverKey);
    if (value.isEmpty()) propertyEmptyValue(serverKey);

    return value;
  }

  public String getPort() throws PropertyNotFoundException {
    String value = properties.getProperty(portKey);

    if (value == null) propertyNotFoundException(portKey);
    if (value.isEmpty()) propertyEmptyValue(portKey);

    return value;
  }

  public String getDatabaseName() throws PropertyNotFoundException {
    String value = properties.getProperty(databaseNameKey);

    if (value == null) propertyNotFoundException(databaseNameKey);
    if (value.isEmpty()) propertyEmptyValue(databaseNameKey);

    return value;
  }

  public String getUser() {
    String value = properties.getProperty(userKey);

    if (value == null || value.isEmpty()) return userHardCode;

    return value;
  }

  public String getPassWord() {
    String value = properties.getProperty(passWordKey);

    if (value == null || value.isEmpty()) return passwordHardCode;

    return value;
  }

  public String getEncrypt() throws PropertyNotFoundException {
    String value = properties.getProperty(encryptKey);

    if (value == null) propertyNotFoundException(encryptKey);
    if (value.isEmpty()) propertyEmptyValue(encryptKey);

    value = value.toUpperCase();

    checkIfValueIsValid(encryptKey, value, new String[] {"TRUE", "FALSE"});

    return value;
  }

  public String getTrustServerCertificate() throws PropertyNotFoundException {
    String value = properties.getProperty(trustServerCertificateKey);

    if (value == null) propertyNotFoundException(trustServerCertificateKey);
    if (value.isEmpty()) propertyEmptyValue(trustServerCertificateKey);

    value = value.toUpperCase();

    checkIfValueIsValid(trustServerCertificateKey, value, new String[] {"TRUE", "FALSE"});

    return value;
  }

  private void propertyNotFoundException(String property) throws PropertyNotFoundException {
    throw new PropertyNotFoundException(
        theLanguage.getText("PROPERTY_NOT_FOUND_EXCEPTION", property));
  }

  private void propertyEmptyValue(String property) throws PropertyNotFoundException {
    throw new PropertyNotFoundException(
        theLanguage.getText("PROPERTY_NOT_FOUND_EMPTY_STRING_EXCEPTION", property));
  }

  private void checkIfValueIsValid(String property, String value, String[] validValues)
      throws PropertyNotFoundException, TextNotFoundException {
    for (String theValue : validValues) {
      if (value.equals(theValue)) return;
    }
    throw new PropertyNotFoundException(
        theLanguage.getText("PROPERTY_NOT_FOUND_INVALID_VALUE_EXCEPTION", value, property));
  }
}
