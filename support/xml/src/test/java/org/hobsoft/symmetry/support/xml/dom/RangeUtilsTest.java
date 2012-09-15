/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dom/RangeUtilsTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.NodeFilter;
import org.xml.sax.SAXException;

/**
 * Tests <code>RangeUtils</code>.
 * 
 * @author Mark Hobson
 * @see RangeUtils
 */
public class RangeUtilsTest extends AbstractDOMTestCase
{
	// getMaxOffset tests -----------------------------------------------------
	
	@Test
	public void getMaxOffsetWithElement()
	{
		Element root = getDocument().createElement("root");
		Element child1 = getDocument().createElement("a");
		Element child2 = getDocument().createElement("b");
		Element child3 = getDocument().createElement("c");
		root.appendChild(child1);
		root.appendChild(child2);
		root.appendChild(child3);
		getDocument().appendChild(root);
		
		assertEquals(3, RangeUtils.getMaxOffset(root));
	}
	
	@Test
	public void getMaxOffsetWithText()
	{
		Element root = getDocument().createElement("root");
		Text text = getDocument().createTextNode("xyz");
		root.appendChild(text);
		getDocument().appendChild(root);
		
		assertEquals(3, RangeUtils.getMaxOffset(text));
	}
	
	@Test
	public void getMaxOffsetWithComment()
	{
		Comment comment = getDocument().createComment("xyz");
		getDocument().appendChild(comment);
		
		assertEquals(3, RangeUtils.getMaxOffset(comment));
	}
	
	@Test
	public void getMaxOffsetWithCDATASection()
	{
		Element root = getDocument().createElement("root");
		CDATASection cdata = getDocument().createCDATASection("xyz");
		root.appendChild(cdata);
		getDocument().appendChild(root);
		
		assertEquals(3, RangeUtils.getMaxOffset(cdata));
	}
	
	@Test
	public void getMaxOffsetWithProcessingInstruction()
	{
		ProcessingInstruction pi = getDocument().createProcessingInstruction("a", "xyz");
		getDocument().appendChild(pi);
		
		assertEquals(3, RangeUtils.getMaxOffset(pi));
	}
	
	// getBoundaryPointNode tests ---------------------------------------------
	
	@Test
	public void getBoundaryPointNodeForwards()
	{
		Element a = getDocument().createElement("a");
		getDocument().appendChild(a);
		
		assertEquals(a, RangeUtils.getBoundaryPointNode(getDocument(), 0, true));
	}
	
	@Test
	public void getBoundaryPointNodeForwardsText()
	{
		Element a = getDocument().createElement("a");
		Text b = getDocument().createTextNode("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		assertEquals(b, RangeUtils.getBoundaryPointNode(b, 0, true));
	}
	
	@Test
	public void getBoundaryPointNodeForwardsEnd()
	{
		Element a = getDocument().createElement("a");
		getDocument().appendChild(a);
		
		assertNull(RangeUtils.getBoundaryPointNode(getDocument(), 1, true));
	}
	
	@Test
	public void getBoundaryPointNodeForwardsEndText()
	{
		Element a = getDocument().createElement("a");
		Text b = getDocument().createTextNode("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		assertEquals(b, RangeUtils.getBoundaryPointNode(b, 1, true));
	}
	
	@Test
	public void getBoundaryPointNodeBackwards()
	{
		Element a = getDocument().createElement("a");
		getDocument().appendChild(a);
		
		assertEquals(a, RangeUtils.getBoundaryPointNode(getDocument(), 1, false));
	}
	
	@Test
	public void getBoundaryPointNodeBackwardsText()
	{
		Element a = getDocument().createElement("a");
		Text b = getDocument().createTextNode("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		assertEquals(b, RangeUtils.getBoundaryPointNode(b, 1, true));
	}
	
	@Test
	public void getBoundaryPointNodeBackwardsStart()
	{
		Element a = getDocument().createElement("a");
		getDocument().appendChild(a);
		
		assertNull(RangeUtils.getBoundaryPointNode(getDocument(), 0, false));
	}
	
	@Test
	public void getBoundaryPointNodeBackwardsStartText()
	{
		Element a = getDocument().createElement("a");
		Text b = getDocument().createTextNode("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		assertEquals(b, RangeUtils.getBoundaryPointNode(b, 0, true));
	}
	
	// getPartiallySelectedNodes tests ----------------------------------------
	
	@Test
	public void getPartiallySelectedNodesSelected()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(getDocument(), 0, getDocument(), 1);
		List<Node> expected = Collections.emptyList();
		assertEquals(expected, RangeUtils.getPartiallySelectedNodes(range));
	}
	
	@Test
	public void getPartiallySelectedNodesStartContainer()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(root, 0, getDocument(), 1);
		List<Node> expected = Arrays.asList(new Node[] {root});
		assertEquals(expected, RangeUtils.getPartiallySelectedNodes(range));
	}
	
	@Test
	public void getPartiallySelectedNodesEndContainer()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(getDocument(), 0, root, 0);
		List<Node> expected = Arrays.asList(new Node[] {root});
		assertEquals(expected, RangeUtils.getPartiallySelectedNodes(range));
	}
	
	@Test
	public void getPartiallySelectedNodesSelectedAncestor()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(element, 0, element, 0);
		List<Node> expected = Collections.emptyList();
		assertEquals(expected, RangeUtils.getPartiallySelectedNodes(range));
	}
	
	@Test
	public void getPartiallySelectedNodesStartAncestor()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(element, 0, getDocument(), 1);
		List<Node> expected = Arrays.asList(new Node[] {root, element});
		assertEquals(expected, RangeUtils.getPartiallySelectedNodes(range));
	}
	
	@Test
	public void getPartiallySelectedNodesEndAncestor()
	{
		Element root = getDocument().createElement("root");
		Element element = getDocument().createElement("element");
		root.appendChild(element);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(getDocument(), 0, element, 0);
		List<Node> expected = Arrays.asList(new Node[] {root, element});
		assertEquals(expected, RangeUtils.getPartiallySelectedNodes(range));
	}
	
	// selectWord tests -------------------------------------------------------
	
	@Test
	public void selectWordWithEmpty()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 0, text, 0);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 0, text, 0, range);
	}
	
	@Test
	public void selectWordAtStart()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("cat");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 0, text, 0);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 0, text, 3, range);
	}
	
	@Test
	public void selectWordAtEnd()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("cat");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 3, text, 3);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 0, text, 3, range);
	}
	
	@Test
	public void selectWordInMiddle()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("cat");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 1, text, 1);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 0, text, 3, range);
	}
	
	@Test
	public void selectWordInLineAtStart()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("cat dog pig");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 0, text, 0);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 0, text, 3, range);
	}
	
	@Test
	public void selectWordInLineAtEnd()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("cat dog pig");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 11, text, 11);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 8, text, 11, range);
	}
	
	@Test
	public void selectWordInLineInMiddleAtStartOfWord()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("cat dog pig");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 4, text, 4);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 4, text, 7, range);
	}
	
	@Test
	public void selectWordInLineInMiddleAtMiddleOfWord()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("cat dog pig");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 5, text, 5);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 4, text, 7, range);
	}
	
	@Test
	public void selectWordInLineInMiddleAtEndOfWord()
	{
		Element element = getDocument().createElement("element");
		Text text = getDocument().createTextNode("cat dog pig");
		element.appendChild(text);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text, 7, text, 7);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text, 7, text, 8, range);
	}
	
	@Test
	public void selectWordAcrossNodesInMiddle()
	{
		Element element = getDocument().createElement("element");
		Text text1 = getDocument().createTextNode("c");
		Text text2 = getDocument().createTextNode("a");
		Text text3 = getDocument().createTextNode("t");
		element.appendChild(text1);
		element.appendChild(text2);
		element.appendChild(text3);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text2, 0, text2, 0);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text1, 0, text3, 1, range);
	}
	
	@Test
	public void selectWordInNodesInMiddle()
	{
		Element element = getDocument().createElement("element");
		Text text1 = getDocument().createTextNode("cat ");
		Text text2 = getDocument().createTextNode("dog");
		Text text3 = getDocument().createTextNode(" pig");
		element.appendChild(text1);
		element.appendChild(text2);
		element.appendChild(text3);
		getDocument().appendChild(element);
		
		Range range = RangeUtils.createRange(text2, 1, text2, 1);
		RangeUtils.selectWord(range, getDocument());
		RangeAssert.assertRange(text2, 0, text2, 3, range);
	}
	
	// fracture tests ---------------------------------------------------------
	
	@Test
	public void fractureBoundaryPointTextContainer() throws SAXException, IOException
	{
		Document actual = parse("<root>cat</root>");
		
		Text text = (Text) NodeUtils.evaluateXPath(actual, "/root/#text");
		RangeUtils.fracture(text, 1, text);

		Document expected = split("<root>c|at</root>");
		
		assertDOM(expected, actual);
	}
	
	@Test
	public void fractureBoundaryPointTextContainerWithAncestor() throws SAXException, IOException
	{
		Document actual = parse("<root><element>cat</element></root>");
		
		Element element = (Element) NodeUtils.evaluateXPath(actual, "/root/element");
		Text text = (Text) NodeUtils.evaluateXPath(actual, "/root/element/#text");
		RangeUtils.fracture(text, 1, element);

		Document expected = split("<root><element>c</element><element>at</element></root>");
		
		assertDOM(expected, actual);
	}
	
	// split tests ------------------------------------------------------------
	
	@Test
	public void splitNoPartiallySelectedNodes()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(getDocument(), 0, getDocument(), 1);

		List<Range> expected = Collections.singletonList(range);
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}
	
	@Test
	public void splitStartContainerPartiallySelected()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(root, 0, getDocument(), 1);
		
		List<Range> expected = new ArrayList<Range>();
		expected.add(RangeUtils.createRange(root, 0, root, 0));
		expected.add(RangeUtils.createRange(getDocument(), 1, getDocument(), 1));
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}
	
	@Test
	public void splitStartAncestorPartiallySelected()
	{
		Element root = getDocument().createElement("root");
		Text x = getDocument().createTextNode("x");
		Element a = getDocument().createElement("a");
		Text y = getDocument().createTextNode("y");
		a.appendChild(x);
		root.appendChild(a);
		root.appendChild(y);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(x, 0, y, 1);
		
		List<Range> expected = new ArrayList<Range>();
		expected.add(RangeUtils.createRange(x, 0, a, 1));
		expected.add(RangeUtils.createRange(root, 1, y, 1));
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}

	@Test
	public void splitStartDeepAncestorPartiallySelected()
	{
		Element root = getDocument().createElement("root");
		Text x = getDocument().createTextNode("x");
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		Text y = getDocument().createTextNode("y");
		b.appendChild(x);
		a.appendChild(b);
		root.appendChild(a);
		root.appendChild(y);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(x, 0, y, 1);
		
		List<Range> expected = new ArrayList<Range>();
		expected.add(RangeUtils.createRange(x, 0, b, 1));
		expected.add(RangeUtils.createRange(a, 1, a, 1));
		expected.add(RangeUtils.createRange(root, 1, y, 1));
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}

	@Test
	public void splitEndContainerPartiallySelected()
	{
		Element root = getDocument().createElement("root");
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(getDocument(), 0, root, 0);
		
		List<Range> expected = new ArrayList<Range>();
		expected.add(RangeUtils.createRange(getDocument(), 0, getDocument(), 0));
		expected.add(RangeUtils.createRange(root, 0, root, 0));
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}
	
	@Test
	public void splitEndAncestorPartiallySelected()
	{
		Element root = getDocument().createElement("root");
		Text x = getDocument().createTextNode("x");
		Element a = getDocument().createElement("a");
		Text y = getDocument().createTextNode("y");
		a.appendChild(y);
		root.appendChild(x);
		root.appendChild(a);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(x, 0, y, 1);
		
		List<Range> expected = new ArrayList<Range>();
		expected.add(RangeUtils.createRange(x, 0, root, 1));
		expected.add(RangeUtils.createRange(a, 0, y, 1));
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}

	@Test
	public void splitEndDeepAncestorPartiallySelected()
	{
		Element root = getDocument().createElement("root");
		Text x = getDocument().createTextNode("x");
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		Text y = getDocument().createTextNode("y");
		b.appendChild(y);
		a.appendChild(b);
		root.appendChild(x);
		root.appendChild(a);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(x, 0, y, 1);
		
		List<Range> expected = new ArrayList<Range>();
		expected.add(RangeUtils.createRange(x, 0, root, 1));
		expected.add(RangeUtils.createRange(a, 0, a, 0));
		expected.add(RangeUtils.createRange(b, 0, y, 1));
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}

	@Test
	public void splitStartAndEndContainersPartiallySelected()
	{
		Element root = getDocument().createElement("root");
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		root.appendChild(a);
		root.appendChild(b);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(a, 0, b, 0);
		
		List<Range> expected = new ArrayList<Range>();
		expected.add(RangeUtils.createRange(a, 0, a, 0));
		expected.add(RangeUtils.createRange(root, 1, root, 1));
		expected.add(RangeUtils.createRange(b, 0, b, 0));
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}

	@Test
	public void splitStartAndEndContainersPartiallySelectedWithSiblings()
	{
		Element root = getDocument().createElement("root");
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		Element c = getDocument().createElement("c");
		root.appendChild(a);
		root.appendChild(b);
		root.appendChild(c);
		getDocument().appendChild(root);
		
		Range range = RangeUtils.createRange(a, 0, c, 0);
		
		List<Range> expected = new ArrayList<Range>();
		expected.add(RangeUtils.createRange(a, 0, a, 0));
		expected.add(RangeUtils.createRange(root, 1, root, 2));
		expected.add(RangeUtils.createRange(c, 0, c, 0));
		
		RangeAssert.assertRanges(expected, RangeUtils.split(range));
		RangeAssert.assertSurroundable(expected);
	}
	
	// snap tests -------------------------------------------------------------
	
	@Test
	public void snapExact()
	{
		Element a = getDocument().createElement("a");
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(getDocument(), 0, getDocument(), 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, getDocument(), 1, range);
	}
	
	@Test
	public void snapExactSelf()
	{
		Element a = getDocument().createElement("a");
		Element a2 = getDocument().createElement("a");
		a.appendChild(a2);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(a, 0, a, 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(a, 0, a, 1, range);
	}
	
	@Test
	public void snapStartFirstChild()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(a, 0, getDocument(), 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, getDocument(), 1, range);
	}
	
	@Test
	public void snapStartFirstChildText()
	{
		Element a = getDocument().createElement("a");
		Text x = getDocument().createTextNode("xxx");
		a.appendChild(x);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(x, 0, getDocument(), 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, getDocument(), 1, range);
	}
	
	@Test
	public void snapStartMiddleChild()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		Element c = getDocument().createElement("c");
		a.appendChild(b);
		a.appendChild(c);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(a, 1, getDocument(), 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(a, 1, getDocument(), 1, range);
	}
	
	@Test
	public void snapStartLastChild()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(a, 1, getDocument(), 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(a, 1, getDocument(), 1, range);
	}
	
	@Test
	public void snapStartLastChildCousin()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		Element d = getDocument().createElement("d");
		b.appendChild(d);
		a.appendChild(b);
		Element c = getDocument().createElement("c");
		a.appendChild(c);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(b, 1, getDocument(), 1);
		NodeFilter filter = NodeFilters.element(null, "c");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(a, 1, getDocument(), 1, range);
	}
	
	@Test
	public void snapStartLastChildCousinText()
	{
		Element a = getDocument().createElement("a");
		Text c = getDocument().createTextNode("c");
		a.appendChild(c);
		Element b = getDocument().createElement("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(c, 1, getDocument(), 1);
		NodeFilter filter = NodeFilters.element(null, "b");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(a, 1, getDocument(), 1, range);
	}
	
	@Test
	public void snapStartFirstGrandChild()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		Element c = getDocument().createElement("c");
		b.appendChild(c);
		a.appendChild(b);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(b, 0, getDocument(), 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, getDocument(), 1, range);
	}
	
	@Test
	public void snapEndLastChild()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(getDocument(), 0, a, 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, getDocument(), 1, range);
	}
	
	@Test
	public void snapEndLastChildText()
	{
		Element a = getDocument().createElement("a");
		Text x = getDocument().createTextNode("xxx");
		a.appendChild(x);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(getDocument(), 0, x, 3);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, getDocument(), 1, range);
	}
	
	@Test
	public void snapEndMiddleChild()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		Element c = getDocument().createElement("c");
		a.appendChild(b);
		a.appendChild(c);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(getDocument(), 0, a, 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, a, 1, range);
	}
	
	@Test
	public void snapEndFirstChild()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		a.appendChild(b);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(getDocument(), 0, a, 0);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, a, 0, range);
	}
	
	@Test
	public void snapEndFirstChildCousin()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		a.appendChild(b);
		Element c = getDocument().createElement("c");
		Element d = getDocument().createElement("d");
		c.appendChild(d);
		a.appendChild(c);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(getDocument(), 0, c, 0);
		NodeFilter filter = NodeFilters.element(null, "b");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, a, 1, range);
	}
	
	@Test
	public void snapEndLastGrandChild()
	{
		Element a = getDocument().createElement("a");
		Element b = getDocument().createElement("b");
		Element c = getDocument().createElement("c");
		b.appendChild(c);
		a.appendChild(b);
		getDocument().appendChild(a);
		
		Range range = RangeUtils.createRange(getDocument(), 0, b, 1);
		NodeFilter filter = NodeFilters.element(null, "a");
		
		RangeUtils.snap(range, filter);
		
		RangeAssert.assertRange(getDocument(), 0, getDocument(), 1, range);
	}
}
