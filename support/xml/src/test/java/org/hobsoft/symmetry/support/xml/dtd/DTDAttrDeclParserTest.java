/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDAttrDeclParserTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests <code>DTDAttrDeclParser</code>.
 * 
 * @author Mark Hobson
 * @see DTDAttrDeclParser
 */
public class DTDAttrDeclParserTest
{
	// parse tests ------------------------------------------------------------
	
	@Test
	public void parseRequired()
	{
		assertEquals(DTDAttrDecls.REQUIRED, DTDAttrDeclParser.parse("#REQUIRED", null));
	}

	@Test
	public void parseImplied()
	{
		assertEquals(DTDAttrDecls.IMPLIED, DTDAttrDeclParser.parse("#IMPLIED", null));
	}

	@Test
	public void parseFixed()
	{
		assertEquals(new DTDFixedAttrDecl("a"), DTDAttrDeclParser.parse("#FIXED", "a"));
	}

	@Test
	public void parseDefault()
	{
		assertEquals(new DTDDefaultAttrDecl("a"), DTDAttrDeclParser.parse(null, "a"));
	}
}
