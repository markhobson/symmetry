/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ToggleButtonTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;

import com.googlecode.jtype.Generic;

/**
 * Tests {@code ToggleButton}.
 * 
 * @author Mark Hobson
 * @see ToggleButton
 */
public class ToggleButtonTest extends AbstractComponentTest<ToggleButton>
{
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ToggleButton createComponent()
	{
		return new ToggleButton();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<ToggleButton> getComponentType()
	{
		return Generic.get(ToggleButton.class);
	}
}
