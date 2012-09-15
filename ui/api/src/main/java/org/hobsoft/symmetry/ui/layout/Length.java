/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/layout/Length.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.layout;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkNonNegative;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class Length
{
	// types ------------------------------------------------------------------
	
	/**
	 * Unit of length value.
	 */
	public enum Unit
	{
		PIXELS,
		PERCENTAGE,
		FLEX;
	}

	// fields -----------------------------------------------------------------
	
	private final int value;
	
	private final Unit unit;
	
	// constructors -----------------------------------------------------------
	
	private Length(int value, Unit unit)
	{
		this.value = checkNonNegative(value, "value");
		this.unit = checkNotNull(unit, "unit");
	}
	
	// public methods ---------------------------------------------------------
	
	public int getValue()
	{
		return value;
	}
	
	public Unit getUnit()
	{
		return unit;
	}
	
	public static Length pixels(int pixels)
	{
		// TODO: cache popular values
		return new Length(pixels, Unit.PIXELS);
	}
	
	public static Length percentage(int percentage)
	{
		// TODO: cache popular values
		return new Length(percentage, Unit.PERCENTAGE);
	}
	
	public static Length flex(int flex)
	{
		// TODO: cache popular values
		return new Length(flex, Unit.FLEX);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode(value, unit);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Length))
		{
			return false;
		}
		
		Length length = (Length) object;
		
		return (value == length.value)
			&& unit.equals(length.unit);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return value + toString(unit);
	}
	
	// private methods --------------------------------------------------------
	
	private static String toString(Unit unit)
	{
		String string;
		
		switch (unit)
		{
			case PIXELS:
				string = "px";
				break;
				
			case PERCENTAGE:
				string = "%";
				break;
				
			case FLEX:
				string = "*";
				break;
				
			default:
				string = "?";
				break;
		}
		
		return string;
	}
}
