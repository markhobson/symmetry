/*
 * $HeadURL: https://svn.iizuka.co.uk/common/test/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/test/PrivateAccessorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.test;

import junit.framework.TestCase;

/**
 * Tests <code>PrivateAccessor</code>.
 *
 * @author	Mark Hobson
 * @version	$Id: PrivateAccessorTest.java 69784 2010-01-21 11:40:46Z mark@IIZUKA.CO.UK $
 * @see		PrivateAccessor
 */
public class PrivateAccessorTest extends TestCase
{
	// fields -----------------------------------------------------------------
	
	private DummyBean bean;

	// TestCase methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception
	{
		bean = new DummyBean();
	}

	// tests ------------------------------------------------------------------
	
	public void testGetPrivateField()
	{
		bean.setName("myname");
		
		Object actualName = PrivateAccessor.getPrivateField(bean, DummyBean.NAME);
		
		assertEquals("myname", actualName);
	}
}
