/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/LabelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Label}.
 * 
 * @author Mark Hobson
 * @see Label
 */
public class LabelTest extends AbstractComponentTest<Label>
{
	// fields -----------------------------------------------------------------
	
	private Label label;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		label = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setText()
	{
		label.setText("a");
		
		assertEquals("a", label.getText());
	}
	
	@Test
	public void setTextFiresEvent()
	{
		PropertyChangeListener listener = mockPropertyChangeListener(getMockery(), label, "text", "", "a");
		label.addPropertyChangeListener("text", listener);
		label.setText("a");
	}
	
	@Test
	public void setTextWithNull()
	{
		label.setText(null);
		
		assertEquals("", label.getText());
	}
	
	@Test
	public void setTextWithNullTranslatesEvent()
	{
		label.setText("a");
		
		PropertyChangeListener listener = mockPropertyChangeListener(getMockery(), label, "text", "a", "");
		label.addPropertyChangeListener("text", listener);
		label.setText(null);
	}
	
	@Test
	public void setToolTip()
	{
		label.setToolTip("a");
		
		assertEquals("a", label.getToolTip());
	}
	
	@Test
	public void setToolTipWithNull()
	{
		label.setToolTip(null);
		
		assertEquals("", label.getToolTip());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Label createComponent()
	{
		return new Label();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Label> getComponentType()
	{
		return Generic.get(Label.class);
	}
}
