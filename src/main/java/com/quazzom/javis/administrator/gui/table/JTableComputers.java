package com.quazzom.javis.administrator.gui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import com.quazzom.javis.administrator.gui.controllers.VNCComputerListInternalFrameController;
import com.quazzom.javis.administrator.gui.dialog.JDialogInteraction;
import com.quazzom.javis.administrator.gui.dialog.JDialogYesCancelOption;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.ComputerFilter;

public class JTableComputers extends JTable {

  private ComputerTableModel computerTableModel;
  private Text theLanguage;
  private JDialogInteraction jDialogInteraction;

  public JTableComputers(
      JFrame parent,
      VNCComputerListInternalFrameController vncComputerListInternalFrameController) {

    this.theLanguage = LanguageFactory.getLanguage(LanguagePathToFile.JTABLE_COMPUTERS);
    this.jDialogInteraction = new JDialogInteraction(parent);

    computerTableModel = new ComputerTableModel(new ArrayList<Computer>());
    setModel(computerTableModel);
    setDefaultRenderer(Object.class, new ComputerTableCellRenderer(computerTableModel));

    JPopupMenuRightMouseClick jTablePopMenu =
        new JPopupMenuRightMouseClick(
            this, computerTableModel, vncComputerListInternalFrameController);
    JTableMouseAdapter jTableMouseAdapter = new JTableMouseAdapter(this, jTablePopMenu);
    addMouseListener(jTableMouseAdapter);

    TableRowSorter<ComputerTableModel> sorter = new TableRowSorter<>(computerTableModel);
    setRowSorter(sorter);

    // hide the id column
    getColumnModel().getColumn(JTableComputersColumns.COLUMN_ID.getNumber()).setMinWidth(0);
    getColumnModel().getColumn(JTableComputersColumns.COLUMN_ID.getNumber()).setMaxWidth(0);
    getColumnModel().getColumn(JTableComputersColumns.COLUMN_ID.getNumber()).setWidth(0);
  }

  public void showComputerList(List<Computer> listOfComputers) {
    computerTableModel.showComputerList(listOfComputers);
  }

  public void filteTheComputerList(String filter) {
    computerTableModel.filterComputers(filter);
  }

  public void createComputer(Computer computer) {
    computerTableModel.createComputer(computer);
  }

  public void deleteComputer(Computer computer) {
    computerTableModel.deleteComputer(computer);
  }

  public void updateComputer(Computer computer) {
    computerTableModel.updateComputer(computer);
  }

  class ComputerTableModel extends AbstractTableModel {
    private List<Computer> listOfComputers;
    private List<Computer> listOfComputersFilter;

    private String[] columns = {
      theLanguage.getText(JTableComputersColumns.COLUMN_ID.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_POWER_STATUS_COMPUTER.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_SESSION_TYPE.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_USERNAME.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_HOSTNAME.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_IP.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_IS_MANUAL.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_LOGIN_DATE.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_JAVIS_CLIENT_VERSION.getLanguageID()),
      theLanguage.getText(JTableComputersColumns.COLUMN_DETAILS.getLanguageID())
    };

    public ComputerTableModel(List<Computer> listOfComputers) {
      this.listOfComputers = new ArrayList<>();
      this.listOfComputersFilter = new ArrayList<>();
      setListComputers(listOfComputers);
    }

    @Override
    public int getRowCount() {
      return listOfComputersFilter.size();
    }

    @Override
    public int getColumnCount() {
      return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
      return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

      Computer computer = listOfComputersFilter.get(rowIndex);

      switch (JTableComputersColumns.values()[columnIndex]) {
        case COLUMN_ID:
          return computer.getId();
        case COLUMN_POWER_STATUS_COMPUTER:
          return computer.getPowerStatusStr();
        case COLUMN_SESSION_TYPE:
          return computer.getSessionTypeStr();
        case COLUMN_USERNAME:
          return computer.getUsername();
        case COLUMN_HOSTNAME:
          return computer.getHostname();
        case COLUMN_IP:
          return computer.getIPStr();
        case COLUMN_IS_MANUAL:
          return computer.isManualStr();
        case COLUMN_LOGIN_DATE:
          return computer.getLoginDateStr();
        case COLUMN_JAVIS_CLIENT_VERSION:
          return computer.getJavisClientVersion();
        case COLUMN_DETAILS:
          return computer.getDetails();
        default:
          return "";
      }
    }

    public Computer getComputerAtRow(int rowIndex) {
      return listOfComputersFilter.get(rowIndex);
    }

    public void filterComputers(String filter) {

      ComputerFilter computerFilter = new ComputerFilter();

      listOfComputersFilter.clear();
      if (filter == null || filter.trim().isEmpty()) {
        listOfComputersFilter.addAll(listOfComputers);
      } else {
        for (Computer computer : listOfComputers) {
          if (computerFilter.computerMatchWithString(computer, filter, false)) {
            listOfComputersFilter.add(computer);
          }
        }
      }
      fireTableDataChanged();
    }

    public void createComputer(Computer computer) {
      listOfComputers.add(computer);
      listOfComputersFilter.add(computer);
      fireTableRowsInserted(listOfComputersFilter.size() - 1, listOfComputersFilter.size() - 1);
    }

    public void deleteComputer(Computer computer) {

      // First we need to remove the computer from main list
      int computerIndex = listOfComputers.indexOf(computer);
      if (computerIndex == -1) return;
      listOfComputers.remove(computerIndex);

      // After removing from the main list, we need to remove it from the filter list
      // since it is always used for display
      int computerFilteIndex = listOfComputersFilter.indexOf(computer);

      listOfComputersFilter.remove(computerFilteIndex);

      // Since the filter list is the one used for display, obviously
      // its index is used to update the table
      fireTableRowsDeleted(computerFilteIndex, computerFilteIndex);
    }

    public void updateComputer(Computer computer) {
      int indexOfComputer = listOfComputers.indexOf(computer);
      int indexOfComputerFilter = listOfComputersFilter.indexOf(computer);

      listOfComputers.set(indexOfComputer, computer);
      listOfComputersFilter.set(indexOfComputerFilter, computer);

      for (JTableComputersColumns columns : JTableComputersColumns.values()) {
        fireTableCellUpdated(indexOfComputerFilter, columns.getNumber());
      }
    }

    public void showComputerList(List<Computer> listOfComputers) {
      setListComputers(listOfComputers);
      fireTableDataChanged();
    }

    private void setListComputers(List<Computer> listOfComputers) {
      this.listOfComputers.clear();
      this.listOfComputers.addAll(listOfComputers);
      this.listOfComputersFilter.clear();
      this.listOfComputersFilter.addAll(listOfComputers);
    }
  }

  class ComputerTableCellRenderer extends DefaultTableCellRenderer {

    private ComputerTableModel computerTableModel;

    public ComputerTableCellRenderer(ComputerTableModel computerTableModel) {
      this.computerTableModel = computerTableModel;
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

      JLabel label =
          (JLabel)
              super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

      if (isSelected) {
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        return label;
      }

      int modelRow = table.getRowSorter().convertRowIndexToModel(row);

      Computer computer = computerTableModel.getComputerAtRow(modelRow);

      Computer.PowerStatus ComputerID = computer.getPowerStatus();

      if (ComputerID == Computer.PowerStatus.POWEROFF) label.setBackground(Color.RED);
      else label.setBackground(Color.GREEN);

      return label;
    }
  }

  class JPopupMenuRightMouseClick extends JPopupMenu {

    private JTable jTable;
    private ComputerTableModel computerTableModel;

    private JMenuItem jMenuItemVNCAccessTheComputerOnlyToView;
    private JMenuItem jMenuItemVNCAccessTheComputerAndInteract;
    // --
    private JMenuItem jMenuItemCopyCellContent;
    // --
    private JMenuItem jMenuItemComputerUpdate;
    private JMenuItem jMenuItemComputerDelete;

    private VNCComputerListInternalFrameController vncComputerListInternalFrameController;

    public JPopupMenuRightMouseClick(
        JTable jTable,
        ComputerTableModel computerTableModel,
        VNCComputerListInternalFrameController vncComputerListInternalFrameController) {
      this.jTable = jTable;
      this.computerTableModel = computerTableModel;
      this.vncComputerListInternalFrameController = vncComputerListInternalFrameController;

      JMenuItemComputerSelected jMenuItemComputer = new JMenuItemComputerSelected();

      createMenuButtons(jMenuItemComputer);
    }

    private void createMenuButtons(JMenuItemComputerSelected jMenuItemComputer) {

      jMenuItemVNCAccessTheComputerOnlyToView =
          new JMenuItem(theLanguage.getText("JMENUITEM_VNC_ACCESS_THE_COMPUTER_ONLY_TO_VIEW"));
      jMenuItemVNCAccessTheComputerAndInteract =
          new JMenuItem(theLanguage.getText("JMENUITEM_VNC_ACCESS_THE_COMPUTER_AND_INTERACT"));

      jMenuItemVNCAccessTheComputerOnlyToView.addActionListener(jMenuItemComputer);
      jMenuItemVNCAccessTheComputerAndInteract.addActionListener(jMenuItemComputer);

      JMenu jMenuVNCConnectionOptions =
          new JMenu(theLanguage.getText("JMENU_VNC_CONNECTION_OPTIONS"));
      jMenuVNCConnectionOptions.add(jMenuItemVNCAccessTheComputerOnlyToView);
      jMenuVNCConnectionOptions.add(jMenuItemVNCAccessTheComputerAndInteract);
      add(jMenuVNCConnectionOptions);

      jMenuItemCopyCellContent = new JMenuItem(theLanguage.getText("JMENUITEM_COPY_CELL_CONTENT"));
      jMenuItemCopyCellContent.addActionListener(jMenuItemComputer);
      add(jMenuItemCopyCellContent);

      jMenuItemComputerUpdate = new JMenuItem(theLanguage.getText("JMENUITEM_COMPUTER_UPDATE"));
      jMenuItemComputerDelete = new JMenuItem(theLanguage.getText("JMENUITEM_COMPUTER_DELETE"));

      jMenuItemComputerUpdate.addActionListener(jMenuItemComputer);
      jMenuItemComputerDelete.addActionListener(jMenuItemComputer);

      JMenu jMenuComputerOptions = new JMenu(theLanguage.getText("JMENU_COMPUTER_OPTIONS"));
      jMenuComputerOptions.add(jMenuItemComputerUpdate);
      jMenuComputerOptions.add(jMenuItemComputerDelete);
      add(jMenuComputerOptions);
    }

    class JMenuItemComputerSelected extends AbstractAction {

      @Override
      public void actionPerformed(ActionEvent e) {

        int selectedRow = jTable.getSelectedRow();

        if (selectedRow == -1) return;

        int modelRow = jTable.convertRowIndexToModel(selectedRow);
        Computer computer = computerTableModel.getComputerAtRow(modelRow);

        if (e.getSource().equals(jMenuItemVNCAccessTheComputerOnlyToView)) {
          vNCAccessTheComputerOnlyToView(computer);
        } else if (e.getSource().equals(jMenuItemVNCAccessTheComputerAndInteract)) {
          vNCAccessTheComputerAndInteract(computer);
        } else if (e.getSource().equals(jMenuItemCopyCellContent)) {
          copyCellContent();
        } else if (e.getSource().equals(jMenuItemComputerUpdate)) {
          updateComputer(computer);
        } else if (e.getSource().equals(jMenuItemComputerDelete)) {
          deleteComputer(computer);
        }
      }

      private void vNCAccessTheComputerOnlyToView(Computer computer) {
        vncComputerListInternalFrameController.vNCAccessTheComputerOnlyToView(computer);
      }

      private void vNCAccessTheComputerAndInteract(Computer computer) {
        vncComputerListInternalFrameController.vNCAccessTheComputerAndInteract(computer);
      }

      private void copyCellContent() {

        int row = jTable.getSelectedRow();
        int column = jTable.getSelectedColumn();

        if (row != -1 && column != -1) {

          // It is always necessary to convert the row and column values, since the table is a
          // "TableRowSorter".
          // In addition, the columns can also be moved.
          int rowSorted = jTable.convertRowIndexToModel(row);
          int columnSorted = jTable.convertColumnIndexToModel(column);

          Object cellContent = computerTableModel.getValueAt(rowSorted, columnSorted);

          StringSelection selection = new StringSelection(cellContent.toString());
          Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
          clipboard.setContents(selection, null);
        }
      }

      private void updateComputer(Computer computer) {
        vncComputerListInternalFrameController.showJDialogComputerUpdate(computer);
      }

      private void deleteComputer(Computer computer) {
        JDialogYesCancelOption option =
            jDialogInteraction.showYesCancelOptions(theLanguage.getText("CONFIRM_DELETION"));

        if (option != JDialogYesCancelOption.YES) return;

        vncComputerListInternalFrameController.deleteComputer(computer);
      }
    }
  }

  class JTableMouseAdapter extends MouseAdapter {

    private JTable jTable;
    private JPopupMenuRightMouseClick jPopupMenuRightMouseClick;

    public JTableMouseAdapter(JTable jTable, JPopupMenuRightMouseClick jPopupMenuRightMouseClick) {
      this.jTable = jTable;
      this.jPopupMenuRightMouseClick = jPopupMenuRightMouseClick;
    }

    @Override
    public void mousePressed(MouseEvent e) {

      if (e.getButton() == MouseEvent.BUTTON3) {

        int rowSelected = jTable.rowAtPoint(e.getPoint());

        if (rowSelected != -1) jTable.setRowSelectionInterval(rowSelected, rowSelected);

        int row = jTable.rowAtPoint(e.getPoint());
        int col = jTable.columnAtPoint(e.getPoint());

        // select the cell before displaying the menu to avoid problems
        // with the menu's JItemMenu copy cell option
        if (row >= 0 && col >= 0) {
          jTable.setRowSelectionInterval(row, row);
          jTable.setColumnSelectionInterval(col, col);
        }

        jPopupMenuRightMouseClick.show(jTable, e.getX(), e.getY());
      }
    }
  }
}
