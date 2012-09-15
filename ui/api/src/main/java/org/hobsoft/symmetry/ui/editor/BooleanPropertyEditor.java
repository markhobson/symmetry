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
package org.hobsoft.symmetry.ui.editor;

import org.hobsoft.symmetry.ui.CheckBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class BooleanPropertyEditor extends AbstractPropertyEditor
{
	// constructors -----------------------------------------------------------
	
	public BooleanPropertyEditor()
	{
		super(new CheckBox());
	}
	
	// AbstractPropertyEditor methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		CheckBox checkBox = (CheckBox) getComponent();
		return Boolean.valueOf(checkBox.isSelected());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		if (value != null && !(value instanceof Boolean))
		{
			throw new IllegalArgumentException("Boolean expected: " + value);
		}
		
		CheckBox checkBox = (CheckBox) getComponent();
		checkBox.setSelected(value == Boolean.TRUE);
	}
}
