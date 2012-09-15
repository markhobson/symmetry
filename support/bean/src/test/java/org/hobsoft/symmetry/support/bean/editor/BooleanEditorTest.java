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

import java.beans.PropertyChangeListener;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code BooleanEditor}.
 * 
 * @author Mark Hobson
 * @see BooleanEditor
 */
@RunWith(JMock.class)
public class BooleanEditorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private BooleanEditor editor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		editor = new BooleanEditor();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setValueFiresEvent()
	{
		editor.addPropertyChangeListener(mockPropertyChangeListener(mockery, editor, null, null, Boolean.TRUE));
		
		editor.setValue(Boolean.TRUE);
	}
	
	@Test
	public void setValueDoesNotFireEventWhenValueEquals()
	{
		editor.setValue(Boolean.TRUE);
		editor.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		
		editor.setValue(Boolean.TRUE);
	}
	
	@Test
	public void getAsTextWhenTrue()
	{
		editor.setValue(Boolean.TRUE);
		
		assertEquals("true", editor.getAsText());
	}
	
	@Test
	public void getAsTextWhenFalse()
	{
		editor.setValue(Boolean.FALSE);
		
		assertEquals("false", editor.getAsText());
	}

	@Test
	public void getAsTextWhenNull()
	{
		editor.setValue(null);
		
		assertNull(editor.getAsText());
	}
	
	@Test
	public void setAsTextWithTrue()
	{
		editor.setAsText("true");
		
		assertEquals(Boolean.TRUE, editor.getValue());
	}
	
	@Test
	public void setAsTextWithFalse()
	{
		editor.setAsText("false");
		
		assertEquals(Boolean.FALSE, editor.getValue());
	}
	
	@Test
	public void setAsTextWithNull()
	{
		editor.setAsText(null);
		
		assertNull(editor.getValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithInvalidText()
	{
		editor.setAsText("x");
	}
	
	@Test
	public void getTags()
	{
		assertArrayEquals(new String[] {"true", "false"}, editor.getTags());
	}
}
