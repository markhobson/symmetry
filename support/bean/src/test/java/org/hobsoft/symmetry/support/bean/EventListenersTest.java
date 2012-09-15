/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/EventListenersTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.EventListener;

import org.junit.Test;

/**
 * Tests {@code EventListeners}.
 * 
 * @author Mark Hobson
 * @version $Id: EventListenersTest.java 92540 2011-09-20 09:25:58Z mark@IIZUKA.CO.UK $
 * @see EventListeners
 */
public class EventListenersTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void unproxyListenerWithListener()
	{
		EventListener listener = new DummyEventListener();
		
		assertEquals(listener, EventListeners.unproxy(listener));
	}
	
	@Test
	public void unproxyListenerWithListenerProxy()
	{
		EventListener proxy = new DummyEventListener();
		EventListener listener = new DummyEventListenerProxy(proxy);
		
		assertEquals(proxy, EventListeners.unproxy(listener));
	}
	
	@Test
	public void unproxyListenerWithListenerProxyProxy()
	{
		EventListener proxy = new DummyEventListener();
		EventListener proxyProxy = new DummyEventListenerProxy(proxy);
		EventListener listener = new DummyEventListenerProxy(proxyProxy);
		
		assertEquals(proxy, EventListeners.unproxy(listener));
	}
	
	@Test
	public void unproxyListenersWithListener()
	{
		EventListener listener = new DummyEventListener();
		
		assertArrayEquals(new EventListener[] {listener}, EventListeners.unproxy(new EventListener[] {listener}));
	}
	
	@Test
	public void unproxyListenersWithListenerProxy()
	{
		EventListener proxy = new DummyEventListener();
		EventListener listener = new DummyEventListenerProxy(proxy);
		
		assertArrayEquals(new EventListener[] {proxy}, EventListeners.unproxy(new EventListener[] {listener}));
	}
	
	@Test
	public void unproxyListenersWithListenerProxyProxy()
	{
		EventListener proxy = new DummyEventListener();
		EventListener proxyProxy = new DummyEventListenerProxy(proxy);
		EventListener listener = new DummyEventListenerProxy(proxyProxy);
		
		assertArrayEquals(new EventListener[] {proxy}, EventListeners.unproxy(new EventListener[] {listener}));
	}
}
