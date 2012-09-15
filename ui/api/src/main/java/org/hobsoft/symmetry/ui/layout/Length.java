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
package org.hobsoft.symmetry.ui.layout;

import com.google.common.base.Objects;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkNonNegative;

import static com.google.common.base.Preconditions.checkNotNull;

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
