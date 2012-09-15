/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/pool/ComponentPoolMonitor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.pool;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface ComponentPoolMonitor
{
	void componentInstantiated(Object component);
	
	void componentInstantiationException(Exception exception);
	
	void componentInvalidated(Object component);
	
	void componentBorrowed(Object component);
	
	void componentReturned(Object component);
}
