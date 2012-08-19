/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/TraversalUtilsTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests <code>TraversalUtils</code>.
 * 
 * @author Mark Hobson
 * @version $Id: TraversalUtilsTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see TraversalUtils
 */
public class TraversalUtilsTest
{
	// getTreeWalker tests ----------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void getTreeWalkerWithNullRoot()
	{
		try
		{
			TraversalUtils.getTreeWalker(null);
		}
		catch (IllegalArgumentException exception)
		{
			assertEquals("root cannot be null", exception.getMessage());
			
			throw exception;
		}
	}
}
