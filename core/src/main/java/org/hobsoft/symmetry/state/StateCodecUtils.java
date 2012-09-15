/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/state/StateCodecUtils.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.lang.reflect.Field;
import java.util.EventObject;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StateCodecUtils.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
final class StateCodecUtils
{
	// constructors -----------------------------------------------------------
	
	private StateCodecUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void setSource(EventObject eventObject, Object source)
	{
		try
		{
			Field field = EventObject.class.getDeclaredField("source");
			field.setAccessible(true);
			field.set(eventObject, source);
		}
		catch (NoSuchFieldException exception)
		{
			throw new InternalError();
		}
		catch (IllegalAccessException exception)
		{
			throw new InternalError();
		}
	}
}
