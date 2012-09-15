/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.bean;

import java.util.EventListener;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code EventListeners}.
 * 
 * @author Mark Hobson
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
