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
