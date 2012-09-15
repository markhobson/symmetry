/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/HtmlFiltersTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code HtmlFilters}.
 * 
 * @author Mark Hobson
 * @see HtmlFilters
 */
public class HtmlFiltersTest
{
	// fields -----------------------------------------------------------------
	
	private XMLEventFactory eventFactory;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		eventFactory = XMLEventFactory.newInstance();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void safeAcceptsP()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("P")));
	}
	
	@Test
	public void safeRejectsPWithAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("P", "id")));
	}
	
	@Test
	public void safeAcceptsH1()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("H1")));
	}
	
	@Test
	public void safeRejectsH1WithAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("H1", "id")));
	}
	
	@Test
	public void safeAcceptsH2()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("H2")));
	}
	
	@Test
	public void safeRejectsH2WithAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("H2", "id")));
	}
	
	@Test
	public void safeAcceptsH3()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("H3")));
	}
	
	@Test
	public void safeRejectsH3WithAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("H3", "id")));
	}
	
	@Test
	public void safeAcceptsH4()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("H4")));
	}
	
	@Test
	public void safeRejectsH4WithAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("H4", "id")));
	}
	
	@Test
	public void safeAcceptsH5()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("H5")));
	}
	
	@Test
	public void safeRejectsH5WithAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("H5", "id")));
	}
	
	@Test
	public void safeAcceptsH6()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("H6")));
	}
	
	@Test
	public void safeRejectsH6WithAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("H6", "id")));
	}
	
	@Test
	public void safeAcceptsBr()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("BR")));
	}
	
	@Test
	public void safeAcceptsA()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("A")));
	}
	
	@Test
	public void safeAcceptsAWithHref()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("A", "href")));
	}
	
	@Test
	public void safeAcceptsAWithTarget()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("A", "target")));
	}
	
	@Test
	public void safeRejectsAWithOtherAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("A", "id")));
	}
	
	@Test
	public void safeAcceptsImg()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("IMG")));
	}
	
	@Test
	public void safeAcceptsImgWithSrc()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("IMG", "src")));
	}
	
	@Test
	public void safeAcceptsImgWithAlt()
	{
		assertTrue(HtmlFilters.SAFE.accept(createStartElement("IMG", "alt")));
	}
	
	@Test
	public void safeRejectsImgWithOtherAttribute()
	{
		assertFalse(HtmlFilters.SAFE.accept(createStartElement("IMG", "id")));
	}
	
	// private methods --------------------------------------------------------
	
	private StartElement createStartElement(String localName, String... attributeLocalNames)
	{
		Iterator<Attribute> attributes = createAttributes(attributeLocalNames);
		
		return eventFactory.createStartElement("", Html.XMLNS, localName, attributes, null);
	}
	
	private Iterator<Attribute> createAttributes(String... localNames)
	{
		if (localNames.length == 0)
		{
			return null;
		}
		
		List<Attribute> attributes = new ArrayList<Attribute>();
		
		for (String localName : localNames)
		{
			Attribute attribute = eventFactory.createAttribute(localName, "x");
			
			attributes.add(attribute);
		}
		
		return attributes.iterator();
	}
}
