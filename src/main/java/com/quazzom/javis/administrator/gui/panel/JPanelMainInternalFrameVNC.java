package com.quazzom.javis.administrator.gui.panel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.button.JButtonToPanelsInsideMainInternalFrame;
import com.quazzom.javis.administrator.images.ImagePathToFile;
import com.quazzom.javis.administrator.images.SwingImages;
import com.quazzom.javis.administrator.lang.Language;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;

public class JPanelMainInternalFrameVNC extends JPanelMainInternalFrame {

  private JButton jButtonOpenVNCComputerList;

  private ButtonsControl buttonsControl;
  private SwingMediator swingMediator;
  private Language theLanguage;

  public JPanelMainInternalFrameVNC(SwingMediator swingMediator) {
    super();
    this.buttonsControl = new ButtonsControl();
    this.swingMediator = swingMediator;
    this.theLanguage =
        LanguageFactory.getLanguage(LanguagePathToFile.JPANEL_MAIN_INTERNAL_FRAME_VNC);

    createGUI();
  }

  private void createGUI() {
    jButtonOpenVNCComputerList =
        new JButtonToPanelsInsideMainInternalFrame(
            theLanguage.getText("JBUTTON_TO_PANELS_INSIDE_MAIN_INTERNAL_FRAME"));
    jButtonOpenVNCComputerList.addActionListener(buttonsControl);
    jButtonOpenVNCComputerList.setIcon(
        SwingImages.getImageIcon(ImagePathToFile.ICON_COMPUTER_32_X_32));
    add(jButtonOpenVNCComputerList);
  }

  private class ButtonsControl extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(jButtonOpenVNCComputerList)) {
        swingMediator.showVNCComputerListInternalFrame();
      }
    }
  }
}
