/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/BeanUtilsTest.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.beans.BeanDescriptor;

import org.junit.Test;

import uk.co.iizuka.common.bean.model.Bean;
import uk.co.iizuka.common.bean.model.BoundBean;

/**
 * Tests {@code BeanUtils}.
 * 
 * @author Mark Hobson
 * @version $Id: BeanUtilsTest.java 87484 2011-05-01 15:32:12Z mark@IIZUKA.CO.UK $
 * @see BeanUtils
 */
public class BeanUtilsTest
{
	// equals(BeanDescriptor, BeanDescriptor) tests ---------------------------
	
	@Test
	public void equalsBeanDescriptorWhenEqual()
	{
		BeanDescriptor descriptor1 = new BeanDescriptor(Bean.class);
		BeanDescriptor descriptor2 = new BeanDescriptor(Bean.class);
		
		assertTrue(BeanUtils.equals(descriptor1, descriptor2));
	}
	
	@Test
	public void equalsBeanDescriptorWhenBeanClassDifferent()
	{
		BeanDescriptor descriptor1 = new BeanDescriptor(Bean.class);
		BeanDescriptor descriptor2 = new BeanDescriptor(BoundBean.class);
		
		assertFalse(BeanUtils.equals(descriptor1, descriptor2));
	}

	// TODO: test all other BeanDescriptor properties
	
	// toString(BeanDescriptor) tests -----------------------------------------
	
	@Test
	public void toStringBeanDescriptor()
	{
		BeanDescriptor descriptor = new BeanDescriptor(Bean.class);
		
		assertEquals("java.beans.BeanDescriptor[beanClass=uk.co.iizuka.common.bean.model.Bean,customizerClass=null]",
			BeanUtils.toString(descriptor));
	}
	
	@Test
	public void toStringBeanDescriptorWithNull()
	{
		assertEquals("null", BeanUtils.toString((BeanDescriptor) null));
	}
}
