package com.quazzom.javis.administrator.gui.internal_frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.quazzom.javis.administrator.gui.controllers.VNCComputerListInternalFrameController;
import com.quazzom.javis.administrator.gui.table.JTableComputers;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.Computer;

public class VNCComputerListInternalFrame extends ClosableInternalFrame {

  private JTableComputers jTableComputers;
  private JTextField jTextFieldFilter;
  private Text theLanguage;

  /**
   * @wbp.parser.constructor
   */
  public VNCComputerListInternalFrame(
      JFrame administratorFrame,
      JDesktopPane jdesktopPanel,
      VNCComputerListInternalFrameController vNCComputerListInternalFrameController) {
    this(
        "",
        true,
        true,
        true,
        true,
        administratorFrame,
        jdesktopPanel,
        vNCComputerListInternalFrameController);
  }

  public VNCComputerListInternalFrame(
      String title,
      boolean resizable,
      boolean closable,
      boolean maximizable,
      boolean iconifiable,
      JFrame administratorFrame,
      JDesktopPane jdesktopPanel,
      VNCComputerListInternalFrameController vNCComputerListInternalFrameController) {
    super(title, resizable, closable, maximizable, iconifiable, administratorFrame, jdesktopPanel);

    this.theLanguage =
        LanguageFactory.getLanguage(LanguagePathToFile.VNC_COMPUTER_LIST_INTERNAL_FRAME);

    jTableComputers =
        new JTableComputers(administratorFrame, vNCComputerListInternalFrameController);

    setJMenuBar(new JMenuBarComputerList(vNCComputerListInternalFrameController, theLanguage));

    JPanel jPanelRoot = new JPanel();
    jPanelRoot.setBackground(Color.ORANGE);
    getContentPane().add(jPanelRoot, BorderLayout.CENTER);
    jPanelRoot.setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setBackground(Color.CYAN);
    jPanelRoot.add(panel, BorderLayout.SOUTH);
    panel.setLayout(new BorderLayout(0, 0));

    JPanel jPanelButtons = new JPanel();
    panel.add(jPanelButtons, BorderLayout.SOUTH);
    jPanelButtons.setLayout(new BorderLayout(0, 0));

    JPanel jPanelFilter = new JPanel();
    jPanelButtons.add(jPanelFilter, BorderLayout.NORTH);

    JLabel jLabelFilterInfo =
        new JLabel(String.format("%s:", theLanguage.getText("JLABEL_FILTER_INFO")));
    jLabelFilterInfo.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
    jLabelFilterInfo.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelFilterInfo.setPreferredSize(new Dimension(60, 30));
    jPanelFilter.add(jLabelFilterInfo);

    jTextFieldFilter = new JTextField();
    jTextFieldFilter.setPreferredSize(new Dimension(200, 30));
    jTextFieldFilter.setColumns(40);
    jTextFieldFilter
        .getDocument()
        .addDocumentListener(new DocumentTableFilter(jTableComputers, jTextFieldFilter));
    jPanelFilter.add(jTextFieldFilter);

    JButton jButtonCleanFilter = new JButton(theLanguage.getText("JBUTTON_CLEAN_FILTER"));
    jButtonCleanFilter.addActionListener(
        (e) -> {
          jTextFieldFilter.setText("");
        });
    jButtonCleanFilter.setPreferredSize(new Dimension(90, 30));
    jPanelFilter.add(jButtonCleanFilter);

    JPanel jPanelOptions = new JPanel();
    jPanelButtons.add(jPanelOptions, BorderLayout.SOUTH);

    JPanel jPanelCenter = new JPanel();
    jPanelCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
    jPanelRoot.add(jPanelCenter, BorderLayout.CENTER);
    jPanelCenter.setLayout(new BorderLayout(0, 0));

    JScrollPane jScrollPane = new JScrollPane(jTableComputers);
    jPanelCenter.add(jScrollPane, BorderLayout.CENTER);

    setTitle(String.format("  %s  ", theLanguage.getText("TITLE")));
  }

  public void showComputerList(List<Computer> listOfComputers) {
    jTableComputers.showComputerList(listOfComputers);
  }

  public void createComputer(Computer computer) {
    jTableComputers.createComputer(computer);
  }

  public void deleteComputer(Computer computer) {
    jTableComputers.deleteComputer(computer);
  }

  public void updateComputer(Computer computer) {
    jTableComputers.updateComputer(computer);
  }

  class DocumentTableFilter implements DocumentListener {

    private JTableComputers jTableComputers;
    private JTextField jTextFieldFilter;

    public DocumentTableFilter(JTableComputers jTableComputers, JTextField jTextFieldTofilter) {
      this.jTableComputers = jTableComputers;
      this.jTextFieldFilter = jTextFieldTofilter;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
      applyFilter(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
      applyFilter(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
      applyFilter(e);
    }

    private void applyFilter(DocumentEvent e) {

      String filter = jTextFieldFilter.getText();
      jTableComputers.filteTheComputerList(filter);
    }
  }

  private class JMenuBarComputerList extends JMenuBar implements ActionListener {

    private VNCComputerListInternalFrameController vNCComputerListInternalFrameController;
    private JMenuItem jMenuItemComputerCreate;
    private JMenuItem jMenuItemVNCOpenScreenToConnection;

    public JMenuBarComputerList(
        VNCComputerListInternalFrameController vNCComputerListInternalFrameControlle,
        Text theLanguage) {
      this.vNCComputerListInternalFrameController = vNCComputerListInternalFrameControlle;

      JMenu jMenuComputer = new JMenu(theLanguage.getText("JMENU_COMPUTER"));
      jMenuItemComputerCreate = new JMenuItem(theLanguage.getText("JMENUITEM_COMPUTER_CREATE"));
      jMenuItemComputerCreate.addActionListener(this);

      jMenuComputer.add(jMenuItemComputerCreate);
      add(jMenuComputer);

      JMenu jMenuVNC = new JMenu(theLanguage.getText("JMENU_VNC_ACCESS"));
      jMenuItemVNCOpenScreenToConnection =
          new JMenuItem(theLanguage.getText("JMENUITEM_VNC_OPEN_SCREEN_TO_CONNECTION"));
      jMenuItemVNCOpenScreenToConnection.addActionListener(this);

      jMenuVNC.add(jMenuItemVNCOpenScreenToConnection);
      add(jMenuVNC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(jMenuItemComputerCreate)) {
        vNCComputerListInternalFrameController.showJDialogComputerCreate();
        return;
      }
      if (e.getSource().equals(jMenuItemVNCOpenScreenToConnection)) {
        vNCComputerListInternalFrameController.vNCOpenScreenToConnection();
      }
    }
  }
}
