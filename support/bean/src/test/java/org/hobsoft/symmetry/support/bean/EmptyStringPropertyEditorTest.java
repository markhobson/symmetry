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
package org.hobsoft.symmetry.support.bean;

import java.beans.PropertyEditor;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code EmptyStringPropertyEditor}.
 * 
 * @author Mark Hobson
 * @see EmptyStringPropertyEditor
 */
@RunWith(JMock.class)
public class EmptyStringPropertyEditorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private EmptyStringPropertyEditor editor;
	
	private PropertyEditor delegate;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		delegate = mockery.mock(PropertyEditor.class);
		editor = new EmptyStringPropertyEditor(delegate);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setAsText()
	{
		mockery.checking(new Expectations() { {
			oneOf(delegate).setAsText("a");
		} });
		
		editor.setAsText("a");
	}
	
	@Test
	public void setAsTextWithEmptyString()
	{
		mockery.checking(new Expectations() { {
			oneOf(delegate).setAsText(null);
		} });
		
		editor.setAsText("");
	}
	
	@Test
	public void setAsTextWithNull()
	{
		mockery.checking(new Expectations() { {
			oneOf(delegate).setAsText(null);
		} });
		
		editor.setAsText(null);
	}
}
