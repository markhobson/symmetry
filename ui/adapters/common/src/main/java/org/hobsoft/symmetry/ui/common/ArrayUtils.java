/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/ArrayUtils.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ArrayUtils.java 98237 2012-02-02 16:27:34Z mark@IIZUKA.CO.UK $
 */
final class ArrayUtils
{
	// constructors -----------------------------------------------------------
	
	private ArrayUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Object merge(Class<?> componentType, Object... arrays)
	{
		return merge(componentType, Arrays.asList(arrays));
	}
	
	public static Object merge(Class<?> componentType, Collection<Object> arrays)
	{
		int totalLength = getTotalLength(arrays);
		
		Object target = Array.newInstance(componentType, totalLength);
		int index = 0;
		
		for (Object array : arrays)
		{
			if (array != null)
			{
				int length = Array.getLength(array);
				System.arraycopy(array, 0, target, index, length);
				
				index += length;
			}
		}
		
		return target;
	}
	
	// private methods --------------------------------------------------------
	
	private static int getTotalLength(Collection<Object> arrays)
	{
		int length = 0;
		
		for (Object array : arrays)
		{
			if (array != null)
			{
				length += Array.getLength(array);
			}
		}
		
		return length;
	}
}
