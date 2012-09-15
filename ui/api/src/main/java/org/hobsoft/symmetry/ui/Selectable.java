/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Selectable.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Selectable.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface Selectable
{
	// constants --------------------------------------------------------------
	
	String SELECTED_PROPERTY = "selected";
	
	// methods ----------------------------------------------------------------
	
	boolean isSelected();
	
	void setSelected(boolean selected);
	
	// TODO: Closure select();
}
