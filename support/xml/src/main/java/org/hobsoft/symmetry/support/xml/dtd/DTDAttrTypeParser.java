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

/**
 * Builds <code>DTDAttrType</code> instances from their DTD string representations.
 * 
 * @author Mark Hobson
 * @see DTDAttrType
 */
public final class DTDAttrTypeParser
{
	// constructors -----------------------------------------------------------
	
	private DTDAttrTypeParser()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------

	public static DTDAttrType parse(String string) throws DTDParseException
	{
		if ("CDATA".equals(string))
		{
			return DTDAttrTypes.CDATA;
		}
		
		if ("ID".equals(string))
		{
			return DTDAttrTypes.ID;
		}
		
		if ("IDREF".equals(string))
		{
			return DTDAttrTypes.IDREF;
		}
		
		if ("IDREFS".equals(string))
		{
			return DTDAttrTypes.IDREFS;
		}
		
		if ("ENTITY".equals(string))
		{
			return DTDAttrTypes.ENTITY;
		}
		
		if ("ENTITIES".equals(string))
		{
			return DTDAttrTypes.ENTITIES;
		}
		
		if ("NMTOKEN".equals(string))
		{
			return DTDAttrTypes.NMTOKEN;
		}
		
		if ("NMTOKENS".equals(string))
		{
			return DTDAttrTypes.NMTOKENS;
		}
		
		StringScanner scanner = new StringScanner(string, "()|");
		
		boolean notation = scanner.hasNext("NOTATION ");
		
		if (notation)
		{
			scanner.next();
		}
		
		if (!scanner.hasNext("("))
		{
			throw new DTDParseException("Expected '('", scanner.getIndex());
		}
		
		scanner.next();
		
		DTDValuedAttrType attrType = notation ? new DTDNotationAttrType() : new DTDEnumAttrType();
		
		boolean end = false;
		
		while (!end)
		{
			if (!scanner.hasNext())
			{
				throw new DTDParseException("Expected name", scanner.getIndex());
			}
			
			attrType.addValue(scanner.next());
			
			if (scanner.hasNext(")"))
			{
				end = true;
			}
			else
			{
				if (!scanner.hasNext("|"))
				{
					throw new DTDParseException("Expected '|'" + scanner.next(), scanner.getIndex());
				}
				
				scanner.next();
			}
		}
		
		return attrType;
	}
}
