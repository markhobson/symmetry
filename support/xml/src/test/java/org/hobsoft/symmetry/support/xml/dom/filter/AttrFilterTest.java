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
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import static org.junit.Assert.assertEquals;

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
