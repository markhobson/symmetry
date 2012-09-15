/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/PasswordBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;

import com.googlecode.jtype.Generic;

/**
 * Tests {@code PasswordBox}.
 * 
 * @author Mark Hobson
 * @see PasswordBox
 */
public class PasswordBoxTest extends AbstractComponentTest<PasswordBox>
{
	// AbstractComponentTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<PasswordBox> getComponentType()
	{
		return Generic.get(PasswordBox.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PasswordBox createComponent()
	{
		return new PasswordBox();
	}
}
