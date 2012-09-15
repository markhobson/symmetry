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
