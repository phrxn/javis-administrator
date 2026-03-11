package com.quazzom.javis.administrator.gui.internal_frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.JTabbedPane;
import com.quazzom.javis.administrator.gui.controllers.ControllersMediator;
import com.quazzom.javis.administrator.gui.panel.JPanelMainInternalFrameVNC;

public class MainInternalFrame extends RootInternalFrame {

  private JTabbedPane jTabbedPane;

  public MainInternalFrame(ControllersMediator controllersMediator) {
    super(
        "", // title
        false, // resizable
        false, // closable
        false, // maximizable
        true); // iconifiable

    jTabbedPane = new JTabbedPane(JTabbedPane.TOP);

    jTabbedPane.addTab("VNC", null, new JPanelMainInternalFrameVNC(controllersMediator), null);

    getContentPane().add(jTabbedPane, BorderLayout.CENTER);

    setVisible(true);
    setSize(new Dimension(1024, 735));

    try {
      setMaximum(true);
    } catch (PropertyVetoException e) {
      throw new RuntimeException(e);
    }
  }
}
