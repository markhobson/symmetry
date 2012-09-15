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
 * @param <E>
 *            the enum type
 */
public class EnumEditor<E extends Enum<E>> extends PropertyEditorSupport2
{
	// fields -----------------------------------------------------------------
	
	private final Class<E> enumType;

	// constructors -----------------------------------------------------------
	
	public EnumEditor(Class<E> enumType)
	{
		this.enumType = enumType;
	}

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
		E value = (text != null) ? Enum.valueOf(enumType, text) : null;
		
		setValue(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTags()
	{
		return toStrings(enumType.getEnumConstants());
	}
	
	// private methods --------------------------------------------------------
	
	private static String[] toStrings(Object[] array)
	{
		String[] strings = new String[array.length];
		
		for (int i = 0; i < array.length; i++)
		{
			strings[i] = array[i].toString();
		}
		
		return strings;
	}
}
