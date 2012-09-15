/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/editor/NumberEditorTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.editor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

/**
 * Tests {@code NumberEditor}.
 * 
 * @author Mark Hobson
 * @see NumberEditor
 */
public class NumberEditorTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void getAsText()
	{
		NumberEditor editor = createEditor("0.00");
		editor.setValue(1);
		
		assertEquals("1.00", editor.getAsText());
	}
	
	@Test
	public void getAsTextWhenNull()
	{
		NumberEditor editor = createEditor();
		
		assertNull(editor.getAsText());
	}
	
	@Test
	public void setAsText()
	{
		NumberEditor editor = createEditor("0.00");
		editor.setAsText("1.00");
		
		assertEquals(1L, editor.getValue());
	}
	
	@Test
	public void setAsTextWithNull()
	{
		NumberEditor editor = createEditor();
		editor.setAsText(null);
		
		assertNull(editor.getValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithEmptyString()
	{
		NumberEditor editor = createEditor();
		editor.setAsText("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setAsTextWithInvalidText()
	{
		NumberEditor editor = createEditor("0.00");
		editor.setAsText("x");
	}
	
	// private methods --------------------------------------------------------
	
	private static NumberEditor createEditor()
	{
		return new NumberEditor(NumberFormat.getInstance());
	}
	
	private static NumberEditor createEditor(String pattern)
	{
		return new NumberEditor(new DecimalFormat(pattern));
	}
}
