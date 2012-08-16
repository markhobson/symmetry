/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/TypeUtils.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.state;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TypeUtils.java 94241 2011-10-11 08:32:01Z mark@IIZUKA.CO.UK $
 */
final class TypeUtils
{
	// constructors -----------------------------------------------------------
	
	private TypeUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Object defaultValue(Class<?> type)
	{
		if (type == null)
		{
			throw new NullPointerException("type cannot be null");
		}
		
		Object defaultValue;
		
		if (Boolean.TYPE.equals(type))
		{
			defaultValue = false;
		}
		else if (Byte.TYPE.equals(type))
		{
			defaultValue = (byte) -1;
		}
		else if (Short.TYPE.equals(type))
		{
			defaultValue = (short) -1;
		}
		else if (Integer.TYPE.equals(type))
		{
			defaultValue = -1;
		}
		else if (Long.TYPE.equals(type))
		{
			defaultValue = -1L;
		}
		else if (Character.TYPE.equals(type))
		{
			defaultValue = '\0';
		}
		else if (Float.TYPE.equals(type))
		{
			defaultValue = -1f;
		}
		else if (Double.TYPE.equals(type))
		{
			defaultValue = -1.0;
		}
		else
		{
			defaultValue = null;
		}
		
		return defaultValue;
	}
}
