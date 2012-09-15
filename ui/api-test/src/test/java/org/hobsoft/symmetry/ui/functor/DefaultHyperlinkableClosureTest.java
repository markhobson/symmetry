/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/functor/DefaultHyperlinkableClosureTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hobsoft.symmetry.ui.StubBean;
import org.junit.Test;

/**
 * Tests {@code DefaultHyperlinkableClosure}.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultHyperlinkableClosureTest.java 88711 2011-06-03 15:44:30Z mark@IIZUKA.CO.UK $
 * @see DefaultHyperlinkableClosure
 */
public class DefaultHyperlinkableClosureTest
{
	// TODO: test method and asynchronous properties
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void construct()
	{
		assertEquals("a", new DefaultHyperlinkableClosure<Void>("a").toHyperlink(null));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructWithNull()
	{
		new DefaultHyperlinkableClosure<Void>(null);
	}
	
	@Test
	public void toHyperlinkWithNull()
	{
		assertEquals("a", new DefaultHyperlinkableClosure<Void>("a").toHyperlink(null));
	}
	
	@Test
	public void toHyperlinkWithObject()
	{
		assertEquals("a", new DefaultHyperlinkableClosure<Object>("a").toHyperlink(new Object()));
	}
	
	@Test
	public void toHyperlinkWithPropertyToken()
	{
		StubBean bean = new StubBean();
		bean.setName("a");
		
		assertEquals("a", new DefaultHyperlinkableClosure<Object>("{name}").toHyperlink(bean));
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		assertEquals(new DefaultHyperlinkableClosure<Void>("a").hashCode(),
			new DefaultHyperlinkableClosure<Void>("a").hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		assertTrue(new DefaultHyperlinkableClosure<Void>("a").equals(new DefaultHyperlinkableClosure<Void>("a")));
	}
	
	@Test
	public void equalsWhenNotEqual()
	{
		assertFalse(new DefaultHyperlinkableClosure<Void>("a").equals(new DefaultHyperlinkableClosure<Void>("b")));
	}
	
	@Test
	public void toStringTest()
	{
		String expected = "org.hobsoft.symmetry.ui.functor.DefaultHyperlinkableClosure[hyperlink=a, method=GET, "
			+ "asynchronous=false]";
		
		assertEquals(expected, new DefaultHyperlinkableClosure<Void>("a").toString());
	}
}
