/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtUtils.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.Orientation;
import org.hobsoft.symmetry.util.lang.ValuedRunnable;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtUtils.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public final class SwtUtils
{
	// constants --------------------------------------------------------------
	
	private static final Logger LOG = Logger.getLogger(SwtUtils.class.getName());

	// types ------------------------------------------------------------------
	
	private static class DebugRunnable implements Runnable
	{
		// fields -----------------------------------------------------------------
		
		private Runnable runnable;
		
		// constructors -----------------------------------------------------------
		
		public DebugRunnable(Runnable runnable)
		{
			this.runnable = runnable;
		}
		
		// Runnable methods -------------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run()
		{
			try
			{
				runnable.run();
			}
			catch (RuntimeException exception)
			{
				LOG.log(Level.WARNING, "Uncaught exception in Display.syncExec() or Display.asyncExec():", exception);
			}
		}
	}
	
	// constructors -----------------------------------------------------------
	
	private SwtUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Composite getComposite(Widget widget)
	{
		Composite composite;
		
		if (widget instanceof Composite)
		{
			composite = (Composite) widget;
		}
		else if (widget instanceof TabItem)
		{
			composite = ((TabItem) widget).getParent();
		}
		else
		{
			throw new IllegalStateException("Cannot obtain composite from widget: " + widget);
		}
		
		return composite;
	}
	
	public static String getText(String text, int mnemonic)
	{
		if (mnemonic == -1)
		{
			return text;
		}
		
		int index = text.indexOf(mnemonic);
		
		if (index == -1)
		{
			index = text.indexOf(Character.isUpperCase((char) mnemonic) ? Character.toLowerCase((char) mnemonic)
				: Character.toUpperCase((char) mnemonic));
		}
		
		if (index == -1)
		{
			return text;
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(text.substring(0, index));
		builder.append('&');
		builder.append(text.substring(index));
		return builder.toString();
	}
	
	public static int getStyle(Orientation orient)
	{
		return (orient == Orientation.HORIZONTAL) ? SWT.HORIZONTAL : SWT.VERTICAL;
	}
	
	public static org.eclipse.swt.graphics.Image getSWTImage(Widget parent, Image image)
	{
		if (image == null)
		{
			return null;
		}
		
		org.eclipse.swt.graphics.Image swtImage = new org.eclipse.swt.graphics.Image(Display.getDefault(),
			new ByteArrayInputStream(image.getData()));
		
		swtImage.setBackground(getBackground(parent));
		return swtImage;
	}
	
	public static Color getBackground(Widget widget)
	{
		if (widget instanceof Control)
		{
			return ((Control) widget).getBackground();
		}
		
		if (widget instanceof TableItem)
		{
			return ((TableItem) widget).getBackground();
		}
		
		if (widget instanceof TreeItem)
		{
			return ((TreeItem) widget).getBackground();
		}
		
		if (widget instanceof ToolItem)
		{
			return ((ToolItem) widget).getParent().getBackground();
		}
		
		if (widget instanceof CoolItem)
		{
			return ((CoolItem) widget).getParent().getBackground();
		}
		
		return null;
	}
	
	public static void asyncExec(Runnable runnable)
	{
		Display.getDefault().asyncExec(new DebugRunnable(runnable));
	}
	
	public static Object asyncExec(ValuedRunnable runnable)
	{
		Display.getDefault().asyncExec(new DebugRunnable(runnable));
		return runnable.getValue();
	}
	
	public static void syncExec(Runnable runnable)
	{
		Display.getDefault().syncExec(new DebugRunnable(runnable));
	}
	
	public static Object syncExec(ValuedRunnable runnable)
	{
		Display.getDefault().syncExec(new DebugRunnable(runnable));
		return runnable.getValue();
	}
}
