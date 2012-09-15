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

import java.beans.PropertyDescriptor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hobsoft.symmetry.support.bean.NoSuchPropertyException;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 */
final class BeanPropertyEvaluator
{
	// constants --------------------------------------------------------------
	
	private static final Pattern TOKEN_PATTERN = Pattern.compile("\\{(\\w+)\\}");
	
	// constructors -----------------------------------------------------------
	
	private BeanPropertyEvaluator()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	public static String evaluate(String expression, Object bean)
	{
		if (expression == null)
		{
			return null;
		}
		
		Matcher matcher = TOKEN_PATTERN.matcher(expression);
		StringBuffer buffer = new StringBuffer();
		
		while (matcher.find())
		{
			String parameterName = matcher.group(1);
			String replacement = evaluateToken(parameterName, bean);
			
			matcher.appendReplacement(buffer, replacement);
		}
		
		matcher.appendTail(buffer);
		
		return buffer.toString();
	}
	
	// private methods --------------------------------------------------------
	
	private static String evaluateToken(String name, Object bean)
	{
		if (bean == null)
		{
			return defaultTokenValue(name);
		}
		
		try
		{
			PropertyDescriptor propertyDescriptor = Properties.getDescriptor(bean.getClass(), name);
			Object value = Properties.get(bean, propertyDescriptor);

			return String.valueOf(value);
		}
		catch (NoSuchPropertyException exception)
		{
			return defaultTokenValue(name);
		}
	}
	
	private static String defaultTokenValue(String name)
	{
		return String.format("{%s}", name);
	}
}
