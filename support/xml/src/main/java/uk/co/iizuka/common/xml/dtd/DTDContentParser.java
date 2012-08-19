/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDContentParser.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * Builds <code>DTDContent</code> instances from their DTD string representations.
 * 
 * @author Mark Hobson
 * @version $Id: DTDContentParser.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see DTDContent
 */
public final class DTDContentParser
{
	// constructors -----------------------------------------------------------
	
	private DTDContentParser()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	public static DTDContent parse(String string) throws DTDParseException
	{
		StringScanner scanner = new StringScanner(string, "()?*+|,", true);
		
		if (!scanner.hasNext())
		{
			throw new DTDParseException("No model", scanner.getIndex());
		}
		
		int index = scanner.getIndex();
		String token = scanner.next();
		
		if ("EMPTY".equals(token))
		{
			return DTDEmptyContent.INSTANCE;
		}
		
		if ("ANY".equals(token))
		{
			return DTDAnyContent.INSTANCE;
		}
		
		if (!"(".equals(token))
		{
			throw new DTDParseException("Expected '('", index);
		}

		return scanner.hasNext("#PCDATA") ? parseMixed(scanner) : parseChildren(scanner);
	}
	
	// private methods --------------------------------------------------------
	
	private static DTDContentContainer parseChildren(StringScanner scanner) throws DTDParseException
	{
		DTDContentContainer container = parseChoiceOrSequence(scanner);
		
		container.setCardinality(parseCardinality(scanner));
		
		return container;
	}
	
	private static DTDContentContainer parseChoiceOrSequence(StringScanner scanner) throws DTDParseException
	{
		DTDContentContainer container = null;
		boolean end = false;
		
		while (!end)
		{
			DTDContent cp = parseContentParticle(scanner);
			int index = scanner.getIndex();
			String token = scanner.hasNext() ? scanner.next() : "";

			if (")".equals(token))
			{
				end = true;
			}
			else if ((container instanceof DTDChoiceContent) && !"|".equals(token))
			{
				throw new DTDParseException("Expected '|'", index);
			}
			else if ((container instanceof DTDSequenceContent) && !",".equals(token))
			{
				throw new DTDParseException("Expected ','", index);
			}
			
			if (container == null)
			{
				if ("|".equals(token))
				{
					container = new DTDChoiceContent();
				}
				else if (",".equals(token) || ")".equals(token))
				{
					container = new DTDSequenceContent();
				}
				else
				{
					throw new DTDParseException("Expected '|' or ','", index);
				}
			}
			
			container.addChild(cp);
		}
		
		return container;
	}
	
	private static DTDCardinalContent parseContentParticle(StringScanner scanner) throws DTDParseException
	{
		if (!scanner.hasNext())
		{
			throw new DTDParseException("Expected '(' or name", scanner.getIndex());
		}
		
		String token = scanner.next();
		DTDCardinalContent cardinal;
		
		if ("(".equals(token))
		{
			cardinal = parseChoiceOrSequence(scanner);
		}
		else
		{
			cardinal = new DTDNameContent(token);
		}
		
		cardinal.setCardinality(parseCardinality(scanner));
		
		return cardinal;
	}
	
	private static DTDCardinality parseCardinality(StringScanner scanner)
	{
		if (scanner.hasNext("+"))
		{
			scanner.next();
			return DTDCardinality.ONE_OR_MORE;
		}
		
		if (scanner.hasNext("*"))
		{
			scanner.next();
			return DTDCardinality.ZERO_OR_MORE;
		}
		
		if (scanner.hasNext("?"))
		{
			scanner.next();
			return DTDCardinality.ZERO_OR_ONE;
		}
		
		return DTDCardinality.ONCE;
	}
	
	private static DTDMixedContent parseMixed(StringScanner scanner) throws DTDParseException
	{
		if (!scanner.hasNext("#PCDATA"))
		{
			throw new DTDParseException("Expected '#PCDATA'", scanner.getIndex());
		}
		
		scanner.next();
		
		DTDMixedContent mixed = new DTDMixedContent();
		boolean pcdata = true;
		
		while (scanner.hasNext("|"))
		{
			scanner.next();
			
			if (!scanner.hasNext())
			{
				throw new DTDParseException("Expected name", scanner.getIndex());
			}
			
			mixed.addChild(new DTDNameContent(scanner.next()));
			
			pcdata = false;
		}
		
		if (!scanner.hasNext(")"))
		{
			throw new DTDParseException("Expected ')'", scanner.getIndex());
		}
		
		scanner.next();

		boolean hasAsterisk = scanner.hasNext("*");
		
		if (!pcdata && !hasAsterisk)
		{
			throw new DTDParseException("Expected '*'", scanner.getIndex());
		}
		
		if (hasAsterisk)
		{
			scanner.next();
			mixed.setCardinality(DTDCardinality.ZERO_OR_MORE);
		}
		
		return mixed;
	}
}
