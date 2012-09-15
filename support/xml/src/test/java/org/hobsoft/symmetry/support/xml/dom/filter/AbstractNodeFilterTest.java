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
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.hobsoft.symmetry.support.xml.dom.AbstractDOMTestCase;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

import static org.junit.Assert.fail;

/**
 * Provides a basis for <code>NodeFilter</code> tests.
 * 
 * @author Mark Hobson
 */
public abstract class AbstractNodeFilterTest extends AbstractDOMTestCase
{
	// protected methods ------------------------------------------------------
	
	/**
	 * Asserts that the specified <code>NodeFilter</code> accepts the specified node.
	 * 
	 * @param filter
	 *            the filter to test
	 * @param node
	 *            the node to test
	 * @throws AssertionError
	 *             if the assertion failed
	 */
	protected void assertAccept(NodeFilter filter, Node node)
	{
		assertNodeFilter(NodeFilter.FILTER_ACCEPT, filter, node);
	}
	
	/**
	 * Asserts that the specified <code>NodeFilter</code> rejects the specified node.
	 * 
	 * @param filter
	 *            the filter to test
	 * @param node
	 *            the node to test
	 * @throws AssertionError
	 *             if the assertion failed
	 */
	protected void assertReject(NodeFilter filter, Node node)
	{
		assertNodeFilter(NodeFilter.FILTER_REJECT, filter, node);
	}
	
	/**
	 * Asserts that the specified <code>NodeFilter</code> skips the specified node.
	 * 
	 * @param filter
	 *            the filter to test
	 * @param node
	 *            the node to test
	 * @throws AssertionError
	 *             if the assertion failed
	 */
	protected void assertSkip(NodeFilter filter, Node node)
	{
		assertNodeFilter(NodeFilter.FILTER_SKIP, filter, node);
	}
	
	// private methods --------------------------------------------------------

	private void assertNodeFilter(short expected, NodeFilter filter, Node node)
	{
		assertNodeFilterResult(expected, filter.acceptNode(node));
	}
	
	private void assertNodeFilterResult(short expected, short actual)
	{
		assertNodeFilterResult(null, expected, actual);
	}
	
	private void assertNodeFilterResult(String message, short expected, short actual)
	{
		if (expected != actual)
		{
			StringBuilder builder = new StringBuilder();
			
			if (message != null && message.length() > 0)
			{
				builder.append(message);
			}
			
			builder.append("expected:<");
			builder.append(toString(expected));
			builder.append("> but was:<");
			builder.append(toString(actual));
			builder.append(">");

			fail(builder.toString());
		}
	}
	
	private String toString(short result)
	{
		String string;
		
		switch (result)
		{
			case NodeFilter.FILTER_ACCEPT:
				string = "FILTER_ACCEPT";
				break;
				
			case NodeFilter.FILTER_REJECT:
				string = "FILTER_REJECT";
				break;
				
			case NodeFilter.FILTER_SKIP:
				string = "FILTER_SKIP";
				break;
				
			default:
				string = Short.toString(result);
				break;
		}
		
		return string;
	}
}
