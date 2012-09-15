/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/TextAreaTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.junit.Assert.assertEquals;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code TextArea}.
 * 
 * @author Mark Hobson
 * @version $Id: TextAreaTest.java 96255 2011-12-09 17:37:52Z mark@IIZUKA.CO.UK $
 * @see TextArea
 */
public class TextAreaTest extends AbstractComponentTest<TextArea>
{
	// fields -----------------------------------------------------------------
	
	private TextArea textArea;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		textArea = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newTextArea()
	{
		assertEquals("rows", 3, textArea.getRows());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setRowsNegative()
	{
		textArea.setRows(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setRowsZero()
	{
		textArea.setRows(0);
	}
	
	@Test
	public void setRowsPositive()
	{
		textArea.setRows(3);
		
		assertEquals("rows", 3, textArea.getRows());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TextArea createComponent()
	{
		return new TextArea();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<TextArea> getComponentType()
	{
		return Generic.get(TextArea.class);
	}
}
