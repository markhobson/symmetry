/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/RadioTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;

/**
 * Tests {@code Radio}.
 * 
 * @author Mark Hobson
 * @version $Id: RadioTest.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @see Radio
 */
public class RadioTest extends AbstractComponentTest<Radio>
{
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Radio createComponent()
	{
		return new Radio();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Radio> getComponentType()
	{
		return Generic.get(Radio.class);
	}
}
