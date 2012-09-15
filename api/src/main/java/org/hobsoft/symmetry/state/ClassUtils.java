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
