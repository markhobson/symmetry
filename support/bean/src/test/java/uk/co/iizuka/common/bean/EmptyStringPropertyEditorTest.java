/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/EmptyStringPropertyEditorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

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
 * @version $Id: EmptyStringPropertyEditorTest.java 93028 2011-09-29 14:53:01Z mark@IIZUKA.CO.UK $
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
