/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/TinyObjectUtils.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TinyObjectUtils.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
final class TinyObjectUtils
{
	// types ------------------------------------------------------------------
	
	private static class FieldComparator implements Comparator<Field>
	{
		// constants --------------------------------------------------------------
		
		public static final FieldComparator INSTANCE = new FieldComparator();
		
		// Comparator methods -----------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compare(Field field1, Field field2)
		{
			return field1.getName().compareTo(field2.getName());
		}
	}
	
	// constructors -----------------------------------------------------------
	
	private TinyObjectUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static List<Field> getOrderedDeclaredFields(Class<?> klass)
	{
		List<Field> fields = new ArrayList<Field>();
		
		fields.addAll(Arrays.asList(klass.getDeclaredFields()));
		
		Collections.sort(fields, FieldComparator.INSTANCE);
		
		return fields;
	}
}
