/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/RangeAssert.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.w3c.dom.Node;
import org.w3c.dom.ranges.Range;

/**
 * Provides methods to assert the equality of DOM Range objects.
 * 
 * @author Mark Hobson
 * @version $Id: RangeAssert.java 69821 2010-01-21 16:46:57Z mark@IIZUKA.CO.UK $
 */
public final class RangeAssert
{
	// TODO: move to a proper test component of common-xml project
	
	// constructors -----------------------------------------------------------
	
	private RangeAssert()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Asserts whether the specified range is equal to the given boundary-points.
	 * 
	 * @param expectedStartContainer
	 *            the expected start boundary-point container
	 * @param expectedStartOffset
	 *            the expected start boundary-point offset
	 * @param expectedEndContainer
	 *            the expected end boundary-point container
	 * @param expectedEndOffset
	 *            the expected end boundary-point offset
	 * @param actual
	 *            the actual range
	 * @throws AssertionError
	 *             if the range is not equal to the specified boundary-points
	 */
	public static void assertRange(Node expectedStartContainer, int expectedStartOffset, Node expectedEndContainer,
		int expectedEndOffset, Range actual)
	{
		Range expected = RangeUtils.createRange(expectedStartContainer, expectedStartOffset, expectedEndContainer,
			expectedEndOffset);
		
		assertRange(expected, actual);
	}
	
	/**
	 * Asserts whether the specified ranges are equal.
	 * 
	 * @param expected
	 *            the expected range
	 * @param actual
	 *            the actual range
	 * @throws AssertionError
	 *             if the ranges are not equal
	 */
	public static void assertRange(Range expected, Range actual)
	{
		if (!RangeUtils.isEqual(expected, actual))
		{
			StringBuilder builder = new StringBuilder();

			builder.append("Range expected:<");
			appendRange(builder, expected);
			
			builder.append("> but was:<");
			appendRange(builder, actual);
			
			builder.append(">");
			
			Assert.fail(builder.toString());
		}
	}
	
	/**
	 * Asserts whether the specified lists of ranges are equal.
	 * 
	 * @param expected
	 *            the expected list of ranges
	 * @param actual
	 *            the actual list of ranges range
	 * @throws AssertionError
	 *             if the list of ranges are not equal
	 */
	public static void assertRanges(List<Range> expected, List<Range> actual)
	{
		if (!equals(expected, actual))
		{
			StringBuilder builder = new StringBuilder();
			
			builder.append("Ranges expected:<");
			appendRanges(builder, expected);
			
			builder.append("> but was:<");
			appendRanges(builder, actual);
			
			builder.append(">");
			
			Assert.fail(builder.toString());
		}
	}
	
	/**
	 * Asserts that the specified range has the given start boundary-point.
	 * 
	 * @param expectedContainer
	 *            the expected start boundary-point container
	 * @param expectedOffset
	 *            the expected start boundary-point offset
	 * @param actual
	 *            the actual range
	 * @throws AssertionError
	 *             if the specified range does not have the given start boundary-point
	 */
	public static void assertStart(Node expectedContainer, int expectedOffset, Range actual)
	{
		assertBoundaryPoint("Range start", expectedContainer, expectedOffset, actual.getStartContainer(),
			actual.getStartOffset());
	}
	
	/**
	 * Asserts that the specified range has the given end boundary-point.
	 * 
	 * @param expectedContainer
	 *            the expected end boundary-point container
	 * @param expectedOffset
	 *            the expected end boundary-point offset
	 * @param actual
	 *            the actual range
	 * @throws AssertionError
	 *             if the specified range does not have the given end boundary-point
	 */
	public static void assertEnd(Node expectedContainer, int expectedOffset, Range actual)
	{
		assertBoundaryPoint("Range end", expectedContainer, expectedOffset, actual.getEndContainer(),
			actual.getEndOffset());
	}
	
	/**
	 * Asserts that the specified boundary-points are equal.
	 * 
	 * @param message
	 *            the assertion error message to use
	 * @param expectedContainer
	 *            the expected boundary-point container
	 * @param expectedOffset
	 *            the expected boundary-point offset
	 * @param actualContainer
	 *            the actual boundary-point container
	 * @param actualOffset
	 *            the actual boundary-point offset
	 * @throws AssertionError
	 *             if the specified boundary-points are not equal
	 */
	public static void assertBoundaryPoint(String message, Node expectedContainer, int expectedOffset,
		Node actualContainer, int actualOffset)
	{
		if (!RangeUtils.isEqual(expectedContainer, expectedOffset, actualContainer, actualOffset))
		{
			StringBuilder builder = new StringBuilder();
			
			builder.append(message).append(" expected:<");
			appendBoundaryPoint(builder, expectedContainer, expectedOffset);
			
			builder.append("> but was:<");
			appendBoundaryPoint(builder, actualContainer, actualOffset);
			
			builder.append(">");
			
			Assert.fail(builder.toString());
		}
	}
	
	/**
	 * Asserts that the specified range has no partially selected non-text nodes.
	 * 
	 * @param range
	 *            the range to test
	 * @throws AssertionError
	 *             if the range has partially selected non-text nodes
	 */
	public static void assertSurroundable(Range range)
	{
		if (!RangeUtils.isSurroundable(range))
		{
			StringBuilder builder = new StringBuilder();

			builder.append("Range expected surroundable:<");
			appendRange(builder, range);
			
			builder.append(">");
			
			Assert.fail(builder.toString());
		}
	}
	
	/**
	 * Asserts that the specified list of ranges have no partially selected non-text nodes.
	 * 
	 * @param ranges
	 *            the list of ranges to test
	 * @throws AssertionError
	 *             if a range has partially selected non-text nodes
	 */
	public static void assertSurroundable(List<Range> ranges)
	{
		for (Range range : ranges)
		{
			assertSurroundable(range);
		}
	}
	
	// private methods --------------------------------------------------------
	
	/**
	 * Gets whether the specified list of ranges are equal.
	 * 
	 * @param ranges1
	 *            the first list of ranges to test
	 * @param ranges2
	 *            the second list of ranges to test
	 * @return <code>true</code> if the specified list of ranges are equal
	 */
	private static boolean equals(List<Range> ranges1, List<Range> ranges2)
	{
		if (ranges1 == null || ranges2 == null)
		{
			return (ranges1 == ranges2);
		}
		
		if (ranges1.size() != ranges2.size())
		{
			return false;
		}
		
		for (int i = 0; i < ranges1.size(); i++)
		{
			if (!RangeUtils.isEqual(ranges1.get(i), ranges2.get(i)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Appends a string representation of the specified range to the given string builder.
	 * 
	 * @param builder
	 *            the string builder to append to
	 * @param range
	 *            the range whose string representation to append
	 * @return the string builder
	 */
	private static StringBuilder appendRange(StringBuilder builder, Range range)
	{
		if (range == null)
		{
			return builder.append(range);
		}
		
		builder.append('[');
		appendBoundaryPoint(builder, range.getStartContainer(), range.getStartOffset());
		builder.append(", ");
		appendBoundaryPoint(builder, range.getEndContainer(), range.getEndOffset());
		builder.append(']');
		
		return builder;
	}
	
	/**
	 * Appends a string representation of the specified list of ranges to the given string builder.
	 * 
	 * @param builder
	 *            the string builder to append to
	 * @param ranges
	 *            the list of ranges whose string representation to append
	 * @return the string builder
	 */
	private static StringBuilder appendRanges(StringBuilder builder, List<Range> ranges)
	{
		if (ranges == null)
		{
			return builder.append(ranges);
		}
		
		builder.append('[');
		
		for (Iterator<Range> iterator = ranges.iterator(); iterator.hasNext(); )
		{
			appendRange(builder, iterator.next());
			
			if (iterator.hasNext())
			{
				builder.append(',');
			}
		}
		
		builder.append(']');
		
		return builder;
	}
	
	/**
	 * Appends a string representation of the specified boundary-point to the given string builder.
	 * 
	 * @param builder
	 *            the string builder to append to
	 * @param container
	 *            the boundary-point's container whose string representation to append
	 * @param offset
	 *            the boundary-point's offset whose string representation to append
	 * @return the string builder
	 */
	private static StringBuilder appendBoundaryPoint(StringBuilder builder, Node container, int offset)
	{
		builder.append(container.getNodeName());
		builder.append(':');
		builder.append(offset);
		
		return builder;
	}
}
