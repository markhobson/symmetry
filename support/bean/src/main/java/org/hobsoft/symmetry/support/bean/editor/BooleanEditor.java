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

/**
 * 
 * 
 * @author Mark Hobson
 */
public class BooleanEditor extends PropertyEditorSupport2
{
	// TODO: add configurable true/false values
	// TODO: make package private?
	
	// constants --------------------------------------------------------------
	
	private static final String TRUE_TEXT = "true";
	
	private static final String FALSE_TEXT = "false";

	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		String text;
		
		Object value = getValue();
		
		if (Boolean.TRUE.equals(value))
		{
			text = TRUE_TEXT;
		}
		else if (Boolean.FALSE.equals(value))
		{
			text = FALSE_TEXT;
		}
		else
		{
			text = null;
		}
		
		return text;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		Boolean value;
		
		if (text == null)
		{
			value = null;
		}
		else if (TRUE_TEXT.equals(text))
		{
			value = Boolean.TRUE;
		}
		else if (FALSE_TEXT.equals(text))
		{
			value = Boolean.FALSE;
		}
		else
		{
			throw new IllegalArgumentException("Cannot parse boolean: " + text);
		}
		
		setValue(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTags()
	{
		return new String[] {TRUE_TEXT, FALSE_TEXT};
	}
}
