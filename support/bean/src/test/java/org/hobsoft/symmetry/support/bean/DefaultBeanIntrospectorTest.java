/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/DefaultBeanIntrospectorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

/**
 * Tests {@code DefaultBeanIntrospector} against the TCK.
 * 
 * @author Mark Hobson
 * @see DefaultBeanIntrospector
 */
public class DefaultBeanIntrospectorTest extends BeanIntrospectorTck
{
	// BeanIntrospectorTCK methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BeanIntrospector createBeanIntrospector()
	{
		return new DefaultBeanIntrospector();
	}
}
