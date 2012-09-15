/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/BeanIntrospectorFeatures.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class BeanIntrospectorFeatures
{
	// constants --------------------------------------------------------------
	
	public static final String FLUENT_METHODS = BeanIntrospector.class.getName() + ".fluent";
	
	// TODO: add flags from java.beans.Introspector as features
	
	// constructors -----------------------------------------------------------
	
	private BeanIntrospectorFeatures()
	{
		throw new AssertionError();
	}
}
