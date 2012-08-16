/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Action.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import java.beans.PropertyChangeListener;

import uk.co.iizuka.kozo.ui.event.ActionListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Action.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface Action extends ActionListener
{
	// constants --------------------------------------------------------------
	
	String NAME_PROPERTY = "name";
	
	String DESCRIPTION_PROPERTY = "description";
	
	String IMAGE_PROPERTY = "image";
	
	String MNEMONIC_PROPERTY = "mnemonic";
	
	String ENABLED_PROPERTY = "enabled";
	
	// public methods ---------------------------------------------------------
	
	void addPropertyChangeListener(PropertyChangeListener listener);
	
	void removePropertyChangeListener(PropertyChangeListener listener);
	
	String getName();
	
	String getDescription();
	
	Image getImage();
	
	int getMnemonic();
	
	boolean isEnabled();
}
