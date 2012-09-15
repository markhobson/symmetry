/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/editor/UrlEditorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
