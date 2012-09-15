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

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code TextArea}.
 * 
 * @author Mark Hobson
 * @see TextArea
 */
public class TextAreaTest extends AbstractComponentTest<TextArea>
{
	// fields -----------------------------------------------------------------
	
	private TextArea textArea;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		textArea = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newTextArea()
	{
		assertEquals("rows", 3, textArea.getRows());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setRowsNegative()
	{
		textArea.setRows(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setRowsZero()
	{
		textArea.setRows(0);
	}
	
	@Test
	public void setRowsPositive()
	{
		textArea.setRows(3);
		
		assertEquals("rows", 3, textArea.getRows());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TextArea createComponent()
	{
		return new TextArea();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<TextArea> getComponentType()
	{
		return Generic.get(TextArea.class);
	}
}
