/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/filter/RangeNodeFilterTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.filter;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.NodeFilter;

import uk.co.iizuka.common.xml.dom.RangeUtils;

/**
 * Tests <code>RangeNodeFilter</code>.
 * 
 * @author Mark Hobson
 * @version $Id: RangeNodeFilterTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see RangeNodeFilter
 */
public class RangeNodeFilterTest extends AbstractNodeFilterTest
{
	// fields -----------------------------------------------------------------
	
	private Document document;
	
	private Element root;
	
	private Element a;
	
	private Text x;
	
	private Element b;
	
	private Text y;
	
	// TestCase methods -------------------------------------------------------
	
	@Before
	public void setUp()
	{
		RangeUtils.ensureRange2(getDocument());
		
		document = getDocument();
		
		root = (Element) document.appendChild(document.createElement("root"));
		
		a = (Element) root.appendChild(document.createElement("a"));
		x = (Text) a.appendChild(document.createTextNode("x"));
		
		b = (Element) root.appendChild(document.createElement("b"));
		y = (Text) b.appendChild(document.createTextNode("y"));
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void nodeSelected()
	{
		assertAccept(root, 0, root, 1, a);
	}
	
	@Test
	public void nodeUnselectedBeforeStart()
	{
		assertReject(root, 1, root, 2, a);
	}
	
	@Test
	public void nodeUnselectedAfterEnd()
	{
		assertReject(root, 0, root, 1, b);
	}
	
	@Test
	public void nodeUnselectedAncestorOfBoth()
	{
		assertSkip(root, 0, root, 2, root);
	}
	
	@Test
	public void nodePartiallySelectedByStart()
	{
		assertSkip(root, 1, document, 1, root);
	}
	
	@Test
	public void nodePartiallySelectedByEnd()
	{
		assertSkip(document, 0, root, 1, root);
	}
	
	@Test
	public void textSelected()
	{
		assertAccept(x, 0, x, 1, x);
	}
	
	@Test
	public void textUnselectedBeforeStart()
	{
		assertReject(y, 0, y, 1, x);
	}
	
	@Test
	public void textUnselectedAfterStart()
	{
		assertReject(x, 0, x, 1, y);
	}
	
	@Test
	public void textPartiallySelectedByStart()
	{
		assertAccept(x, 1, root, 1, x);
	}
	
	@Test
	public void textPartiallySelectedByEnd()
	{
		assertAccept(root, 1, y, 0, y);
	}
	
	// protected methods ------------------------------------------------------
	
	protected void assertAccept(Node startContainer, int startOffset, Node endContainer, int endOffset, Node node)
	{
		assertAccept(buildNodeFilter(startContainer, startOffset, endContainer, endOffset), node);
	}
	
	protected void assertReject(Node startContainer, int startOffset, Node endContainer, int endOffset, Node node)
	{
		assertReject(buildNodeFilter(startContainer, startOffset, endContainer, endOffset), node);
	}
	
	protected void assertSkip(Node startContainer, int startOffset, Node endContainer, int endOffset, Node node)
	{
		assertSkip(buildNodeFilter(startContainer, startOffset, endContainer, endOffset), node);
	}
	
	// private methods --------------------------------------------------------
	
	private NodeFilter buildNodeFilter(Node startContainer, int startOffset, Node endContainer, int endOffset)
	{
		Range range = RangeUtils.createRange(startContainer, startOffset, endContainer, endOffset);
		
		return new RangeNodeFilter(range);
	}
}
