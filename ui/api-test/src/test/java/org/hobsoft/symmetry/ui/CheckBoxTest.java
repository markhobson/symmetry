/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/CheckBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;

import com.googlecode.jtype.Generic;

/**
 * Tests {@code CheckBox}.
 * 
 * @author Mark Hobson
 * @see CheckBox
 */
public class CheckBoxTest extends AbstractComponentTest<CheckBox>
{
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CheckBox createComponent()
	{
		return new CheckBox();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<CheckBox> getComponentType()
	{
		return Generic.get(CheckBox.class);
	}
}
