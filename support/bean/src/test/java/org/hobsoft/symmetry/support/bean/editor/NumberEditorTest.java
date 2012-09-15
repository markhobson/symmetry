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

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code NumberEditor}.
 * 
 * @author Mark Hobson
 * @see NumberEditor
 */
public class NumberEditorTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void getAsText()
	{
		NumberEditor editor = createEditor("0.00");
		editor.setValue(1);
		
		assertEquals("1.00", editor.getAsText());
	}
	
	@Test
	public void getAsTextWhenNull()
	{
		NumberEditor editor = createEditor();
		
		assertNull(editor.getAsText());
	}
	
	@Test
	public void setAsText()
	{
		NumberEditor editor = createEditor("0.00");
		editor.setAsText("1.00");
		
		assertEquals(1L, editor.getValue());
	}
	
	@Test
	public void setAsTextWithNull()
	{
		NumberEditor editor = createEditor();
		editor.setAsText(null);
		
		assertNull(editor.getValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithEmptyString()
	{
		NumberEditor editor = createEditor();
		editor.setAsText("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithInvalidText()
	{
		NumberEditor editor = createEditor("0.00");
		editor.setAsText("x");
	}
	
	// private methods --------------------------------------------------------
	
	private static NumberEditor createEditor()
	{
		return new NumberEditor(NumberFormat.getInstance());
	}
	
	private static NumberEditor createEditor(String pattern)
	{
		return new NumberEditor(new DecimalFormat(pattern));
	}
}
