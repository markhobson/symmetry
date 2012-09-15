/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/MultiLineLabelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;

import com.googlecode.jtype.Generic;

/**
 * Tests {@code MultiLineLabel}.
 * 
 * @author Mark Hobson
 * @version $Id: MultiLineLabelTest.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @see MultiLineLabel
 */
public class MultiLineLabelTest extends AbstractComponentTest<MultiLineLabel>
{
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MultiLineLabel createComponent()
	{
		return new MultiLineLabel();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<MultiLineLabel> getComponentType()
	{
		return Generic.get(MultiLineLabel.class);
	}
}
