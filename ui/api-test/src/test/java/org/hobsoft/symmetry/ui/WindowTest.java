/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/WindowTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.junit.Assert.assertEquals;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code Window}.
 * 
 * @author Mark Hobson
 * @version $Id: WindowTest.java 95554 2011-11-25 23:15:51Z mark@IIZUKA.CO.UK $
 * @see Window
 */
public class WindowTest extends AbstractComponentTest<Window>
{
	// fields -----------------------------------------------------------------
	
	private Window window;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		window = getComponent();
	}

	@Test
	public void setTitle()
	{
		window.setTitle("a");
		
		assertEquals("a", window.getTitle());
	}
	
	@Test
	public void setTitleWithNull()
	{
		window.setTitle(null);
		
		assertEquals("", window.getTitle());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Window createComponent()
	{
		return new Window();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Window> getComponentType()
	{
		return Generic.get(Window.class);
	}
}
