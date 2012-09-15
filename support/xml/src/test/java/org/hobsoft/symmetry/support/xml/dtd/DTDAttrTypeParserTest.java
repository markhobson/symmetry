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
package org.hobsoft.symmetry.support.xml.dtd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>DTDAttrTypeParser</code>.
 * 
 * @author Mark Hobson
 * @see DTDAttrTypeParser
 */
public class DTDAttrTypeParserTest
{
	// parse tests ------------------------------------------------------------
	
	@Test
	public void parseCData() throws DTDParseException
	{
		assertEquals(DTDAttrTypes.CDATA, DTDAttrTypeParser.parse("CDATA"));
	}

	@Test
	public void parseId() throws DTDParseException
	{
		assertEquals(DTDAttrTypes.ID, DTDAttrTypeParser.parse("ID"));
	}

	@Test
	public void parseIdRef() throws DTDParseException
	{
		assertEquals(DTDAttrTypes.IDREF, DTDAttrTypeParser.parse("IDREF"));
	}

	@Test
	public void parseIdRefs() throws DTDParseException
	{
		assertEquals(DTDAttrTypes.IDREFS, DTDAttrTypeParser.parse("IDREFS"));
	}

	@Test
	public void parseEntity() throws DTDParseException
	{
		assertEquals(DTDAttrTypes.ENTITY, DTDAttrTypeParser.parse("ENTITY"));
	}

	@Test
	public void parseEntities() throws DTDParseException
	{
		assertEquals(DTDAttrTypes.ENTITIES, DTDAttrTypeParser.parse("ENTITIES"));
	}

	@Test
	public void parseNMToken() throws DTDParseException
	{
		assertEquals(DTDAttrTypes.NMTOKEN, DTDAttrTypeParser.parse("NMTOKEN"));
	}

	@Test
	public void parseNMTokens() throws DTDParseException
	{
		assertEquals(DTDAttrTypes.NMTOKENS, DTDAttrTypeParser.parse("NMTOKENS"));
	}

	@Test
	public void parseEnumeration() throws DTDParseException
	{
		DTDEnumAttrType expected = new DTDEnumAttrType();
		expected.addValue("a");
		expected.addValue("b");
		expected.addValue("c");
		
		assertEquals(expected, DTDAttrTypeParser.parse("(a|b|c)"));
	}

	@Test
	public void parseNotation() throws DTDParseException
	{
		DTDNotationAttrType expected = new DTDNotationAttrType();
		expected.addValue("a");
		expected.addValue("b");
		expected.addValue("c");
		
		assertEquals(expected, DTDAttrTypeParser.parse("NOTATION (a|b|c)"));
	}
}
