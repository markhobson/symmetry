/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/JdkBeanIntrospectorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

/**
 * Tests {@code JdkBeanIntrospector} against the TCK.
 * 
 * @author Mark Hobson
 * @see JdkBeanIntrospector
 */
public class JdkBeanIntrospectorTest extends BeanIntrospectorTck
{
	// BeanIntrospectorTCK methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BeanIntrospector createBeanIntrospector()
	{
		return new JdkBeanIntrospector();
	}
}
