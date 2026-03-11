package com.quazzom.javis.administrator.gui.controllers;

import java.util.List;
import java.util.Optional;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.dialog.JDialogYesCancelOption;
import com.quazzom.javis.administrator.gui.internal_frame.VNCComputerListInternalFrame;
import com.quazzom.javis.administrator.io.VNCProcessCreator;
import com.quazzom.javis.administrator.io.VNCProcessCreatorSwitch;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.persistence.ComputerPersistence;
import com.quazzom.javis.administrator.persistence.ComputerPersistenceSwitch;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.persistence.VNCProgramConfigurationPersistence;
import com.quazzom.javis.administrator.persistence.VNCProgramConfigurationPersistenceSwitch;
import com.quazzom.javis.administrator.rfb.RFBAuthenticationTypes;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.ComputerConnectionInformations;
import com.quazzom.javis.administrator.vnc.ComputerConnectionInformationsException;
import com.quazzom.javis.administrator.vnc.CreateParametersToExecutableSwitch;
import com.quazzom.javis.administrator.vnc.VNCAuthenticationNegotiatorSwitch;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;

public class VNCComputerListInternalFrameController extends InternalFrameController {

  private VNCComputerListInternalFrame vNCComputerListInternalFrame;
  private ComputerPersistence computerPersistence;
  private VNCProgramConfigurationPersistence vncProgramConfigurationPersistence;
  private Text theLanguage;

  public VNCComputerListInternalFrameController(
      GeneralConfiguration generalConfiguration, SwingMediator swingMediator) {
    super(generalConfiguration, swingMediator);
    this.computerPersistence = new ComputerPersistenceSwitch(generalConfiguration);
    this.theLanguage =
        LanguageFactory.getLanguage(LanguagePathToFile.VNC_COMPUTER_LIST_INTERNAL_FRAME_CONTROLLER);
    this.vncProgramConfigurationPersistence =
        new VNCProgramConfigurationPersistenceSwitch(generalConfiguration);
  }

  @Override
  public void showInternalFrame() {

    if (swingMediator.doesTheInternalFrameAlreadyExist(vNCComputerListInternalFrame)) return;

    List<Computer> listOfComputers;

    try {
      listOfComputers = computerPersistence.findAll();
    } catch (PersistenceException e) {
      String textTitle = theLanguage.getText("CREATE_COMPUTER_LIST_EXCEPTION");
      swingMediator.showMessageToUser(JDialogType.ERROR, textTitle, e.getMessage());
      return;
    }

    vNCComputerListInternalFrame =
        new VNCComputerListInternalFrame(
            swingMediator.getJFrameAdministratorFrame(), swingMediator.getJDesktopPane(), this);
    vNCComputerListInternalFrame.showComputerList(listOfComputers);

    swingMediator.addInternalFrame(vNCComputerListInternalFrame);
  }

  public void showJDialogComputerCreate() {
    swingMediator.showJDialogComputerCreate(this);
  }

  public void showJDialogComputerUpdate(Computer computerToUpdate) {
    swingMediator.showJDialogComputerUpdate(this, computerToUpdate);
  }

  public void createComputer(Computer computer) {
    try {
      Computer newComputerWithId = computerPersistence.createComputer(computer);
      vNCComputerListInternalFrame.createComputer(newComputerWithId);
    } catch (PersistenceException e) {
      String textTitle = theLanguage.getText("CREATE_COMPUTER_EXCEPTION");
      swingMediator.showMessageToUser(JDialogType.ERROR, textTitle, e.getMessage());
      vNCComputerListInternalFrame.dispose();
    }
  }

  public void updateComputer(Computer computer) {
    try {
      computerPersistence.updateComputer(computer);
      vNCComputerListInternalFrame.updateComputer(computer);
    } catch (PersistenceException e) {
      String textTitle = theLanguage.getText("UPDATE_COMPUTER_EXCEPTION");
      swingMediator.showMessageToUser(JDialogType.ERROR, textTitle, e.getMessage());
    }
  }

  public void deleteComputer(Computer computer) {

    try {
      boolean computerDeleted = computerPersistence.deleteComputer(computer);
      if (computerDeleted) {
        vNCComputerListInternalFrame.deleteComputer(computer);
      }
    } catch (PersistenceException e) {
      String textTitle = theLanguage.getText("DELETE_COMPUTER_EXCEPTION");
      swingMediator.showMessageToUser(JDialogType.ERROR, textTitle, e.getMessage());
    }
  }

  public void vNCAccessTheComputerOnlyToView(Computer computer) {
    createConnectionWithVNCClient(computer, true);
  }

  public void vNCAccessTheComputerAndInteract(Computer computer) {
    createConnectionWithVNCClient(computer, false);
  }

  public void vNCOpenScreenToConnection() {
    ComputerConnectionInformations cci;
    try {
      cci = new ComputerConnectionInformations("", 0, false);
      createConnectionWithVNCClient(cci);
    } catch (TCPPortException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private void createConnectionWithVNCClient(Computer computer, boolean isOnlyView) {

    ComputerConnectionInformations cci;
    try {
      cci = new ComputerConnectionInformations(computer.getHostname(), 0, isOnlyView);
      createConnectionWithVNCClient(cci);
    } catch (TCPPortException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private void createConnectionWithVNCClient(ComputerConnectionInformations computer) {

    Optional<VNCProgramConfiguration> optionalVncConfiguration;

    try {
      optionalVncConfiguration = vncProgramConfigurationPersistence.findDefaultConfiguration();
    } catch (PersistenceException e) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR, theLanguage.getText("FIND_VNC_CONFIGURATION_ERROR"), e.getMessage());
      return;
    }

    if (optionalVncConfiguration.isEmpty()) {
      swingMediator.showMessageToUser(
          JDialogType.ERROR,
          theLanguage.getText("WITHOUT_VNC_CONFIGURATION_ERROR"),
          theLanguage.getText("WITHOUT_VNC_CONFIGURATION_ERROR_MESSAGE"));
      return;
    }

    VNCProgramConfiguration vncProgramConfig =
        optionalVncConfiguration.orElse(new VNCProgramConfiguration());

    try {
      computer.setPort(vncProgramConfig.getDefaultPortToAccess());
    } catch (ComputerConnectionInformationsException | TCPPortException e) {
      throw new RuntimeException(e);
    }

    if (swingMediator.showJDialogVNCComputer(computer) == JDialogYesCancelOption.CANCEL) {
      return;
    }

    //

    VNCAuthenticationNegotiatorSwitch vncAuthenticationNegotiator =
        new VNCAuthenticationNegotiatorSwitch(
            generalConfiguration, computer, vncProgramConfig, swingMediator);

    Optional<List<RFBAuthenticationTypes>> optionalAuthenticationList =
        vncAuthenticationNegotiator.searchListOfAuthenticationTypesInVNCClient();

    // if the list is empty, an error has occurred
    if (optionalAuthenticationList.isEmpty()) {
      return;
    }

    List<RFBAuthenticationTypes> listOfClientAuthentications = optionalAuthenticationList.get();

    //

    CreateParametersToExecutableSwitch createParametersToExecutableSwitch =
        new CreateParametersToExecutableSwitch(listOfClientAuthentications, swingMediator);

    Optional<List<String>> listOfParameters =
        createParametersToExecutableSwitch.createParameters(computer, vncProgramConfig);

    // if the list is empty, an error has occurred
    if (listOfParameters.isEmpty()) {
      return;
    }

    //

    VNCProcessCreator vncProcess =
        new VNCProcessCreatorSwitch(
            generalConfiguration, listOfParameters.get(), vncProgramConfig, swingMediator);
    vncProcess.executeVNCProcess();
  }
}
