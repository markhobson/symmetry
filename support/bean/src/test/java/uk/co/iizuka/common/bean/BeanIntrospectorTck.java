/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/BeanIntrospectorTck.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.common.bean.model.FooListener;
import uk.co.iizuka.common.bean.model.TestBean;

/**
 * Provides a TCK to test {@code BeanIntrospector} implementations against.
 * 
 * @author Mark Hobson
 * @version $Id: BeanIntrospectorTck.java 86795 2011-04-11 21:03:44Z mark@IIZUKA.CO.UK $
 * @see BeanIntrospector
 */
public abstract class BeanIntrospectorTck
{
	// fields -----------------------------------------------------------------
	
	private BeanIntrospector introspector;

	// public methods ---------------------------------------------------------
	
	@Before
	public final void setUpTck()
	{
		introspector = createBeanIntrospector();
	}
	
	// tests ------------------------------------------------------------------

	@Test
	public void getBeanInfoBeanDescriptor()
	{
		BeanInfo info = introspector.getBeanInfo(TestBean.class);
		BeanDescriptor descriptor = info.getBeanDescriptor();
		
		assertNotNull(descriptor);
		assertEquals(TestBean.class, descriptor.getBeanClass());
	}
	
	@Test
	public void getBeanInfoPropertyDescriptors() throws IntrospectionException
	{
		BeanInfo info = introspector.getBeanInfo(TestBean.class);
		
		PropertyDescriptor[] actualDescriptors = info.getPropertyDescriptors();

		PropertyDescriptor[] expectedDescriptors = new PropertyDescriptor[] {
			new PropertyDescriptor("class", TestBean.class, "getClass", null),
			new PropertyDescriptor("foo", TestBean.class, "getFoo", "setFoo"),
			new PropertyDescriptor("fooBoolean", TestBean.class, "isFooBoolean", "setFooBoolean"),
			new PropertyDescriptor("fooFluent", TestBean.class, "getFooFluent", null),
			new PropertyDescriptor("fooListenerCount", TestBean.class, "getFooListenerCount", null),
			new PropertyDescriptor("fooListeners", TestBean.class, "getFooListeners", null),
			new PropertyDescriptor("fooReadOnly", TestBean.class, "getFooReadOnly", null),
			new PropertyDescriptor("fooWriteOnly", TestBean.class, null, "setFooWriteOnly"),
		};
		
		BeanAssert.assertPropertyDescriptors(expectedDescriptors, actualDescriptors);
	}
	
	@Test
	public void getBeanInfoEventSetDescriptors() throws IntrospectionException
	{
		BeanInfo info = introspector.getBeanInfo(TestBean.class);
		
		EventSetDescriptor[] actualDescriptors = info.getEventSetDescriptors();

		EventSetDescriptor[] expectedDescriptors = new EventSetDescriptor[] {
			new EventSetDescriptor(TestBean.class, "foo", FooListener.class, new String[] {"foo1", "foo2"},
				"addFooListener", "removeFooListener", "getFooListeners")
		};
		
		BeanAssert.assertEventSetDescriptors(expectedDescriptors, actualDescriptors);
	}
	
	@Test
	public void fluentMethodProperty() throws IntrospectionException
	{
		if (introspector.hasFeature(BeanIntrospectorFeatures.FLUENT_METHODS))
		{
			introspector.setFeature(BeanIntrospectorFeatures.FLUENT_METHODS, Boolean.TRUE);
			
			BeanInfo info = introspector.getBeanInfo(TestBean.class);
			
			PropertyDescriptor[] actualDescriptors = info.getPropertyDescriptors();
	
			PropertyDescriptor[] expectedDescriptors = new PropertyDescriptor[] {
				new PropertyDescriptor("class", TestBean.class, "getClass", null),
				new PropertyDescriptor("foo", TestBean.class, "getFoo", "setFoo"),
				new PropertyDescriptor("fooBoolean", TestBean.class, "isFooBoolean", "setFooBoolean"),
				new PropertyDescriptor("fooFluent", TestBean.class, "getFooFluent", "setFooFluent"),
				new PropertyDescriptor("fooFluentWriteOnly", TestBean.class, null, "setFooFluentWriteOnly"),
				new PropertyDescriptor("fooListenerCount", TestBean.class, "getFooListenerCount", null),
				new PropertyDescriptor("fooListeners", TestBean.class, "getFooListeners", null),
				new PropertyDescriptor("fooReadOnly", TestBean.class, "getFooReadOnly", null),
				new PropertyDescriptor("fooWriteOnly", TestBean.class, null, "setFooWriteOnly"),
			};
			
			BeanAssert.assertPropertyDescriptors(expectedDescriptors, actualDescriptors);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract BeanIntrospector createBeanIntrospector();
}
