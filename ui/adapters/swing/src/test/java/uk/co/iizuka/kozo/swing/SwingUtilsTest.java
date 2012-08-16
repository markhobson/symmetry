/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/test/java/uk/co/iizuka/kozo/swing/SwingUtilsTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.swing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import javax.swing.SwingConstants;

import org.junit.Test;

import uk.co.iizuka.kozo.ui.Orientation;
import uk.co.iizuka.kozo.ui.swing.SwingUtils;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingUtilsTest.java 97405 2011-12-30 13:36:34Z mark@IIZUKA.CO.UK $
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
