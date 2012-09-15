/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/functor/BeanPropertyEvaluatorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

import org.hobsoft.symmetry.ui.StubBean;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code BeanPropertyEvaluator}.
 * 
 * @author Mark Hobson
 * @see BeanPropertyEvaluator
 */
public class BeanPropertyEvaluatorTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void evaluateWithNullString()
	{
		assertNull(BeanPropertyEvaluator.evaluate(null, createBean()));
	}
	
	@Test
	public void evaluateWithNullBean()
	{
		assertEquals("a", BeanPropertyEvaluator.evaluate("a", null));
	}
	
	@Test
	public void evaluateWithPropertyToken()
	{
		assertEquals("a", BeanPropertyEvaluator.evaluate("{name}", createBean("a")));
	}
	
	@Test
	public void evaluateWithPropertyTokenWhenNull()
	{
		assertEquals("null", BeanPropertyEvaluator.evaluate("{name}", createBean(null)));
	}
	
	@Test
	public void evaluateWithPropertyTokenWhenNotFound()
	{
		assertEquals("{a}", BeanPropertyEvaluator.evaluate("{a}", createBean()));
	}
	
	@Test
	public void evaluateWithPropertyTokenAndPrecedingText()
	{
		assertEquals("ab", BeanPropertyEvaluator.evaluate("a{name}", createBean("b")));
	}
	
	@Test
	public void evaluateWithPropertyTokenAndFollowingText()
	{
		assertEquals("ab", BeanPropertyEvaluator.evaluate("{name}b", createBean("a")));
	}
	
	@Test
	public void evaluateWithPropertyTokenAndNullBean()
	{
		assertEquals("{name}", BeanPropertyEvaluator.evaluate("{name}", null));
	}
	
	// private methods --------------------------------------------------------
	
	private static StubBean createBean()
	{
		return new StubBean();
	}
	
	private static StubBean createBean(String name)
	{
		StubBean bean = createBean();
		bean.setName(name);
		return bean;
	}
}
