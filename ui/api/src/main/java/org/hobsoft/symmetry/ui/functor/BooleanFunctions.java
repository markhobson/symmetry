/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/BooleanFunctions.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: BooleanFunctions.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public final class BooleanFunctions
{
	// constants --------------------------------------------------------------
	
	private static final Function<Boolean, String> TRUE_FALSE = booleanToString("True", "False");
	
	private static final Function<Boolean, String> YES_NO = booleanToString("Yes", "No");
	
	private static final Function<Boolean, String> ON_OFF = booleanToString("On", "Off");
	
	private static final Function<Boolean, String> ONE_ZERO = booleanToString("1", "0");
	
	// constructors -----------------------------------------------------------
	
	private BooleanFunctions()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Function<Boolean, String> trueFalse()
	{
		return TRUE_FALSE;
	}
	
	public static Function<Boolean, String> yesNo()
	{
		return YES_NO;
	}
	
	public static Function<Boolean, String> onOff()
	{
		return ON_OFF;
	}
	
	public static Function<Boolean, String> oneZero()
	{
		return ONE_ZERO;
	}
	
	// private methods --------------------------------------------------------
	
	private static Function<Boolean, String> booleanToString(final String trueValue, final String falseValue)
	{
		return new Function<Boolean, String>()
		{
			@Override
			public String apply(Boolean value)
			{
				if (value == null)
				{
					return null;
				}
				
				return value ? trueValue : falseValue;
			}
		};
	}
}
