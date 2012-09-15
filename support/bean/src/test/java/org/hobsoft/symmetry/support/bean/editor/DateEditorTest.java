/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/editor/DateEditorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.editor;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

/**
 * Tests {@code DateEditor}.
 * 
 * @author Mark Hobson
 * @version $Id: DateEditorTest.java 97413 2011-12-30 17:52:58Z mark@IIZUKA.CO.UK $
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
