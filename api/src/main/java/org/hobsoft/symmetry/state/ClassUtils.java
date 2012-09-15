/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/state/ClassUtils.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ClassUtils.java 94221 2011-10-10 14:16:13Z mark@IIZUKA.CO.UK $
 */
final class ClassUtils
{
	// constructors -----------------------------------------------------------
	
	private ClassUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Class<?> box(Class<?> type)
	{
		Class<?> boxedType;
		
		if (Boolean.TYPE.equals(type))
		{
			boxedType = Boolean.class;
		}
		else if (Byte.TYPE.equals(type))
		{
			boxedType = Byte.class;
		}
		else if (Short.TYPE.equals(type))
		{
			boxedType = Short.class;
		}
		else if (Integer.TYPE.equals(type))
		{
			boxedType = Integer.class;
		}
		else if (Long.TYPE.equals(type))
		{
			boxedType = Long.class;
		}
		else if (Character.TYPE.equals(type))
		{
			boxedType = Character.class;
		}
		else if (Float.TYPE.equals(type))
		{
			boxedType = Float.class;
		}
		else if (Double.TYPE.equals(type))
		{
			boxedType = Double.class;
		}
		else if (Void.TYPE.equals(type))
		{
			boxedType = Void.class;
		}
		else
		{
			boxedType = type;
		}
		
		return boxedType;
	}
}
