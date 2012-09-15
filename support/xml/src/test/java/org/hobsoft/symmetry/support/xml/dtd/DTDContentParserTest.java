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
 * Tests <code>DTDContentParser</code>.
 * 
 * @author Mark Hobson
 * @see DTDContentParser
 */
public class DTDContentParserTest
{
	// parse tests ------------------------------------------------------------
	
	@Test(expected = DTDParseException.class)
	public void parseEmptyString() throws DTDParseException
	{
		DTDContentParser.parse("");
	}

	@Test
	public void parseEmpty() throws DTDParseException
	{
		assertEquals(DTDEmptyContent.INSTANCE, DTDContentParser.parse("EMPTY"));
	}

	@Test
	public void parseAny() throws DTDParseException
	{
		assertEquals(DTDAnyContent.INSTANCE, DTDContentParser.parse("ANY"));
	}

	@Test
	public void parseChoice() throws DTDParseException
	{
		DTDChoiceContent expected = new DTDChoiceContent();
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		expected.addChild(new DTDNameContent("c"));
		
		assertEquals(expected, DTDContentParser.parse("(a|b|c)"));
	}

	@Test
	public void parseChoiceOneOrMore() throws DTDParseException
	{
		DTDChoiceContent expected = new DTDChoiceContent(DTDCardinality.ONE_OR_MORE);
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a|b)+"));
	}

	@Test
	public void parseChoiceZeroOrMore() throws DTDParseException
	{
		DTDChoiceContent expected = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a|b)*"));
	}

	@Test
	public void contentModelChoiceZeroOrOne() throws DTDParseException
	{
		DTDChoiceContent expected = new DTDChoiceContent(DTDCardinality.ZERO_OR_ONE);
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a|b)?"));
	}

	@Test
	public void parseChoiceNameOneOrMore() throws DTDParseException
	{
		DTDChoiceContent expected = new DTDChoiceContent();
		expected.addChild(new DTDNameContent("a", DTDCardinality.ONE_OR_MORE));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a+|b)"));
	}

	@Test
	public void parseChoiceNameZeroOrMore() throws DTDParseException
	{
		DTDChoiceContent expected = new DTDChoiceContent();
		expected.addChild(new DTDNameContent("a", DTDCardinality.ZERO_OR_MORE));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a*|b)"));
	}

	@Test
	public void parseChoiceNameZeroOrOne() throws DTDParseException
	{
		DTDChoiceContent expected = new DTDChoiceContent();
		expected.addChild(new DTDNameContent("a", DTDCardinality.ZERO_OR_ONE));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a?|b)"));
	}

	@Test
	public void parseSingleSequence() throws DTDParseException
	{
		DTDSequenceContent expected = new DTDSequenceContent();
		expected.addChild(new DTDNameContent("a"));
		
		assertEquals(expected, DTDContentParser.parse("(a)"));
	}

	@Test
	public void parseSequence() throws DTDParseException
	{
		DTDSequenceContent expected = new DTDSequenceContent();
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		expected.addChild(new DTDNameContent("c"));
		
		assertEquals(expected, DTDContentParser.parse("(a,b,c)"));
	}

	@Test
	public void parseSequenceOneOrMore() throws DTDParseException
	{
		DTDSequenceContent expected = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a,b)+"));
	}

	@Test
	public void parseSequenceZeroOrMore() throws DTDParseException
	{
		DTDSequenceContent expected = new DTDSequenceContent(DTDCardinality.ZERO_OR_MORE);
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a,b)*"));
	}

	@Test
	public void parseSequenceZeroOrOne() throws DTDParseException
	{
		DTDSequenceContent expected = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a,b)?"));
	}

	@Test
	public void parseSequenceNameOneOrMore() throws DTDParseException
	{
		DTDSequenceContent expected = new DTDSequenceContent();
		expected.addChild(new DTDNameContent("a", DTDCardinality.ONE_OR_MORE));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a+,b)"));
	}

	@Test
	public void parseSequenceNameZeroOrMore() throws DTDParseException
	{
		DTDSequenceContent expected = new DTDSequenceContent();
		expected.addChild(new DTDNameContent("a", DTDCardinality.ZERO_OR_MORE));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a*,b)"));
	}

	@Test
	public void parseSequenceNameZeroOrOne() throws DTDParseException
	{
		DTDSequenceContent expected = new DTDSequenceContent();
		expected.addChild(new DTDNameContent("a", DTDCardinality.ZERO_OR_ONE));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(a?,b)"));
	}

	@Test(expected = DTDParseException.class)
	public void parseChoiceAndSequence() throws DTDParseException
	{
		DTDContentParser.parse("(a,b|c)");
	}

	@Test
	public void parseComplex() throws DTDParseException
	{
		DTDChoiceContent choice = new DTDChoiceContent(DTDCardinality.ZERO_OR_MORE);
		choice.addChild(new DTDNameContent("b", DTDCardinality.ZERO_OR_MORE));
		choice.addChild(new DTDNameContent("c", DTDCardinality.ZERO_OR_ONE));
		
		DTDSequenceContent sequence = new DTDSequenceContent(DTDCardinality.ZERO_OR_ONE);
		sequence.addChild(new DTDNameContent("d"));
		sequence.addChild(new DTDNameContent("e", DTDCardinality.ONE_OR_MORE));
		sequence.addChild(new DTDNameContent("f", DTDCardinality.ZERO_OR_MORE));
		
		DTDSequenceContent expected = new DTDSequenceContent(DTDCardinality.ONE_OR_MORE);
		expected.addChild(new DTDNameContent("a", DTDCardinality.ONE_OR_MORE));
		expected.addChild(choice);
		expected.addChild(sequence);
		
		assertEquals(expected, DTDContentParser.parse("(a+,(b*|c?)*,(d,e+,f*)?)+"));
	}

	@Test
	public void parseMixedPCData() throws DTDParseException
	{
		DTDMixedContent expected = new DTDMixedContent();
		
		assertEquals(expected, DTDContentParser.parse("(#PCDATA)"));
	}

	@Test
	public void parseMixedPCDataWithAsterisk() throws DTDParseException
	{
		DTDMixedContent expected = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		
		assertEquals(expected, DTDContentParser.parse("(#PCDATA)*"));
	}

	@Test
	public void parseMixedPCDataNames() throws DTDParseException
	{
		DTDMixedContent expected = new DTDMixedContent(DTDCardinality.ZERO_OR_MORE);
		expected.addChild(new DTDNameContent("a"));
		expected.addChild(new DTDNameContent("b"));
		
		assertEquals(expected, DTDContentParser.parse("(#PCDATA|a|b)*"));
	}

	@Test(expected = DTDParseException.class)
	public void parseMixedPCDataNamesWithNoAsterisk() throws DTDParseException
	{
		DTDContentParser.parse("(#PCDATA|a|b)");
	}
}
