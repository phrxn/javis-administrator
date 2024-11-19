package com.quazzom.javis.administrator.gui.controllers;

import java.util.List;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogType;
import com.quazzom.javis.administrator.gui.internal_frame.VNCComputerListInternalFrame;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.persistence.ComputerPersistence;
import com.quazzom.javis.administrator.persistence.ComputerPersistenceSwitch;
import com.quazzom.javis.administrator.persistence.PersistenceException;
import com.quazzom.javis.administrator.vnc.Computer;

public class VNCComputerListInternalFrameController extends InternalFrameController {

  private VNCComputerListInternalFrame vNCComputerListInternalFrame;
  private ComputerPersistence computerPersistence;
  private Text theLanguage;

  public VNCComputerListInternalFrameController(
      GeneralConfiguration generalConfiguration, SwingMediator swingMediator) {
    super(generalConfiguration, swingMediator);
    this.computerPersistence = new ComputerPersistenceSwitch(generalConfiguration);
    this.theLanguage =
        LanguageFactory.getLanguage(LanguagePathToFile.VNC_COMPUTER_LIST_INTERNAL_FRAME_CONTROLLER);
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

  public void vNCAccessTheComputerOnlyToView(Computer computer) {}

  public void vNCAccessTheComputerAndInteract(Computer computer) {}

  public void vNCAccessTheComputerWithCredentials(Computer computer) {}

  public void vNCOpenScreenToConnection() {}
}
