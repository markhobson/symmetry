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
package org.hobsoft.symmetry.ui.functor;

import com.google.common.base.Function;

/**
 * 
 * 
 * @author Mark Hobson
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
