/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/PeerHandler.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry;

import java.beans.PropertyChangeListener;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface PeerHandler extends PropertyChangeListener
{
	// TODO: rename
	
	Object createPeer(Object component);
}
