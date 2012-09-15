/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/StateSession.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyChangeListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StateSession.java 92869 2011-09-23 11:10:31Z mark@IIZUKA.CO.UK $
 */
public interface StateSession extends PropertyChangeListener
{
	Object getRoot();
	
	State getState();
	
	void close();
}
