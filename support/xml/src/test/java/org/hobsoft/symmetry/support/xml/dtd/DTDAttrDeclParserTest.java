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
