/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingUtils.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import javax.swing.SwingConstants;

import org.hobsoft.symmetry.ui.Orientation;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingUtils.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public final class SwingUtils
{
	// constructors -----------------------------------------------------------
	
	private SwingUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static int getOrientation(Orientation orient)
	{
		return (orient == Orientation.HORIZONTAL) ? SwingConstants.HORIZONTAL : SwingConstants.VERTICAL;
	}
}
