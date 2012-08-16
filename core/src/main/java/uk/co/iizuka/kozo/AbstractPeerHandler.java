/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/AbstractPeerHandler.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractPeerHandler.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractPeerHandler implements PeerHandler
{
	// fields -----------------------------------------------------------------
	
	private final PeerManager peerManager;
	
	// constructors -----------------------------------------------------------
	
	public AbstractPeerHandler(PeerManager peerManager)
	{
		this.peerManager = peerManager;
	}
	
	// public methods ---------------------------------------------------------
	
	public PeerManager getPeerManager()
	{
		return peerManager;
	}
}
