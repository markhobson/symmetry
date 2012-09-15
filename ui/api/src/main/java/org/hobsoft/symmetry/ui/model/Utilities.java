/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/Utilities.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 */
final class Utilities
{
	// TODO: can we achieve these with Guava?
	
	// constructors -----------------------------------------------------------
	
	private Utilities()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void setSize(List<?> list, int size)
	{
		if (size < list.size())
		{
			crop(list, size);
		}
		else if (size > list.size())
		{
			grow(list, size);
		}
	}

	public static void grow(Collection<?> collection, int size)
	{
		growCapture(collection, size);
	}
	
	public static void crop(List<?> list, int size)
	{
		// TODO: there must be a more efficient way of doing this, e.g. using subList?
		
		int n = list.size() - size;
		
		while (n > 0)
		{
			list.remove(list.size() - 1);
			n--;
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static <E> void growCapture(Collection<E> collection, int size)
	{
		int n = size - collection.size();
		
		if (n > 0)
		{
			collection.addAll(Collections.<E>nCopies(n, null));
		}
	}
}
