package com.quazzom.javis.administrator.gui;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

public class SwingUtilitiesAdministrator {

  public void invokeAndWait(final Runnable runnable) {
    if (SwingUtilities.isEventDispatchThread()) {
      runnable.run();
    } else {
      try {
        SwingUtilities.invokeAndWait(runnable);
      } catch (final InterruptedException e) {
        throw new RuntimeException(e);
      } catch (final InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * A wrapper for {@link SwingUtilities#invokeLater(Runnable)}.
   *
   * @param runnable The runnable to invoke later.
   */
  public void invokeLater(final Runnable runnable) {
    SwingUtilities.invokeLater(runnable);
  }

  public boolean isEventDispatchThread() {
    return SwingUtilities.isEventDispatchThread();
  }
}
