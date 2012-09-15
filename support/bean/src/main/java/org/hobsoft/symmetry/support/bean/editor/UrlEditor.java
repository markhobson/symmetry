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

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class UrlEditor extends PropertyEditorSupport2
{
	// TODO: make package private?
	
	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		Object value = getValue();
		
		return (value != null) ? value.toString() : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		setValue(parse(text));
	}
	
	// private methods --------------------------------------------------------
	
	private static URL parse(String text)
	{
		if (text == null)
		{
			return null;
		}
		
		try
		{
			return new URL(text);
		}
		catch (MalformedURLException exception)
		{
			throw new IllegalArgumentException("Cannot parse URL: " + text, exception);
		}
	}
}
