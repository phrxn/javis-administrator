package com.quazzom.javis.administrator.gui.panel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import com.quazzom.javis.administrator.gui.button.JButtonToPanelsInsideMainInternalFrame;
import com.quazzom.javis.administrator.gui.controllers.ControllersMediator;
import com.quazzom.javis.administrator.images.ImagePathToFile;
import com.quazzom.javis.administrator.images.SwingImages;
import com.quazzom.javis.administrator.lang.Language;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;

public class JPanelMainInternalFrameVNC extends JPanelMainInternalFrame {

  private JButton jButtonOpenVNCComputerList;
  private JButton jButtonOpenVNCConfigurationJDialog;

  private ButtonsControl buttonsControl;
  private Language theLanguage;

  private ControllersMediator controllersMediator;

  public JPanelMainInternalFrameVNC(ControllersMediator controllersMediator) {
    this.controllersMediator = controllersMediator;
    this.buttonsControl = new ButtonsControl();
    this.theLanguage =
        LanguageFactory.getLanguage(LanguagePathToFile.JPANEL_MAIN_INTERNAL_FRAME_VNC);

    createGUI();
  }

  private void createGUI() {
    jButtonOpenVNCComputerList =
        new JButtonToPanelsInsideMainInternalFrame(theLanguage.getText("JBUTTON_COMPUTER_LIST"));
    jButtonOpenVNCComputerList.addActionListener(buttonsControl);
    jButtonOpenVNCComputerList.setIcon(
        SwingImages.getImageIcon(ImagePathToFile.ICON_COMPUTER_32_X_32));
    add(jButtonOpenVNCComputerList);

    jButtonOpenVNCConfigurationJDialog =
        new JButtonToPanelsInsideMainInternalFrame(
            theLanguage.getText("JBUTTON_VNC_CONFIGURATION_SCREEN"));
    jButtonOpenVNCConfigurationJDialog.addActionListener(buttonsControl);
    jButtonOpenVNCConfigurationJDialog.setIcon(
        SwingImages.getImageIcon(ImagePathToFile.ICON_VNC_CONFIGURATION_32_X_32));
    add(jButtonOpenVNCConfigurationJDialog);
  }

  private class ButtonsControl extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(jButtonOpenVNCComputerList)) {
        controllersMediator.showVNCComputerListInternalFrame();
      }
      if (e.getSource().equals(jButtonOpenVNCConfigurationJDialog)) {
        controllersMediator.showVNCConfigurationJDialog();
      }
    }
  }
}
