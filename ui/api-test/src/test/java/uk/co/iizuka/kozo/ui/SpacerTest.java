/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/SpacerTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;

/**
 * Tests {@code Spacer}.
 * 
 * @author Mark Hobson
 * @version $Id: SpacerTest.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @see Spacer
 */
public class SpacerTest extends AbstractComponentTest<Spacer>
{
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Spacer createComponent()
	{
		return new Spacer();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Spacer> getComponentType()
	{
		return Generic.get(Spacer.class);
	}
}
