/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/PeerManager.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PeerManager.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface PeerManager
{
	void registerComponent(Object component);
	
	Object getPeer(Object component);
}
