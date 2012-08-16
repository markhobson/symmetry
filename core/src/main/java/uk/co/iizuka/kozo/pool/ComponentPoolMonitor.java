/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/pool/ComponentPoolMonitor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.pool;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComponentPoolMonitor.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface ComponentPoolMonitor
{
	void componentInstantiated(Object component);
	
	void componentInstantiationException(Exception exception);
	
	void componentInvalidated(Object component);
	
	void componentBorrowed(Object component);
	
	void componentReturned(Object component);
}
