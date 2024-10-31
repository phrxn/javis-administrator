package com.quazzom.javis.administrator.gui.text_pane;

import java.awt.Dimension;
import javax.swing.JTextPane;

/**
 * This is a modified {@link JTextPane} that doesn't wrap text.
 *
 * <p>The normal {@link JTextPane} wraps long lines of text instead of adding a horizontal
 * scrollbar. This modified version does not wrap long lines, and instead shows the horizontal
 * scrollbar.
 *
 * @author Christian Ihle
 */
public class JTextPaneWithoutWrap extends JTextPane {

  /** */
  private static final long serialVersionUID = 1L;

  /**
   * Makes sure the size of the textpane fills the whole viewport.
   *
   * <p>Since {@link #getScrollableTracksViewportWidth()} is disabled then the size set here is the
   * textpane's preferred size. Which is the width of the text in it. If the text in this textpane
   * is shorter than the viewport, then the part of the textpane not filled with text is grayed out.
   *
   * <p>To fix this, the size is adjusted to the same size as the viewport. {@inheritDoc}
   */
  @Override
  public void setSize(final Dimension d) {
    // Parent is the viewport
    if (d.width < getParent().getSize().width) {
      d.width = getParent().getSize().width;
    }

    super.setSize(d);
  }

  /**
   * If the scrollpane should be the same size as the viewport.
   *
   * <p>If that's the case then long lines will wrap at the end of the textpane. If the scrollpane
   * has it's own size then there is no need to wrap long lines.
   *
   * @return false, to disable word wrap.
   *     <p>{@inheritDoc}
   */
  @Override
  public boolean getScrollableTracksViewportWidth() {
    return false;
  }
}
