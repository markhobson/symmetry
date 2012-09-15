/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/SAXAssert.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import org.junit.Assert;
import org.xml.sax.Attributes;

/**
 * Provides methods to assert the equality of SAX objects.
 * 
 * @author Mark Hobson
 */
public final class SAXAssert
{
	// constructors -----------------------------------------------------------
	
	private SAXAssert()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------

	public static void assertEquals(Attributes expected, Attributes actual)
	{
		// assert length
		int length = expected.getLength();
		Assert.assertEquals("Attributes length", length, actual.getLength());
		
		// assert attributes
		for (int i = 0; i < length; i++)
		{
			Assert.assertEquals("Attribute namespace URI", expected.getURI(i), actual.getURI(i));
			Assert.assertEquals("Attribute local name", expected.getLocalName(i), actual.getLocalName(i));
			Assert.assertEquals("Attribute qualified name", expected.getQName(i), actual.getQName(i));
			Assert.assertEquals("Attribute type", expected.getType(i), actual.getType(i));
			Assert.assertEquals("Attribute value", expected.getValue(i), actual.getValue(i));
		}
	}
}
