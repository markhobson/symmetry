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
package org.hobsoft.symmetry.ui;

import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code TextBox}.
 * 
 * @author Mark Hobson
 * @see TextBox
 */
public class TextBoxTest extends AbstractComponentTest<TextBox>
{
	// fields -----------------------------------------------------------------
	
	private TextBox textBox;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		textBox = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setText()
	{
		textBox.setText("a");
		
		assertEquals("a", textBox.getText());
	}
	
	@Test
	public void setTextFiresEvent()
	{
		PropertyChangeListener listener = mockPropertyChangeListener(getMockery(), textBox, "text", "", "a");
		textBox.addPropertyChangeListener("text", listener);
		textBox.setText("a");
	}
	
	@Test
	public void setTextWithNull()
	{
		textBox.setText(null);
		
		assertEquals("", textBox.getText());
	}
	
	@Test
	public void setTextWithNullTranslatesEvent()
	{
		textBox.setText("a");
		
		PropertyChangeListener listener = mockPropertyChangeListener(getMockery(), textBox, "text", "a", "");
		textBox.addPropertyChangeListener("text", listener);
		textBox.setText(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnsNegative()
	{
		textBox.setColumns(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnsZero()
	{
		textBox.setColumns(0);
	}
	
	@Test
	public void setColumnsPositive()
	{
		textBox.setColumns(1);
		
		assertEquals("columns", 1, textBox.getColumns());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxLengthNegative()
	{
		textBox.setMaxLength(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setMaxLengthZero()
	{
		textBox.setMaxLength(0);
	}
	
	@Test
	public void setMaxLengthPositive()
	{
		textBox.setMaxLength(1);
		
		assertEquals("maxLength", 1, textBox.getMaxLength());
	}
	
	@Test
	public void setReadOnly()
	{
		textBox.setReadOnly(true);
		
		assertTrue("readOnly", textBox.isReadOnly());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TextBox createComponent()
	{
		return new TextBox();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<TextBox> getComponentType()
	{
		return Generic.get(TextBox.class);
	}
}
