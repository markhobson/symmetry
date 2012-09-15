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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code DateEditor}.
 * 
 * @author Mark Hobson
 * @see DateEditor
 */
@RunWith(JMock.class)
public class DateEditorTest
{
	// fields -----------------------------------------------------------------

	private final Mockery mockery = new JUnit4Mockery();
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void setValueFiresEvent()
	{
		DateEditor editor = createEditor("dd/MM/yyyy");
		editor.addPropertyChangeListener(mockPropertyChangeListener(mockery, editor, null, null,
			createDate(1, 2, 2000)));
		
		editor.setValue(createDate(1, 2, 2000));
	}
	
	@Test
	public void setValueDoesNotFireEventWhenValueEquals()
	{
		DateEditor editor = createEditor("dd/MM/yyyy");
		editor.setValue(createDate(1, 2, 2000));
		editor.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		
		editor.setValue(createDate(1, 2, 2000));
	}
	
	@Test
	public void getAsText()
	{
		DateEditor editor = createEditor("dd/MM/yyyy");
		editor.setValue(createDate(1, 2, 2000));
		
		assertEquals("01/02/2000", editor.getAsText());
	}
	
	@Test
	public void getAsTextWhenNull()
	{
		DateEditor editor = createEditor();
		
		assertNull(editor.getAsText());
	}
	
	@Test
	public void setAsText()
	{
		DateEditor editor = createEditor("dd/MM/yyyy");
		editor.setAsText("01/02/2000");
		
		assertEquals(createDate(1, 2, 2000), editor.getValue());
	}
	
	@Test
	public void setAsTextWithNull()
	{
		DateEditor editor = createEditor();
		editor.setAsText(null);
		
		assertNull(editor.getValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithEmptyString()
	{
		DateEditor editor = createEditor();
		editor.setAsText("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithInvalidText()
	{
		DateEditor editor = createEditor("dd/MM/yyyy");
		editor.setAsText("x");
	}
	
	// private methods --------------------------------------------------------
	
	private static DateEditor createEditor()
	{
		return new DateEditor(DateFormat.getInstance());
	}
	
	private static DateEditor createEditor(String pattern)
	{
		return new DateEditor(new SimpleDateFormat(pattern));
	}
	
	private static Date createDate(int day, int month, int year)
	{
		return createDate(day, month, year, 0, 0);
	}
	
	private static Date createDate(int day, int month, int year, int hour, int minute)
	{
		return createDate(day, month, year, hour, minute, 0);
	}
	
	private static Date createDate(int day, int month, int year, int hour, int minute, int second)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(0);
		
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		
		return calendar.getTime();
	}
}
