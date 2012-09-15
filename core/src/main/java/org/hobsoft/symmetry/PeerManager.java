/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/PeerManager.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface PeerManager
{
	void registerComponent(Object component);
	
	Object getPeer(Object component);
}
