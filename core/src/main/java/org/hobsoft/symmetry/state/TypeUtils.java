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
package org.hobsoft.symmetry.state;

/**
 * 
 * 
 * @author Mark Hobson
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
