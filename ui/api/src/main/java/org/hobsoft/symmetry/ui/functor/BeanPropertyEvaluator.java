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
	
	private static final Pattern PROPERTY_NAME_PATTERN = Pattern.compile("\\w+");
	
	private static final Pattern SEPARATOR_PATTERN = Pattern.compile("\\.");
	
	private static final Pattern PROPERTY_PATH_PATTERN = Pattern.compile(PROPERTY_NAME_PATTERN + "(?:"
		+ SEPARATOR_PATTERN + PROPERTY_NAME_PATTERN + ")*");
	
	private static final Pattern TOKEN_PATTERN = Pattern.compile("\\{(" + PROPERTY_PATH_PATTERN + ")\\}");
	
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
			String token = matcher.group(1);
			String replacement = evaluateToken(token, bean);
			
			matcher.appendReplacement(buffer, replacement);
		}
		
		matcher.appendTail(buffer);
		
		return buffer.toString();
	}
	
	// private methods --------------------------------------------------------
	
	private static String evaluateToken(String token, Object bean)
	{
		try
		{
			return String.valueOf(evaluatePropertyPath(token, bean));
		}
		catch (NoSuchPropertyException exception)
		{
			return defaultTokenValue(token);
		}
	}
	
	private static Object evaluatePropertyPath(String propertyPath, Object bean)
	{
		String[] propertyNames = propertyPath.split(SEPARATOR_PATTERN.pattern());
		
		for (String propertyName : propertyNames)
		{
			if (bean == null)
			{
				throw new NoSuchPropertyException(null, propertyName);
			}
			
			bean = evaluatePropertyName(propertyName, bean);
		}
		
		return bean;
	}
	
	private static Object evaluatePropertyName(String propertyName, Object bean)
	{
		PropertyDescriptor propertyDescriptor = Properties.getDescriptor(bean.getClass(), propertyName);
		Object value = Properties.get(bean, propertyDescriptor);

		return value;
	}
	
	private static String defaultTokenValue(String name)
	{
		return String.format("{%s}", name);
	}
}
