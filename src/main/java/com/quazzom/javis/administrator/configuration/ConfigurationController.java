package com.quazzom.javis.administrator.configuration;

public class ConfigurationController {

  private GeneralConfiguration generalConfiguration;
  private ConfigurationViewer configurationViewer;

  public ConfigurationController(ConfigurationViewer configurationViewer) {
    this(new GeneralConfiguration(), configurationViewer);
  }

  public ConfigurationController(
      GeneralConfiguration generalConfiguration, ConfigurationViewer configurationViewer) {
    this.generalConfiguration = generalConfiguration;
    this.configurationViewer = configurationViewer;
  }

  /**
   * load the configuration file
   *
   * @return <code>true</code> if the file was loaded, othewise <code>false<code> is returned.
   */
  public boolean loadConfigurationFile() {
    try {
      generalConfiguration.loadConfigurationFromFile();
      return true;
    } catch (Exception e) {
      configurationViewer.showErrorLoadingConfigurationFile(e.getMessage());
    }
    return false;
  }
}
