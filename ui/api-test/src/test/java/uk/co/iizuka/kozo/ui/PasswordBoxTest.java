/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/PasswordBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;

/**
 * Tests {@code PasswordBox}.
 * 
 * @author Mark Hobson
 * @version $Id: PasswordBoxTest.java 96224 2011-12-09 16:24:18Z mark@IIZUKA.CO.UK $
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
