/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/AttrFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * Tests <code>AttrFilter</code>.
 * 
 * @author Mark Hobson
 * @see AttrFilter
 */
public class AttrFilterTest extends AbstractNodeFilterTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void attr()
	{
		assertAccept(new AttrFilter("a"), createAttributeNS(null, "a"));
	}
	
	@Test
	public void attrWithDifferentLocalName()
	{
		assertSkip(new AttrFilter("a"), createAttributeNS(null, "b"));
	}
	
	@Test
	public void namespacedAttr()
	{
		assertAccept(new AttrFilter("ns", "a"), createAttributeNS("ns", "a"));
	}
	
	@Test
	public void namespacedAttrWithDifferentNamespaceURI()
	{
		assertSkip(new AttrFilter("ns", "a"), createAttributeNS("ns2", "a"));
	}
	
	@Test
	public void namespacedPrefixedAttr()
	{
		assertAccept(new AttrFilter("ns", "a"), createAttributeNS("ns", "p:a"));
	}
	
	@Test
	public void valuedAttr()
	{
		assertAccept(new AttrFilter(null, "a", "x"), createAttributeNS(null, "a", "x"));
	}
	
	@Test
	public void valuedAttrWithDifferentLocalName()
	{
		assertSkip(new AttrFilter(null, "a", "x"), createAttributeNS(null, "b", "x"));
	}
	
	@Test
	public void valuedAttrWithDifferentValue()
	{
		assertSkip(new AttrFilter(null, "a", "x"), createAttributeNS(null, "a", "y"));
	}
	
	@Test
	public void elementAttr()
	{
		assertAccept(new AttrFilter("a"), createElementAttributeNS(null, "a"));
	}
	
	@Test
	public void elementAttrWithDifferentLocalName()
	{
		assertSkip(new AttrFilter("a"), createElementAttributeNS(null, "b"));
	}
	
	@Test
	public void elementNamespacedAttr()
	{
		assertAccept(new AttrFilter("ns", "a"), createElementAttributeNS("ns", "a"));
	}
	
	@Test
	public void elementNamespacedAttrWithDifferentNamespaceURI()
	{
		assertSkip(new AttrFilter("ns", "a"), createElementAttributeNS("ns2", "a"));
	}
	
	@Test
	public void elementValuedAttr()
	{
		assertAccept(new AttrFilter(null, "a", "x"), createElementAttributeNS(null, "a", "x"));
	}
	
	@Test
	public void elementValuedAttrWithDifferentLocalName()
	{
		assertSkip(new AttrFilter(null, "a", "x"), createElementAttributeNS(null, "b", "x"));
	}
	
	@Test
	public void elementValuedAttrWithDifferentValue()
	{
		assertSkip(new AttrFilter(null, "a", "x"), createElementAttributeNS(null, "a", "y"));
	}
	
	@Test
	public void toStringWithLocalName()
	{
		assertEquals("attr[uri=null,name=a]", new AttrFilter("a").toString());
	}
	
	@Test
	public void toStringWithNamespaceURI()
	{
		assertEquals("attr[uri=ns,name=a]", new AttrFilter("ns", "a").toString());
	}
	
	@Test
	public void toStringWithValue()
	{
		assertEquals("attr[uri=null,name=a,value=x]", new AttrFilter(null, "a", "x").toString());
	}
	
	// private methods --------------------------------------------------------
	
	private Attr createAttributeNS(String namespaceURI, String qualifiedName)
	{
		return createAttributeNS(namespaceURI, qualifiedName, null);
	}
	
	private Attr createAttributeNS(String namespaceURI, String qualifiedName, String value)
	{
		Attr attr = getDocument().createAttributeNS(namespaceURI, qualifiedName);
		
		if (value != null)
		{
			attr.setValue(value);
		}
		
		return attr;
	}

	private Element createElementAttributeNS(String namespaceURI, String qualifiedName)
	{
		return createElementAttributeNS(namespaceURI, qualifiedName, null);
	}

	private Element createElementAttributeNS(String namespaceURI, String qualifiedName, String value)
	{
		Attr attr = createAttributeNS(namespaceURI, qualifiedName, value);
		
		Element element = getDocument().createElementNS(null, "element");
		element.setAttributeNodeNS(attr);
		
		return element;
	}
}
