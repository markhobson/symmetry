/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/Functions.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.Map;

import org.hobsoft.symmetry.ui.event.TableEvent;
import org.hobsoft.symmetry.ui.event.TableModelEvent;
import org.hobsoft.symmetry.ui.model.BeanTableModel;
import org.hobsoft.symmetry.ui.model.TableModel;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Functions.java 98450 2012-02-14 14:47:35Z mark@IIZUKA.CO.UK $
 */
public final class Functions
{
	// types ------------------------------------------------------------------
	
	private static class EnumToTitleCaseFunction<E extends Enum<E>> implements Function<E, String>
	{
		@Override
		public String apply(E enumConstant)
		{
			return (enumConstant != null) ? unconstantize(enumConstant.name()) : null;
		}
	}

	private static class EnumToCamelCaseFunction<E extends Enum<E>> implements Function<E, String>
	{
		@Override
		public String apply(E enumConstant)
		{
			if (enumConstant == null)
			{
				return null;
			}
			
			String camelCase = unconstantize(enumConstant.name());
			return uncapitalize(camelCase.replace(" ", ""));
		}
	}
	
	// constants --------------------------------------------------------------
	
	private static final Function<Object, Object> IDENTITY = new Function<Object, Object>()
	{
		@Override
		public Object apply(Object input)
		{
			return input;
		}
	};
	
	private static final Function<Object, Boolean> NOT_NULL = new Function<Object, Boolean>()
	{
		@Override
		public Boolean apply(Object input)
		{
			return (input != null);
		}
	};
	
	private static final Function<Object, String> TO_STRING = new Function<Object, String>()
	{
		@Override
		public String apply(Object input)
		{
			return (input != null) ? input.toString() : null;
		}
	};
	
	private static final Function<String, String> EMPTY_STRING_TO_NULL = new Function<String, String>()
	{
		@Override
		public String apply(String string)
		{
			return (string == null || string.length() == 0) ? null : string;
		}
	};
	
	private static final Function<String, Integer> PARSE_INTEGER = new Function<String, Integer>()
	{
		@Override
		public Integer apply(String string)
		{
			return (string != null) ? Integer.valueOf(string) : null;
		}
	};
	
	private static final Function<PropertyChangeEvent, Object> PROPERTY_NEW_VALUE =
		new Function<PropertyChangeEvent, Object>()
		{
			@Override
			public Object apply(PropertyChangeEvent event)
			{
				return event.getNewValue();
			}
		};
	
	private static final Function<TableEvent, Object> TABLE_VALUE = new Function<TableEvent, Object>()
	{
		@Override
		public Object apply(TableEvent event)
		{
			TableModel model = event.getSource().getModel();
			int row = event.getRow();
			int column = event.getColumn();

			if (model instanceof BeanTableModel<?>)
			{
				return ((BeanTableModel<?>) model).getBeanAt(row);
			}
			
			return model.getValueAt(row, column);
		}
	};
	
	private static final Function<TableModelEvent, Integer> TABLE_ROWS = new Function<TableModelEvent, Integer>()
	{
		@Override
		public Integer apply(TableModelEvent event)
		{
			return event.getSource().getRowCount();
		}
	};

	// constructors -----------------------------------------------------------
	
	private Functions()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T> Function<T, T> identity()
	{
		// valid since function always returns input
		@SuppressWarnings("unchecked")
		Function<T, T> identity = (Function<T, T>) IDENTITY;
		
		return identity;
	}
	
	public static <T, U> Function<T, U> constant(final U value)
	{
		return new Function<T, U>()
		{
			@Override
			public U apply(T input)
			{
				return value;
			}
		};
	}
	
	public static <T> Function<T, Boolean> notNull()
	{
		// valid since T erases to Object
		@SuppressWarnings("unchecked")
		Function<T, Boolean> notNull = (Function<T, Boolean>) NOT_NULL;
		
		return notNull;
	}
	
	public static <T, U, V> Function<T, V> compose(final Function<T, U> first, final Function<U, V> second)
	{
		return new Function<T, V>()
		{
			@Override
			public V apply(T input)
			{
				return second.apply(first.apply(input));
			}
		};
	}
	
	public static <T, U> Function<T, U> chain(final Function<T, U> first, final Function<T, U> second)
	{
		return new Function<T, U>()
		{
			@Override
			public U apply(T input)
			{
				U value = first.apply(input);
				
				if (value == null)
				{
					value = second.apply(input);
				}
				
				return value;
			}
		};
	}
	
	public static <T, U> Function<T, U> cast(final Class<U> type)
	{
		return new Function<T, U>()
		{
			@Override
			public U apply(T input)
			{
				return type.cast(input);
			}
		};
	}
	
	public static <T> Function<T, String> toStringFunction()
	{
		// valid since T erases to Object
		@SuppressWarnings("unchecked")
		Function<T, String> toString = (Function<T, String>) TO_STRING;
		
		return toString;
	}
	
	public static Function<String, String> emptyStringToNull()
	{
		return EMPTY_STRING_TO_NULL;
	}
	
	public static Function<String, Integer> parseInteger()
	{
		return PARSE_INTEGER;
	}
	
	public static <K, V> Function<K, V> forMapEntry(K key, V value)
	{
		return forMap(Collections.singletonMap(key, value));
	}
	
	public static <K, V> Function<K, V> forMap(final Map<K, V> map)
	{
		return new Function<K, V>()
		{
			@Override
			public V apply(K key)
			{
				return map.get(key);
			}
		};
	}
	
	public static Function<PropertyChangeEvent, Object> propertyNewValue()
	{
		return PROPERTY_NEW_VALUE;
	}
	
	public static <T> Function<PropertyChangeEvent, T> propertyNewValue(Class<T> propertyType)
	{
		return compose(propertyNewValue(), cast(propertyType));
	}
	
	public static <E extends Enum<E>> Function<E, String> enumTitleCase()
	{
		// TODO: cache when satisfactory way of dealing with generics decided upon
		return new EnumToTitleCaseFunction<E>();
	}
	
	public static <E extends Enum<E>> Function<E, String> enumCamelCase()
	{
		// TODO: cache when satisfactory way of dealing with generics decided upon
		return new EnumToCamelCaseFunction<E>();
	}
	
	public static Function<Object, String> evaluate(final String expression)
	{
		return new Function<Object, String>()
		{
			@Override
			public String apply(Object bean)
			{
				return BeanPropertyEvaluator.evaluate(expression, bean);
			}
		};
	}
	
	public static Function<TableEvent, Object> tableValue()
	{
		return TABLE_VALUE;
	}
	
	public static Function<TableModelEvent, Integer> tableRows()
	{
		return TABLE_ROWS;
	}
	
	// private methods --------------------------------------------------------
	
	private static String unconstantize(String string)
	{
		StringBuilder builder = new StringBuilder(string);
		
		boolean capitialize = true;
		
		for (int i = 0; i < builder.length(); i++)
		{
			char c = builder.charAt(i);
			
			if (c == '_')
			{
				builder.setCharAt(i, ' ');
				capitialize = true;
			}
			else
			{
				if (!capitialize)
				{
					builder.setCharAt(i, Character.toLowerCase(c));
				}
				
				capitialize = false;
			}
		}
		
		return builder.toString();
	}
	
	private static String uncapitalize(String string)
	{
		if (string == null || string.length() == 0)
		{
			return string;
		}
		
		return Character.toLowerCase(string.charAt(0)) + string.substring(1);
	}
}
