package com.quazzom.javis.administrator.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GeneralConfiguration {

  public static final String pathToConfigurationFile = "configuration.properties";

  public enum allValidConfigurationOptions {
    EXECUTION_MODE("EXECUTION_MODE"), // NORMAL e DEMO
    ENVIROMENT_MODE("ENVIRONMENT_MODE"), // PRODUCTION e TEST
    STORAGE("STORAGE"), // SQL_SERVER
    AUTHENTICATION("AUTHENTICATION"), // AD e PROGRAM
    LANGUAGE("LANGUAGE"); // PT-BR ou EN-US

    private final String variableName;

    allValidConfigurationOptions(String variableName) {
      this.variableName = variableName;
    }

    public String getValue() {
      return this.variableName;
    }
  }

  enum ExecutionModeOptions implements HasValue {
    INVALID("?"),
    NORMAL("NORMAL"),
    DEMO("DEMO");

    private final String variableName;

    ExecutionModeOptions(String variableName) {
      this.variableName = variableName;
    }

    @Override
    public String getValue() {
      return this.variableName;
    }
  }

  enum EnvironmentModeOptions implements HasValue {
    INVALID("?"),
    PRODUCTION("PRODUCTION"),
    TEST("TEST");

    private final String variableName;

    EnvironmentModeOptions(String variableName) {
      this.variableName = variableName;
    }

    @Override
    public String getValue() {
      return this.variableName;
    }
  }

  enum StorageTypeOptions implements HasValue {
    INVALID("?"),
    SQL_SERVER("SQL_SERVER");

    private final String variableName;

    StorageTypeOptions(String variableName) {
      this.variableName = variableName;
    }

    @Override
    public String getValue() {
      return this.variableName;
    }
  }

  enum AuthenticationTypeOptions implements HasValue {
    INVALID("?"),
    ACTIVE_DIRECTORY("ACTIVE_DIRECTORY"),
    PROGRAM("PROGRAM");

    private final String variableName;

    AuthenticationTypeOptions(String variableName) {
      this.variableName = variableName;
    }

    @Override
    public String getValue() {
      return this.variableName;
    }
  }

  enum LanguageTypeOptions implements HasValue {
    INVALID("?"),
    EN_US("EN_US"),
    PT_BR("PT_BR");

    private final String variableName;

    LanguageTypeOptions(String variableName) {
      this.variableName = variableName;
    }

    @Override
    public String getValue() {
      return this.variableName;
    }
  }

  private ExecutionModeOptions executionMode;
  private EnvironmentModeOptions environmentMode;
  private StorageTypeOptions storageType;
  private AuthenticationTypeOptions authenticationType;
  private LanguageTypeOptions languageType;

  private InputStream configurationStreamData = null;

  public GeneralConfiguration() {
    this(null);
  }

  public GeneralConfiguration(InputStream configurationStreamData) {
    this.configurationStreamData = configurationStreamData;
  }

  // --------------------------------------------------------------------------------------------

  public ExecutionModeOptions getExecutionMode() {
    return executionMode;
  }

  public void setExecutionMode(String executionMode) throws ConfigurationInvalidValueException {

    isStringOptionEmpty(executionMode, allValidConfigurationOptions.EXECUTION_MODE);

    isStringOptionValid(
        executionMode, allValidConfigurationOptions.EXECUTION_MODE, ExecutionModeOptions.class);

    for (ExecutionModeOptions execution : ExecutionModeOptions.values()) {
      if (execution.getValue().equals(executionMode)) {
        setExecutionMode(execution);
        break;
      }
    }
  }

  public void setExecutionMode(ExecutionModeOptions executionMode) {
    this.executionMode = executionMode;
  }

  // --------------------------------------------------------------------------------------------

  public EnvironmentModeOptions getEnvironmentMode() {
    return environmentMode;
  }

  public void setEnvironmentMode(String environmentMode) throws ConfigurationInvalidValueException {

    isStringOptionEmpty(environmentMode, allValidConfigurationOptions.ENVIROMENT_MODE);

    isStringOptionValid(
        environmentMode,
        allValidConfigurationOptions.ENVIROMENT_MODE,
        EnvironmentModeOptions.class);

    for (EnvironmentModeOptions environment : EnvironmentModeOptions.values()) {
      if (environment.getValue().equals(environmentMode)) {
        setEnvironmentMode(environment);
        break;
      }
    }
  }

  public void setEnvironmentMode(EnvironmentModeOptions environmentMode) {
    this.environmentMode = environmentMode;
  }

  // --------------------------------------------------------------------------------------------

  public StorageTypeOptions getStorageType() {
    return storageType;
  }

  public void setStorageType(String storageType) throws ConfigurationInvalidValueException {

    isStringOptionEmpty(storageType, allValidConfigurationOptions.STORAGE);

    isStringOptionValid(
        storageType, allValidConfigurationOptions.STORAGE, StorageTypeOptions.class);

    for (StorageTypeOptions storage : StorageTypeOptions.values()) {
      if (storage.getValue().equals(storageType)) {
        setStorageType(storage);
        break;
      }
    }
  }

  public void setStorageType(StorageTypeOptions storageType) {
    this.storageType = storageType;
  }

  // --------------------------------------------------------------------------------------------

  public AuthenticationTypeOptions getAuthenticationType() {
    return authenticationType;
  }

  public void setAuthenticationType(String authenticationType)
      throws ConfigurationInvalidValueException {

    isStringOptionEmpty(authenticationType, allValidConfigurationOptions.AUTHENTICATION);

    isStringOptionValid(
        authenticationType,
        allValidConfigurationOptions.AUTHENTICATION,
        AuthenticationTypeOptions.class);

    for (AuthenticationTypeOptions authentication : AuthenticationTypeOptions.values()) {
      if (authentication.getValue().equals(authenticationType)) {
        setAuthenticationType(authentication);
        break;
      }
    }
  }

  public void setAuthenticationType(AuthenticationTypeOptions authenticationType) {
    this.authenticationType = authenticationType;
  }

  // --------------------------------------------------------------------------------------------

  public LanguageTypeOptions getLanguageType() {
    return languageType;
  }

  public void setLanguageType(String languageType) throws ConfigurationInvalidValueException {

    isStringOptionEmpty(languageType, allValidConfigurationOptions.LANGUAGE);

    isStringOptionValid(
        languageType, allValidConfigurationOptions.LANGUAGE, LanguageTypeOptions.class);

    for (LanguageTypeOptions language : LanguageTypeOptions.values()) {
      if (language.getValue().equals(languageType)) {
        setLanguageType(language);
        break;
      }
    }
  }

  public void setLanguageType(LanguageTypeOptions languageType) {
    this.languageType = languageType;
  }

  // --------------------------------------------------------------------------------------------

  public void loadConfigurationFromFile() throws IOException, ConfigurationInvalidValueException {

    if (configurationStreamData == null)
      configurationStreamData = new FileInputStream(pathToConfigurationFile);

    Properties properties = new Properties();
    properties.load(configurationStreamData);

    propertiesContainsAllOptions(properties);

    setAllOptions(properties);
  }

  public void propertiesContainsAllOptions(Properties p) throws IOException {

    for (allValidConfigurationOptions optionKey : allValidConfigurationOptions.values()) {

      String key = optionKey.getValue();

      propertiesContainsTheOption(p, key);
    }
  }

  public void propertiesContainsTheOption(Properties p, String option) throws IOException {

    if (!p.containsKey(option))
      throw new IOException(
          String.format("The configuration file doesn't contains the option: %s", option));
  }

  public void setAllOptionsToInvalid() {
    executionMode = ExecutionModeOptions.INVALID;
    environmentMode = EnvironmentModeOptions.INVALID;
    storageType = StorageTypeOptions.INVALID;
    authenticationType = AuthenticationTypeOptions.INVALID;
    languageType = LanguageTypeOptions.INVALID;
  }

  public boolean isAnyOptionInValid() {
    if (executionMode == ExecutionModeOptions.INVALID
        || environmentMode == EnvironmentModeOptions.INVALID
        || storageType == StorageTypeOptions.INVALID
        || authenticationType == AuthenticationTypeOptions.INVALID
        || languageType == LanguageTypeOptions.INVALID) return true;

    return false;
  }

  public void setAllOptions(Properties propertiesContainingAllOptions)
      throws IOException, ConfigurationInvalidValueException {

    propertiesContainsAllOptions(propertiesContainingAllOptions);

    setExecutionMode(
        propertiesContainingAllOptions.getProperty(
            allValidConfigurationOptions.EXECUTION_MODE.getValue()));
    setEnvironmentMode(
        propertiesContainingAllOptions.getProperty(
            allValidConfigurationOptions.ENVIROMENT_MODE.getValue()));
    setStorageType(
        propertiesContainingAllOptions.getProperty(
            allValidConfigurationOptions.STORAGE.getValue()));
    setAuthenticationType(
        propertiesContainingAllOptions.getProperty(
            allValidConfigurationOptions.AUTHENTICATION.getValue()));
    setLanguageType(
        propertiesContainingAllOptions.getProperty(
            allValidConfigurationOptions.LANGUAGE.getValue()));
  }

  // --------------------------- the private methods ---------------------------

  private void isStringOptionEmpty(String value, allValidConfigurationOptions variable)
      throws ConfigurationInvalidValueException {

    if (value.isEmpty()) {
      throw new ConfigurationInvalidValueException(
          String.format("The option %s cannot be empty", variable.getValue()));
    }
  }

  private <E extends Enum<E> & HasValue> void isStringOptionValid(
      String value, allValidConfigurationOptions variable, Class<E> theEnumWithValidValues)
      throws ConfigurationInvalidValueException {

    for (E valor : theEnumWithValidValues.getEnumConstants()) {
      if (valor.getValue().equals(value)) return;
    }
    throw new ConfigurationInvalidValueException(
        String.format("%s is not a valid value for the %s option", value, variable.getValue()));
  }

  // Interface to ensure Enum has getValue method
  public interface HasValue {
    String getValue();
  }
}
