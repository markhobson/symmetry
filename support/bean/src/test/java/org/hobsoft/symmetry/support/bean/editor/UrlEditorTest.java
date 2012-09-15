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
import java.net.MalformedURLException;
import java.net.URL;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code UrlEditor}.
 * 
 * @author Mark Hobson
 * @see UrlEditor
 */
@RunWith(JMock.class)
public class UrlEditorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private UrlEditor editor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		editor = new UrlEditor();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setValueFiresEvent() throws MalformedURLException
	{
		editor.addPropertyChangeListener(mockPropertyChangeListener(mockery, editor, null, null,
			new URL("http://localhost/")));
		
		editor.setValue(new URL("http://localhost/"));
	}
	
	@Test
	public void setValueDoesNotFireEventWhenValueEquals() throws MalformedURLException
	{
		editor.setValue(new URL("http://localhost/"));
		editor.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		
		editor.setValue(new URL("http://localhost/"));
	}
	
	@Test
	public void getAsText() throws MalformedURLException
	{
		editor.setValue(new URL("http://localhost/"));
		
		assertEquals("http://localhost/", editor.getAsText());
	}
	
	@Test
	public void getAsTextWhenNull()
	{
		editor.setValue(null);
		
		assertNull(editor.getAsText());
	}
	
	@Test
	public void setAsText() throws MalformedURLException
	{
		editor.setAsText("http://localhost/");
		
		assertEquals(new URL("http://localhost/"), editor.getValue());
	}
	
	@Test
	public void setAsTextWithNull()
	{
		editor.setAsText(null);
		
		assertNull(editor.getValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithEmptyString()
	{
		editor.setAsText("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithInvalidText()
	{
		editor.setAsText("x");
	}
}
