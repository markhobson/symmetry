/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/HtmlLabelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;

import com.googlecode.jtype.Generic;

/**
 * Tests {@code HtmlLabel}.
 * 
 * @author Mark Hobson
 * @see HtmlLabel
 */
public class HtmlLabelTest extends AbstractComponentTest<HtmlLabel>
{
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HtmlLabel createComponent()
	{
		return new HtmlLabel();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<HtmlLabel> getComponentType()
	{
		return Generic.get(HtmlLabel.class);
	}
}
