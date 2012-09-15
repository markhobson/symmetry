/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/SpacerTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;

import com.googlecode.jtype.Generic;

/**
 * Tests {@code Spacer}.
 * 
 * @author Mark Hobson
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
