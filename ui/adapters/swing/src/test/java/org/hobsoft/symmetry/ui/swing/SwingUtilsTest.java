/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/SwingUtilsTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import javax.swing.SwingConstants;

import org.hobsoft.symmetry.ui.Orientation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void nonInstantiable()
	{
		Constructor<?>[] constructors = SwingUtils.class.getConstructors();
		
		for (int i = 0; i < constructors.length; i++)
		{
			assertTrue(Modifier.isPrivate(constructors[i].getModifiers()));
		}
	}
	
	@Test
	public void getOrientationHorizontal()
	{
		assertEquals(SwingConstants.HORIZONTAL, SwingUtils.getOrientation(Orientation.HORIZONTAL));
	}
	
	@Test
	public void getOrientationVertical()
	{
		assertEquals(SwingConstants.VERTICAL, SwingUtils.getOrientation(Orientation.VERTICAL));
	}
}
