/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/internal/Preconditions.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Preconditions.java 99068 2012-03-08 13:05:12Z mark@IIZUKA.CO.UK $
 */
public final class Preconditions
{
	// TODO: can we achieve these with Guava?
	
	// constructors -----------------------------------------------------------
	
	private Preconditions()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	public static int checkNonNegative(int value, String name)
	{
		checkArgument(value >= 0, "%s must be non-negative: %s", name, value);
		
		return value;
	}

	public static int checkPositive(int value, String name)
	{
		checkArgument(value > 0, "%s must be positive: %s", name, value);
		
		return value;
	}

	public static void checkElementIndexes(int[] indexes, int size, String name)
	{
		checkNonNegative(size, "size");
		
		for (int index : indexes)
		{
			if (index < 0)
			{
				throw new IndexOutOfBoundsException(String.format("%s must not be negative: %s", name,
					Arrays.toString(indexes)));
			}
			
			if (index >= size)
			{
				throw new IndexOutOfBoundsException(String.format("%s must be less than %d: %s", name, size,
					Arrays.toString(indexes)));
			}
		}
	}
}
