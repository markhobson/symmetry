/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Enableable.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface Enableable
{
	// constants --------------------------------------------------------------
	
	String ENABLED_PROPERTY = "enabled";
	
	// methods ----------------------------------------------------------------
	
	boolean isEnabled();
	
	void setEnabled(boolean enabled);
}
