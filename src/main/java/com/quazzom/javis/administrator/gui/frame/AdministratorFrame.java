package com.quazzom.javis.administrator.gui.frame;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import com.quazzom.javis.administrator.configuration.GeneralConfiguration;
import com.quazzom.javis.administrator.gui.SwingCommons;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.images.ImagePathToFile;
import com.quazzom.javis.administrator.images.SwingImages;

public class AdministratorFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  private static final int FRAME_WIDTH_MINIMUM = 1024;
  private static final int FRAME_HEIGHT_MINIMUM = 735;

  private final Rectangle SCREEN_SIZE =
      GraphicsEnvironment.getLocalGraphicsEnvironment()
          .getDefaultScreenDevice()
          .getDefaultConfiguration()
          .getBounds();
  private final Dimension FRAME_MINIMUM_SIZE =
      new Dimension(FRAME_WIDTH_MINIMUM, FRAME_HEIGHT_MINIMUM);

  private SwingCommons swingCommons;

  private JDesktopPane jDesktopPane;

  private SwingMediator swingMediator;

  public AdministratorFrame() {

    swingCommons = new SwingCommons();

    startTheFrameGUIThings();
  }

  private void startTheFrameGUIThings() {

    jDesktopPane = new JDesktopPane();
    add(jDesktopPane);

    addWindowListener(new JFrameControl());
    setTitle(swingCommons.createTitle());
    setBounds(SCREEN_SIZE);
    setMinimumSize(FRAME_MINIMUM_SIZE);
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setVisible(true);
  }

  public void startGUIProgram(GeneralConfiguration generalConfiguration) {
    swingMediator = new SwingMediator(generalConfiguration, this, jDesktopPane);
    swingMediator.showLoginInternalFrame();
    setIconImage(SwingImages.getImage(ImagePathToFile.ICON_JAVIS_64_X_64));
  }

  class JFrameControl extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
      swingMediator.showExitProgram();
    }
  }
}
