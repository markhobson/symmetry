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

import org.hobsoft.symmetry.ui.TextBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StringPropertyEditor extends AbstractPropertyEditor
{
	// constructors -----------------------------------------------------------
	
	public StringPropertyEditor()
	{
		super(new TextBox(40));
	}
	
	// AbstractPropertyEditor methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		return getTextBox().getText();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		getTextBox().setText((String) value);
	}
	
	// protected methods ------------------------------------------------------
	
	protected TextBox getTextBox()
	{
		return (TextBox) getComponent();
	}
}
