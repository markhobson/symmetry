/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/EmptyCharacterDataFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.traversal.NodeFilter;

/**
 * Tests <code>EmptyCharacterDataFilter</code>.
 * 
 * @author Mark Hobson
 * @see EmptyCharacterDataFilter
 */
public class EmptyCharacterDataFilterTest extends AbstractNodeFilterTest
{
	// fields -----------------------------------------------------------------
	
	private NodeFilter filter;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		filter = new EmptyCharacterDataFilter();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void emptyTextNode()
	{
		assertAccept(filter, getDocument().createTextNode(""));
	}
	
	@Test
	public void nonEmptyTextNode()
	{
		assertSkip(filter, getDocument().createTextNode("a"));
	}
	
	@Test
	public void emptyCommentNode()
	{
		assertAccept(filter, getDocument().createComment(""));
	}
	
	@Test
	public void nonEmptyCommentNode()
	{
		assertSkip(filter, getDocument().createComment("a"));
	}
	
	@Test
	public void emptyCDATASectionNode()
	{
		assertAccept(filter, getDocument().createCDATASection(""));
	}
	
	@Test
	public void nonEmptyCDATASectionNode()
	{
		assertSkip(filter, getDocument().createCDATASection("a"));
	}
}
