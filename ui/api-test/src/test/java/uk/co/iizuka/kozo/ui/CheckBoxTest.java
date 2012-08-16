/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/CheckBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;

/**
 * Tests {@code CheckBox}.
 * 
 * @author Mark Hobson
 * @version $Id: CheckBoxTest.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
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
