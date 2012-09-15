/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/test/java/uk/co/iizuka/kozo/state/PropertyStateTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.Properties;
import org.junit.Test;

/**
 * Tests {@code PropertyState}.
 * 
 * @author Mark Hobson
 * @see PropertyState
 */
public class PropertyStateTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void newPropertyState()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getDescriptor();
		Object value = createValue();
		
		PropertyState property = new PropertyState(bean, descriptor, value);
		
		assertPropertyState(bean, descriptor, value, property);
	}
	
	@Test
	public void newPropertyStateWithNullValue()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getDescriptor();
		
		PropertyState property = new PropertyState(bean, descriptor, null);
		
		assertPropertyState(bean, descriptor, null, property);
	}
	
	@Test
	public void newPropertyStateWithPrimitiveValue()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getPrimitiveDescriptor();
		int value = 1;
		
		PropertyState property = new PropertyState(bean, descriptor, value);
		
		assertPropertyState(bean, descriptor, value, property);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newPropertyStateWithNullValueWhenPrimitive()
	{
		new PropertyState(createBean(), getPrimitiveDescriptor(), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void newPropertyStateWithNullBean()
	{
		new PropertyState(null, getDescriptor(), createValue());
	}
	
	@Test(expected = NullPointerException.class)
	public void newPropertyStateWithNullDescriptor()
	{
		new PropertyState(createBean(), null, createValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newPropertyStateWithInvalidValue()
	{
		new PropertyState(createBean(), getDescriptor(), new Object());
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getDescriptor();
		Object value = createValue();
		
		PropertyState property1 = new PropertyState(bean, descriptor, value);
		PropertyState property2 = new PropertyState(bean, descriptor, value);
		
		assertEquals(property1.hashCode(), property2.hashCode());
	}
	
	@Test
	public void hashCodeWhenEqualWithArrayValue()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getArrayDescriptor();
		
		PropertyState property1 = new PropertyState(bean, descriptor, createArrayValue());
		PropertyState property2 = new PropertyState(bean, descriptor, createArrayValue());
		
		assertEquals(property1.hashCode(), property2.hashCode());
	}
	
	@Test
	public void hashCodeWhenEqualWithNullValue()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getDescriptor();
		
		PropertyState property1 = new PropertyState(bean, descriptor, null);
		PropertyState property2 = new PropertyState(bean, descriptor, null);
		
		assertEquals(property1.hashCode(), property2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getDescriptor();
		Object value = createValue();
		
		PropertyState property1 = new PropertyState(bean, descriptor, value);
		PropertyState property2 = new PropertyState(bean, descriptor, value);
		
		assertTrue(property1.equals(property2));
	}
	
	@Test
	public void equalsWhenEqualWithArrayValue()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getArrayDescriptor();
		
		PropertyState property1 = new PropertyState(bean, descriptor, createArrayValue());
		PropertyState property2 = new PropertyState(bean, descriptor, createArrayValue());
		
		assertTrue(property1.equals(property2));
	}
	
	@Test
	public void equalsWhenEqualWithNullValue()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getDescriptor();
		
		PropertyState property1 = new PropertyState(bean, descriptor, null);
		PropertyState property2 = new PropertyState(bean, descriptor, null);
		
		assertTrue(property1.equals(property2));
	}
	
	@Test
	public void equalsWhenBeanNotEqual()
	{
		PropertyDescriptor descriptor = getDescriptor();
		Object value = createValue();
		
		PropertyState property1 = new PropertyState(createBean(), descriptor, value);
		PropertyState property2 = new PropertyState(createBean(), descriptor, value);
		
		assertFalse(property1.equals(property2));
	}
	
	@Test
	public void equalsWhenDescriptorNotEqual()
	{
		DummyBean bean = createBean();
		Object value = createValue();
		
		PropertyState property1 = new PropertyState(bean, getDescriptor("foo"), value);
		PropertyState property2 = new PropertyState(bean, getDescriptor("bar"), value);
		
		assertFalse(property1.equals(property2));
	}
	
	@Test
	public void equalsWhenValueNotEqual()
	{
		DummyBean bean = createBean();
		PropertyDescriptor descriptor = getDescriptor();
		
		PropertyState property1 = new PropertyState(bean, descriptor, "x");
		PropertyState property2 = new PropertyState(bean, descriptor, "y");
		
		assertFalse(property1.equals(property2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		PropertyState property = createPropertyState();
		
		assertFalse(property.equals(new Object()));
	}
	
	@Test
	public void equalsWithNull()
	{
		PropertyState property = createPropertyState();
		
		// workaround Checkstyle bug 2809655
		// CHECKSTYLE:OFF
		assertFalse(property.equals(null));
		// CHECKSTYLE:ON
	}
	
	@Test
	public void toStringTest()
	{
		DummyBean bean = createBean();
		PropertyState property = new PropertyState(bean, getDescriptor("foo"), "x");
		
		assertEquals(bean + ".foo=x", property.toString());
	}
	
	// private methods --------------------------------------------------------
	
	private static PropertyState createPropertyState()
	{
		return new PropertyState(createBean(), getDescriptor(), createValue());
	}
	
	private static DummyBean createBean()
	{
		return new DummyBean();
	}
	
	private static PropertyDescriptor getDescriptor()
	{
		return getDescriptor("foo");
	}
	
	private static PropertyDescriptor getPrimitiveDescriptor()
	{
		return getDescriptor("baz");
	}
	
	private static PropertyDescriptor getArrayDescriptor()
	{
		return getDescriptor("foos");
	}
	
	private static PropertyDescriptor getDescriptor(String propertyName)
	{
		return Properties.getDescriptor(DummyBean.class, propertyName);
	}
	
	private static String createValue()
	{
		return "x";
	}
	
	private static String[] createArrayValue()
	{
		return new String[] {"x"};
	}
	
	private static void assertPropertyState(Object expectedBean, PropertyDescriptor expectedDescriptor,
		Object expectedValue, PropertyState actual)
	{
		assertEquals("Bean", expectedBean, actual.getBean());
		assertEquals("Property descriptor", expectedDescriptor, actual.getDescriptor());
		assertEquals("Property value", expectedValue, actual.getValue());
	}
}
