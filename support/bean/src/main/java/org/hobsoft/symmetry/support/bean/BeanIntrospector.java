/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/BeanIntrospector.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.BeanInfo;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: BeanIntrospector.java 86783 2011-04-11 18:18:45Z mark@IIZUKA.CO.UK $
 */
public interface BeanIntrospector
{
	boolean hasFeature(String name);
	
	Object getFeature(String name);
	
	void setFeature(String name, Object value);
	
	BeanInfo getBeanInfo(Class<?> beanClass);
}
