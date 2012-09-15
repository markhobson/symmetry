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
package org.hobsoft.symmetry.support.bean.editor;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class NumberEditor extends PropertyEditorSupport2
{
	// fields -----------------------------------------------------------------
	
	private final NumberFormat formatter;
	
	// constructors -----------------------------------------------------------
	
	public NumberEditor(NumberFormat formatter)
	{
		this.formatter = formatter;
	}
	
	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		Object value = getValue();
		
		return (value != null) ? formatter.format(value) : null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		setValue(parse(text));
	}
	
	// public methods ---------------------------------------------------------
	
	public NumberFormat getNumberFormat()
	{
		return formatter;
	}
	
	// private methods --------------------------------------------------------
	
	private Number parse(String text)
	{
		if (text == null)
		{
			return null;
		}
		
		try
		{
			return formatter.parse(text);
		}
		catch (ParseException exception)
		{
			throw new IllegalArgumentException("Cannot parse number: " + text, exception);
		}
	}
}
