/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/editor/LongEditorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean.editor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static uk.co.iizuka.common.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;

import java.beans.PropertyChangeListener;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code LongEditor}.
 * 
 * @author Mark Hobson
 * @version $Id: LongEditorTest.java 97413 2011-12-30 17:52:58Z mark@IIZUKA.CO.UK $
 * @see LongEditor
 */
@RunWith(JMock.class)
public class LongEditorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private LongEditor editor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		editor = new LongEditor();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setValueFiresEvent()
	{
		editor.addPropertyChangeListener(mockPropertyChangeListener(mockery, editor, null, null, 123L));
		
		editor.setValue(123L);
	}
	
	@Test
	public void setValueDoesNotFireEventWhenValueEquals()
	{
		editor.setValue(123L);
		editor.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		
		editor.setValue(123L);
	}
	
	@Test
	public void getAsText()
	{
		editor.setValue(123L);
		
		assertEquals("123", editor.getAsText());
	}
	
	@Test
	public void getAsTextWhenNull()
	{
		editor.setValue(null);
		
		assertNull(editor.getAsText());
	}
	
	@Test
	public void setAsText()
	{
		editor.setAsText("123");
		
		assertEquals(123L, editor.getValue());
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
