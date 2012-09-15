/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/TextBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyChangeListener;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code TextBox}.
 * 
 * @author Mark Hobson
 * @version $Id: TextBoxTest.java 96255 2011-12-09 17:37:52Z mark@IIZUKA.CO.UK $
 * @see TextBox
 */
public class TextBoxTest extends AbstractComponentTest<TextBox>
{
	// fields -----------------------------------------------------------------
	
	private TextBox textBox;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		textBox = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setText()
	{
		textBox.setText("a");
		
		assertEquals("a", textBox.getText());
	}
	
	@Test
	public void setTextFiresEvent()
	{
		PropertyChangeListener listener = mockPropertyChangeListener(getMockery(), textBox, "text", "", "a");
		textBox.addPropertyChangeListener("text", listener);
		textBox.setText("a");
	}
	
	@Test
	public void setTextWithNull()
	{
		textBox.setText(null);
		
		assertEquals("", textBox.getText());
	}
	
	@Test
	public void setTextWithNullTranslatesEvent()
	{
		textBox.setText("a");
		
		PropertyChangeListener listener = mockPropertyChangeListener(getMockery(), textBox, "text", "a", "");
		textBox.addPropertyChangeListener("text", listener);
		textBox.setText(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnsNegative()
	{
		textBox.setColumns(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnsZero()
	{
		textBox.setColumns(0);
	}
	
	@Test
	public void setColumnsPositive()
	{
		textBox.setColumns(1);
		
		assertEquals("columns", 1, textBox.getColumns());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxLengthNegative()
	{
		textBox.setMaxLength(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setMaxLengthZero()
	{
		textBox.setMaxLength(0);
	}
	
	@Test
	public void setMaxLengthPositive()
	{
		textBox.setMaxLength(1);
		
		assertEquals("maxLength", 1, textBox.getMaxLength());
	}
	
	@Test
	public void setReadOnly()
	{
		textBox.setReadOnly(true);
		
		assertTrue("readOnly", textBox.isReadOnly());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TextBox createComponent()
	{
		return new TextBox();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<TextBox> getComponentType()
	{
		return Generic.get(TextBox.class);
	}
}
