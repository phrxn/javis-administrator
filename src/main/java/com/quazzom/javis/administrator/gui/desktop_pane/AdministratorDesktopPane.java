package com.quazzom.javis.administrator.gui.desktop_pane;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class AdministratorDesktopPane extends JDesktopPane {

	public AdministratorDesktopPane() {
		setDesktopManager(new BoundedDesktopManager());
		addComponentListener(new SetInternalFramesLocation(this));
	}

	class BoundedDesktopManager extends DefaultDesktopManager {

		public void beginDraggingFrame(JComponent f) {
			if (!"fixed".equals(f.getClientProperty("dragMode")))
				return;
		}

		public void dragFrame(JComponent f, int newX, int newY) {
			if (!"fixed".equals(f.getClientProperty("dragMode")))
				super.dragFrame(f, newX, newY);
		}

		public void endDraggingFrame(JComponent f) {
			if (!"fixed".equals(f.getClientProperty("dragMode")))
				super.endDraggingFrame(f);
		}

		@Override
		public void beginResizingFrame(JComponent f, int direction) {
			// Don't do anything. Needed to prevent the DefaultDesktopManager setting the
			// dragMode
		}

		@Override
		public void setBoundsForFrame(JComponent f, int newX, int newY, int newWidth, int newHeight) {
			if (!(f instanceof JInternalFrame))
				return;

			boolean didResize = (f.getWidth() != newWidth || f.getHeight() != newHeight);
			if (!inBounds((JInternalFrame) f, newX, newY, newWidth, newHeight)) {
				Container parent = f.getParent();
				Dimension parentSize = parent.getSize();
				int boundedX = (int) Math.min(Math.max(0, newX), parentSize.getWidth() - newWidth);
				int boundedY = (int) Math.min(Math.max(0, newY), parentSize.getHeight() - newHeight);
				f.setBounds(boundedX, boundedY, newWidth, newHeight);
			} else {
				f.setBounds(newX, newY, newWidth, newHeight);
			}
			if (didResize) {
				f.validate();
			}
		}

		protected boolean inBounds(JInternalFrame f, int newX, int newY, int newWidth, int newHeight) {
			if (newX < 0 || newY < 0)
				return false;
			if (newX + newWidth > f.getDesktopPane().getWidth())
				return false;
			if (newY + newHeight > f.getDesktopPane().getHeight())
				return false;
			return true;
		}
	}

	private class SetInternalFramesLocation extends ComponentAdapter {
		private JDesktopPane jDesktopPane;

		public SetInternalFramesLocation(JDesktopPane jDesktopPane) {
			this.jDesktopPane = jDesktopPane;
		}

		@Override
		public void componentResized(ComponentEvent e) {

			int desktopWidth = jDesktopPane.getWidth();
			int desktopHeight = jDesktopPane.getHeight();

			for (JInternalFrame internalFrame : jDesktopPane.getAllFrames()) {
				int internalWidth = internalFrame.getWidth();
				int internalHeight = internalFrame.getHeight();

				int newX = (desktopWidth - internalWidth) / 2;
				int newY = (desktopHeight - internalHeight) / 2;

				internalFrame.setLocation(newX, newY);
			}
		}
	}
}
