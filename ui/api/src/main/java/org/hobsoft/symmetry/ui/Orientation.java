/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Orientation.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

/**
 * A type-safe enumeration of component orientations.
 * 
 * @author Mark Hobson
 * @see org.hobsoft.symmetry.ui.Box#setOrientation(Orientation)
 */
public enum Orientation
{
	// constants --------------------------------------------------------------
	
	/**
	 * Instance that orients a component along the x-axis.
	 */
	HORIZONTAL,
	
	/**
	 * Instance that orients a component along the y-axis.
	 */
	VERTICAL;
}
