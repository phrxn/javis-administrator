package com.quazzom.javis.administrator.gui.popup;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.DefaultEditorKit;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguagePathToFile;
import com.quazzom.javis.administrator.lang.Text;

public class CopyPopup extends JPopupMenu implements PopupMenuListener, ActionListener {
  private static final long serialVersionUID = 1L;

  private final JMenuItem jMenuItemCopy;

  private final JMenuItem jMenuItemSelectAll;

  private final JTextPane textpane;

  public CopyPopup(final JTextPane textpane) {
    this(textpane, LanguageFactory.getLanguage(LanguagePathToFile.COPY_POPUP));
  }

  /**
   * Constructor. Creates the menu.
   *
   * @param textpane The text pane to use the popup on.
   */
  public CopyPopup(final JTextPane textpane, Text text) {
    this.textpane = textpane;

    final int menuShortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

    jMenuItemCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
    jMenuItemCopy.setText(text.getText("JMENUITEM_COPY"));
    jMenuItemCopy.setMnemonic(keyCode("C"));
    jMenuItemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, menuShortcutKeyMask));

    jMenuItemSelectAll = new JMenuItem(text.getText("JMENUITEM_SELECTALL"));
    jMenuItemSelectAll.setMnemonic(keyCode("A"));
    jMenuItemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, menuShortcutKeyMask));

    add(jMenuItemCopy);
    add(jMenuItemSelectAll);

    textpane.setComponentPopupMenu(this);
    jMenuItemSelectAll.addActionListener(this);

    addPopupMenuListener(this);
  }

  @Override
  public void popupMenuWillBecomeVisible(final PopupMenuEvent e) {

    if (textpane.getSelectedText() == null) jMenuItemCopy.setEnabled(false);
    else jMenuItemCopy.setEnabled(true);

    if (textpane.getText().length() == 0) jMenuItemSelectAll.setEnabled(false);
    else jMenuItemSelectAll.setEnabled(true);
  }

  @Override
  public void popupMenuWillBecomeInvisible(final PopupMenuEvent e) {}

  @Override
  public void popupMenuCanceled(final PopupMenuEvent e) {}

  @Override
  public void actionPerformed(final ActionEvent e) {
    textpane.requestFocusInWindow();
    textpane.selectAll();
  }

  private int keyCode(final String key) {
    return KeyStroke.getKeyStroke(key).getKeyCode();
  }
}
