/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

import static org.junit.Assert.fail;

/**
 * Provides methods to assert the equality of JavaBean objects.
 * 
 * @author Mark Hobson
 */
public final class BeanAssert
{
	// constructors -----------------------------------------------------------
	
	private BeanAssert()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void assertBeanDescriptor(BeanDescriptor expected, BeanDescriptor actual)
	{
		assertBeanDescriptor(null, expected, actual);
	}
	
	public static void assertBeanDescriptor(String message, BeanDescriptor expected, BeanDescriptor actual)
	{
		if (!BeanUtils.equals(expected, actual))
		{
			failNotEquals(message, BeanUtils.toString(expected), BeanUtils.toString(actual));
		}
	}
	
	public static void assertPropertyDescriptor(PropertyDescriptor expected, PropertyDescriptor actual)
	{
		assertPropertyDescriptor(null, expected, actual);
	}
	
	public static void assertPropertyDescriptor(String message, PropertyDescriptor expected, PropertyDescriptor actual)
	{
		// TODO: implement PropertyDescriptors.equals since PropertyDescriptor.equals does not consider
		// FeatureDescriptor fields
		if (!expected.equals(actual))
		{
			failNotEquals(message, Properties.toString(expected), Properties.toString(actual));
		}
	}
	
	public static void assertPropertyDescriptors(PropertyDescriptor[] expected, PropertyDescriptor[] actual)
	{
		assertPropertyDescriptors(null, expected, actual);
	}
	
	public static void assertPropertyDescriptors(String message, PropertyDescriptor[] expected,
		PropertyDescriptor[] actual)
	{
		// TODO: implement PropertyDescriptors.equals since PropertyDescriptor.equals does not consider
		// FeatureDescriptor fields
		if (!Arrays.equals(expected, actual))
		{
			failNotEquals(message, toString(expected), toString(actual));
		}
	}
	
	public static void assertEventSetDescriptor(EventSetDescriptor expected, EventSetDescriptor actual)
	{
		assertEventSetDescriptor(null, expected, actual);
	}
	
	public static void assertEventSetDescriptor(String message, EventSetDescriptor expected, EventSetDescriptor actual)
	{
		if (!BeanUtils.equals(expected, actual))
		{
			failNotEquals(message, EventSets.toString(expected), EventSets.toString(actual));
		}
	}
	
	public static void assertEventSetDescriptors(EventSetDescriptor[] expected, EventSetDescriptor[] actual)
	{
		assertEventSetDescriptors(null, expected, actual);
	}
	
	public static void assertEventSetDescriptors(String message, EventSetDescriptor[] expected,
		EventSetDescriptor[] actual)
	{
		if (!equals(expected, actual))
		{
			failNotEquals(message, toString(expected), toString(actual));
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static boolean equals(EventSetDescriptor[] descriptors1, EventSetDescriptor[] descriptors2)
	{
		if (descriptors1 == null || descriptors2 == null)
		{
			return descriptors1 == descriptors2;
		}
		
		if (descriptors1.length != descriptors2.length)
		{
			return false;
		}
		
		for (int i = 0; i < descriptors1.length; i++)
		{
			if (!BeanUtils.equals(descriptors1[i], descriptors2[i]))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static String toString(PropertyDescriptor[] descriptors)
	{
		if (descriptors == null)
		{
			return "null";
		}
		
		StringBuilder builder = new StringBuilder("[");
		
		for (int i = 0; i < descriptors.length; i++)
		{
			builder.append(Properties.toString(descriptors[i]));
		}
		
		builder.append("]");
		
		return builder.toString();
	}
	
	private static String toString(EventSetDescriptor[] descriptors)
	{
		if (descriptors == null)
		{
			return "null";
		}
		
		StringBuilder builder = new StringBuilder("[");
		
		for (int i = 0; i < descriptors.length; i++)
		{
			builder.append(EventSets.toString(descriptors[i]));
		}
		
		builder.append("]");
		
		return builder.toString();
	}
	
	private static void failNotEquals(String message, Object expected, Object actual)
	{
		StringBuilder builder = new StringBuilder();
		
		if (message != null)
		{
			builder.append(message).append(" ");
		}
		
		builder.append("expected:<").append(expected).append("> but was:<").append(actual).append(">");
		
		fail(builder.toString());
	}
}
