/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/SelectionMode.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

/**
 * A type-safe enumeration of data model selection modes.
 * 
 * @author Mark Hobson
 * @version $Id: SelectionMode.java 97978 2012-01-24 16:11:50Z mark@IIZUKA.CO.UK $
 * @see Tree#setSelectionMode(SelectionMode)
 */
public enum SelectionMode
{
	// TODO: support contiguous ranges?
	
	// constants --------------------------------------------------------------
	
	/**
	 * Instance that allows at most one data entry to be selected.
	 */
	SINGLE,
	
	/**
	 * Instance that allows multiple data entries to be selected.
	 */
	MULTIPLE;
}
