/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/BeanPropertyEvaluator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: BeanPropertyEvaluator.java 87543 2011-05-02 15:54:57Z mark@IIZUKA.CO.UK $
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
