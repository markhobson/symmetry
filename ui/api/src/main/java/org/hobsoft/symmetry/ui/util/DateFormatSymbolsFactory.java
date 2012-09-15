/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/util/DateFormatSymbolsFactory.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.util;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class DateFormatSymbolsFactory
{
	// fields -----------------------------------------------------------------
	
	private static Map<Locale, DateFormatSymbols> symbolsMap;
	
	static
	{
		symbolsMap = new HashMap<Locale, DateFormatSymbols>();
	}
	
	// constructors -----------------------------------------------------------
	
	private DateFormatSymbolsFactory()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static DateFormatSymbols getInstance()
	{
		return getInstance(Locale.getDefault());
	}
	
	public static DateFormatSymbols getInstance(Locale locale)
	{
		DateFormatSymbols symbols;
		
		synchronized (symbolsMap)
		{
			symbols = symbolsMap.get(locale);
			
			if (symbols == null)
			{
				symbols = new DateFormatSymbols(locale);
				symbolsMap.put(locale, symbols);
			}
		}
		
		return symbols;
	}
}
