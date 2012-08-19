/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDAttrTypeParser.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * Builds <code>DTDAttrType</code> instances from their DTD string representations.
 * 
 * @author Mark Hobson
 * @version $Id: DTDAttrTypeParser.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
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
