/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/stax/filter/AttributeEventFilterTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.stax.filter;

import org.hobsoft.symmetry.support.xml.stax.AbstractXMLEventTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests {@code AttributeEventFilter}.
 * 
 * @author Mark Hobson
 * @see AttributeEventFilter
 */
public class AttributeEventFilterTest extends AbstractXMLEventTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void acceptWithAttribute()
	{
		AttributeEventFilter filter = new AttributeEventFilter("a");
		
		assertTrue(filter.accept(createAttribute("a")));
	}
	
	@Test
	public void acceptWithNamespacedAttribute()
	{
		AttributeEventFilter filter = new AttributeEventFilter("u", "a");
		
		assertTrue(filter.accept(createNamespacedAttribute("u", "a")));
	}
	
	@Test
	public void acceptWithPrefixedAttribute()
	{
		AttributeEventFilter filter = new AttributeEventFilter("u", "a", "p");
		
		assertTrue(filter.accept(createNamespacedAttribute("u", "a", "p")));
	}

	@Test
	public void acceptWithAttributeAndLocalNameWildcard()
	{
		AttributeEventFilter filter = new AttributeEventFilter("*");
		
		assertTrue(filter.accept(createAttribute()));
	}
	
	@Test
	public void acceptWithAttributeAndNamespaceUriWildcard()
	{
		AttributeEventFilter filter = new AttributeEventFilter("*", "a");
		
		assertTrue(filter.accept(createNamespacedAttribute("a")));
	}
}
