/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtWindowPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Image;
import uk.co.iizuka.kozo.ui.Window;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtWindowPeer.java 88029 2011-05-13 18:53:16Z mark@IIZUKA.CO.UK $
 */
public class SwtWindowPeer extends SwtBoxPeer
{
	// constructors -----------------------------------------------------------
	
	public SwtWindowPeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		Shell shell = new Shell(Display.getDefault());
		shell.setLayout(newLayout());
		return shell;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Window component = (Window) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Shell shell = (Shell) getPeerManager().getPeer(component);
		
		if (Window.TITLE_PROPERTY.equals(name))
		{
			shell.setText((String) newValue);
		}
		else if (Window.IMAGE_PROPERTY.equals(name))
		{
			if (newValue != null)
			{
				shell.setImage(SwtUtils.getSWTImage(shell, (Image) newValue));
			}
		}
		else if (Component.VISIBLE_PROPERTY.equals(name))
		{
			// TODO: reimplement
//			setVisible(shell, ((Boolean) newValue).booleanValue());
		}
		else
		{
			super.propertyChange(event);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected void setVisible(Shell shell, boolean visible)
	{
		if (visible)
		{
			// TODO: pack when widgets have sensible preferred sizes
//			shell.pack();
			Display display = shell.getDisplay();
			Rectangle screenBounds = display.getBounds();
			Point shellSize = shell.getSize();
			shell.setLocation((screenBounds.width - shellSize.x) >> 1, (screenBounds.height - shellSize.y) >> 1);
			shell.open();
			
			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
			
			display.sleep();
		}
		else
		{
			shell.close();
		}
	}
}
