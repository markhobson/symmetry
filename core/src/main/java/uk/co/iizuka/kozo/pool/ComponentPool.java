/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/pool/ComponentPool.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.pool;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComponentPool.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface ComponentPool
{
	Object borrowComponent() throws ComponentPoolException;
	
	void invalidateComponent(Object component) throws ComponentPoolException;
	
	void returnComponent(Object component) throws ComponentPoolException;
	
	void close() throws ComponentPoolException;
}
