/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/editor/BooleanEditorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.editor;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.beans.PropertyChangeListener;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
