/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ToggleButtonGroupTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code ToggleButtonGroup}.
 * 
 * @author Mark Hobson
 * @see ToggleButtonGroup
 */
public class ToggleButtonGroupTest
{
	// fields -----------------------------------------------------------------

	private ToggleButtonGroup group;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		group = new ToggleButtonGroup();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setSelectedButtonWithChild()
	{
		ToggleButton button1 = new ToggleButton();
		ToggleButton button2 = new ToggleButton();
		group.add(button1, button2);
		
		group.setSelectedButton(button2);
		
		assertTrue(button2.isSelected());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setSelectedButtonWithNonChild()
	{
		group.setSelectedButton(new ToggleButton());
	}

	@Test
	public void setSelectedButtonWithNull()
	{
		ToggleButton button = new ToggleButton();
		button.setSelected(true);
		group.add(button);
		
		group.setSelectedButton(null);
		
		assertFalse(button.isSelected());
	}
}
