/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDAttrTypeParserTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests <code>DTDAttrTypeParser</code>.
 * 
 * @author Mark Hobson
 * @version $Id: DTDAttrTypeParserTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
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
