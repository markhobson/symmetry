/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/editor/EnumEditorTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.editor;

import java.beans.PropertyChangeListener;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code EnumEditor}.
 * 
 * @author Mark Hobson
 * @see EnumEditor
 */
public class EnumEditorTest
{
	// types ------------------------------------------------------------------
	
	private enum FakeEnum
	{
		A,
		B;
	}

	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	private EnumEditor<FakeEnum> editor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		editor = new EnumEditor<FakeEnum>(FakeEnum.class);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setValueFiresEvent()
	{
		editor.addPropertyChangeListener(mockPropertyChangeListener(mockery, editor, null, null, FakeEnum.A));
		
		editor.setValue(FakeEnum.A);
	}
	
	@Test
	public void setValueDoesNotFireEventWhenValueEquals()
	{
		editor.setValue(FakeEnum.A);
		editor.addPropertyChangeListener(mockery.mock(PropertyChangeListener.class));
		
		editor.setValue(FakeEnum.A);
	}
	
	@Test
	public void getAsText()
	{
		editor.setValue(FakeEnum.A);
		
		assertEquals("A", editor.getAsText());
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
		editor.setAsText("A");
		
		assertEquals(FakeEnum.A, editor.getValue());
	}
	
	@Test
	public void setAsTextWithNull()
	{
		editor.setAsText(null);
		
		assertNull(editor.getValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithInvalidValue()
	{
		editor.setAsText("x");
	}
	
	@Test
	public void getTags()
	{
		assertArrayEquals(new String[] {"A", "B"}, editor.getTags());
	}
}
