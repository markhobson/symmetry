/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Enableable.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Enableable.java 86683 2011-04-07 22:02:49Z mark@IIZUKA.CO.UK $
 */
public interface Enableable
{
	// constants --------------------------------------------------------------
	
	String ENABLED_PROPERTY = "enabled";
	
	// methods ----------------------------------------------------------------
	
	boolean isEnabled();
	
	void setEnabled(boolean enabled);
}
