/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/RangeUtils.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position.Bias;

import org.hobsoft.symmetry.support.xml.dom.filter.RangeNodeFilter;
import org.hobsoft.symmetry.support.xml.dom.visitor.PrintNodeVisitor;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

/**
 * Provides various utility methods for working with the DOM Range API.
 * 
 * @author Mark Hobson
 * @see <a href="http://www.w3.org/TR/2000/REC-DOM-Level-2-Traversal-Range-20001113/">Document Object Model (DOM) Level
 *      2 Traversal and Range Specification</a>
 */
public final class RangeUtils
{
	// constants --------------------------------------------------------------
	
	/**
	 * The name of the DOM Range feature.
	 */
	public static final String RANGE_FEATURE = "Range";
	
	/**
	 * The version of the DOM Level 2 Range feature.
	 */
	public static final String RANGE_VERSION_2_0 = "2.0";
	
	// constructors -----------------------------------------------------------
	
	private RangeUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Gets whether the specified node's DOM implementation supports Range 2.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @return <code>true</code> if the node's DOM implementation supports Range 2.0
	 */
	public static boolean hasRange2(Node node)
	{
		return NodeUtils.hasFeature(node, RANGE_FEATURE, RANGE_VERSION_2_0);
	}
	
	/**
	 * Ensures that the specified node's DOM implementation supports Range 2.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Range 2.0
	 */
	public static void ensureRange2(Node node)
	{
		NodeUtils.ensureFeature(node, RANGE_FEATURE, RANGE_VERSION_2_0);
	}
	
	/**
	 * Creates a range collapsed at the specified boundary-point from the container's owning document.
	 * <p>
	 * <strong>WARNING:</strong> The caller is responsible for detaching the range that this method returns.
	 * </p>
	 * 
	 * @param container
	 *            the boundary-point container
	 * @param offset
	 *            the boundary-point offset
	 * @return the range
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Range 2.0
	 */
	public static Range createRange(Node container, int offset)
	{
		return createRange(container, offset, container, offset);
	}
	
	/**
	 * Creates a range with the specified start and end boundary-points from the container's owning document.
	 * <p>
	 * <strong>WARNING:</strong> The caller is responsible for detaching the range that this method returns.
	 * </p>
	 * 
	 * @param container
	 *            the start and end boundary-point container
	 * @param startOffset
	 *            the start boundary-point offset
	 * @param endOffset
	 *            the end boundary-point offset
	 * @return the range
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Range 2.0
	 */
	public static Range createRange(Node container, int startOffset, int endOffset)
	{
		return createRange(container, startOffset, container, endOffset);
	}
	
	/**
	 * Creates a range with the specified start and end boundary-points from the container's owning document.
	 * <p>
	 * <strong>WARNING:</strong> The caller is responsible for detaching the range that this method returns.
	 * </p>
	 * 
	 * @param startContainer
	 *            the start boundary-point container
	 * @param startOffset
	 *            the start boundary-point offset
	 * @param endContainer
	 *            the end boundary-point container
	 * @param endOffset
	 *            the end boundary-point offset
	 * @return the range
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Range 2.0
	 */
	public static Range createRange(Node startContainer, int startOffset, Node endContainer, int endOffset)
	{
		Range range = createRange(startContainer);
		
		range.setStart(startContainer, startOffset);
		range.setEnd(endContainer, endOffset);
		
		return range;
	}
	
	/**
	 * Creates an empty range from the specified node's owning document.
	 * <p>
	 * <strong>WARNING:</strong> The caller is responsible for detaching the range that this method returns.
	 * </p>
	 * 
	 * @param node
	 *            the node whose owning document should be used to create the range
	 * @return the range
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Range 2.0
	 */
	public static Range createRange(Node node)
	{
		ensureRange2(node);
		
		Document document = NodeUtils.getDocument(node);
		DocumentRange documentRange = (DocumentRange) document;
		return documentRange.createRange();
	}
	
	/**
	 * Gets whether the specified ranges are equal.
	 * 
	 * @param range1
	 *            the first range to test
	 * @param range2
	 *            the second range to test
	 * @return <code>true</code> if the specified ranges are equal
	 */
	public static boolean isEqual(Range range1, Range range2)
	{
		return range1.compareBoundaryPoints(Range.START_TO_START, range2) == 0
			&& range1.compareBoundaryPoints(Range.END_TO_END, range2) == 0;
	}
	
	/**
	 * Gets whether the specified range has the given start boundary-point.
	 * 
	 * @param range
	 *            the range to test
	 * @param container
	 *            the start boundary-point container
	 * @param offset
	 *            the start boundary-point offset
	 * @return <code>true</code> if the range has the specified start boundary-point
	 */
	public static boolean hasStart(Range range, Node container, int offset)
	{
		return isEqual(range.getStartContainer(), range.getStartOffset(), container, offset);
	}
	
	/**
	 * Gets whether the specified range has the given end boundary-point.
	 * 
	 * @param range
	 *            the range to test
	 * @param container
	 *            the end boundary-point container
	 * @param offset
	 *            the end boundary-point offset
	 * @return <code>true</code> if the range has the specified end boundary-point
	 */
	public static boolean hasEnd(Range range, Node container, int offset)
	{
		return isEqual(range.getEndContainer(), range.getEndOffset(), container, offset);
	}
	
	/**
	 * Gets whether the specified boundary-points are equal.
	 * 
	 * @param container1
	 *            the first boundary-point container
	 * @param offset1
	 *            the first boundary-point offset
	 * @param container2
	 *            the second boundary-point container
	 * @param offset2
	 *            the second boundary-point offset
	 * @return <code>true</code> if the specified boundary-points are equal
	 */
	public static boolean isEqual(Node container1, int offset1, Node container2, int offset2)
	{
		return (container1 == container2 && offset1 == offset2);
	}
	
	/**
	 * Gets the last valid range boundary-point offset for the specified range boundary-point container.
	 * 
	 * @param container
	 *            the range boundary-point container
	 * @return the last valid range boundary-point offset
	 */
	public static int getMaxOffset(Node container)
	{
		if (isTextContainer(container))
		{
			return (container instanceof CharacterData)
				? ((CharacterData) container).getLength()
				: ((ProcessingInstruction) container).getData().length();
		}
		
		return container.getChildNodes().getLength();
	}
	
	/**
	 * Gets whether the specified node is a boundary-point container whose offset is between the 16-bit units of the
	 * UTF-16 encoded string contained by it.
	 * 
	 * @param node
	 *            the node whose type to test
	 * @return <code>true</code> if the node is a boundary-point container whose offset is between the 16-bit units of
	 *         the UTF-16 encoded string contained by it
	 * @see #isTextContainer(short)
	 */
	public static boolean isTextContainer(Node node)
	{
		return isTextContainer(node.getNodeType());
	}
	
	/**
	 * Gets whether the specified node type is a boundary-point container whose offset is between the 16-bit units of
	 * the UTF-16 encoded string contained by it.
	 * <p>
	 * According to the DOM 2 Range Specification, the following node types satisfy this condition:
	 * </p>
	 * <ul>
	 * <li><code>CharacterData</code></li>
	 * <li><code>Comment</code></li>
	 * <li><code>ProcessingInstruction</code></li>
	 * </ul>
	 * 
	 * @param type
	 *            the node type to test
	 * @return <code>true</code> if the node type is a boundary-point container whose offset is between the 16-bit units
	 *         of the UTF-16 encoded string contained by it
	 * @throws IllegalArgumentException
	 *             if the specified node type is invalid
	 */
	public static boolean isTextContainer(short type)
	{
		NodeUtils.validateNodeType(type);
		
		return type == Node.TEXT_NODE
			|| type == Node.CDATA_SECTION_NODE
			|| type == Node.PROCESSING_INSTRUCTION_NODE
			|| type == Node.COMMENT_NODE;
	}
	
	public static Node getStartNode(Range range)
	{
		return getBoundaryPointNode(range.getStartContainer(), range.getStartOffset(), true);
	}
	
	public static Node getEndNode(Range range)
	{
		return getBoundaryPointNode(range.getEndContainer(), range.getEndOffset(), false);
	}
	
	public static Node getBoundaryPointNode(Node container, int offset, boolean forwards)
	{
		if (isTextContainer(container))
		{
			return container;
		}
		
		if (!forwards)
		{
			offset--;
		}
		
		return container.getChildNodes().item(offset);
	}
	
	/**
	 * Gets a list of nodes that are partially selected by the specified range.
	 * 
	 * @param range
	 *            the range to obtain the partially selected nodes of
	 * @return a list of nodes that are partially selected by the range
	 */
	public static List<Node> getPartiallySelectedNodes(Range range)
	{
		return getPartiallySelectedNodes(range, null);
	}
	
	/**
	 * Gets a list of nodes that are partially selected by the specified range and accepted by the given node filter.
	 * 
	 * @param range
	 *            the range to obtain the partially selected nodes of
	 * @param filter
	 *            the node filter to use
	 * @return a list of nodes that are partially selected by the range and accepted by the node filter
	 */
	public static List<Node> getPartiallySelectedNodes(Range range, NodeFilter filter)
	{
		List<Node> startAncestors = TraversalUtils.getAncestorsAndSelf(range.getStartContainer(), filter);
		List<Node> endAncestors = TraversalUtils.getAncestorsAndSelf(range.getEndContainer(), filter);
		
		// locate ancestor index where containers deviate
		int start = getChangeIndex(startAncestors, endAncestors);
		
		List<Node> nodes = new ArrayList<Node>();

		// start ancestor containers below this index are partially selected
		for (int i = start; i < startAncestors.size(); i++)
		{
			nodes.add(startAncestors.get(i));
		}

		// end ancestor containers below this index are partially selected
		for (int i = start; i < endAncestors.size(); i++)
		{
			nodes.add(endAncestors.get(i));
		}
		
		return nodes;
	}
	
	/**
	 * Gets a list of non-text nodes that are partially selected by the specified range.
	 * 
	 * @param range
	 *            the range to use
	 * @return a list of non-text nodes that are partially selected by the range
	 */
	public static List<Node> getUnsurroundableNodes(Range range)
	{
		return getPartiallySelectedNodes(range, NodeFilters.not(NodeFilters.text()));
	}
	
	/**
	 * Gets whether the specified range has no partially selected non-text nodes.
	 * 
	 * @param range
	 *            the range to test
	 * @return <code>true</code> if the range has no partially selected non-text nodes
	 */
	public static boolean isSurroundable(Range range)
	{
		return getUnsurroundableNodes(range).isEmpty();
	}
	
	/**
	 * Sets the start and end boundary-points of the specified range to the beginning and end of their containing word's
	 * respectively.
	 * 
	 * @param range
	 *            the range whose boundary-points to set
	 * @param root
	 *            the root node of the subtree to constrain the selection to
	 */
	public static void selectWord(Range range, Node root)
	{
		setStartBeforeWord(range, root);
		setEndAfterWord(range, root);
	}
	
	/**
	 * Sets the start boundary-point of the specified range to the beginning of its containing word.
	 * 
	 * @param range
	 *            the range whose start boundary-point to set
	 * @param root
	 *            the root node of the subtree to constrain the selection to
	 */
	public static void setStartBeforeWord(Range range, Node root)
	{
		DOMCharacterIterator characterIterator = new DOMCharacterIterator(root, null);
		
		DOMCharacterIteratorUtils.wordStart(characterIterator, range.getStartContainer(), range.getStartOffset());
		
		range.setStart(characterIterator.getNode(), characterIterator.getOffset());
	}
	
	/**
	 * Sets the end boundary-point of the specified range to the end of its containing word.
	 * 
	 * @param range
	 *            the range whose end boundary-point to set
	 * @param root
	 *            the root node of the subtree to constrain the selection to
	 */
	public static void setEndAfterWord(Range range, Node root)
	{
		DOMCharacterIterator characterIterator = new DOMCharacterIterator(root, null);

		DOMCharacterIteratorUtils.wordEnd(characterIterator, range.getEndContainer(), range.getEndOffset());
		
		range.setEnd(characterIterator.getNode(Bias.Backward), characterIterator.getOffset(Bias.Backward));
	}
	
	/**
	 * Fractures the DOM tree before the specified node up to and including the given root node.
	 * 
	 * @param node
	 *            the node to start the fracture before
	 * @param root
	 *            the node at which to stop the fracture at, which must be an ancestor-or-self of the first node
	 * @throws IllegalArgumentException
	 *             if the root node is not an ancestor-or-self of the first node
	 */
	public static void fractureBefore(Node node, Node root)
	{
		Node container = node.getParentNode();
		int offset = NodeUtils.indexOf(node);
		
		fracture(container, offset, root);
	}
	
	/**
	 * Fractures the DOM tree after the specified node up to and including the given root node.
	 * 
	 * @param node
	 *            the node to start the fracture after
	 * @param root
	 *            the node at which to stop the fracture at, which must be an ancestor-or-self of the first node
	 * @throws IllegalArgumentException
	 *             if the root node is not an ancestor-or-self of the first node
	 */
	public static void fractureAfter(Node node, Node root)
	{
		Node container = node.getParentNode();
		int offset = NodeUtils.indexOf(node) + 1;
		
		fracture(container, offset, root);
	}
	
	/**
	 * Fractures the DOM tree at the specified boundary-point up to and including the given node.
	 * 
	 * @param container
	 *            the boundary-point container to start the fracture from
	 * @param offset
	 *            the boundary-point offset to start the fracture from
	 * @param root
	 *            the node at which to stop the fracture at, which must be an ancestor-or-self of the range's root
	 *            container
	 * @throws IllegalArgumentException
	 *             if the root node is not an ancestor-or-self of the boundary-point's container
	 */
	public static void fracture(Node container, int offset, Node root)
	{
		// validate root node
		if (!NodeUtils.isAncestorOrSelf(root, container))
		{
			throw new IllegalArgumentException("Root node must be an ancestor-or-self of the container");
		}
		
		// get root node start boundary-point
		Node rootContainer = root.getParentNode();
		int rootOffset = NodeUtils.indexOf(root);

		if (rootContainer == null)
		{
			throw new IllegalArgumentException("Root node must have a parent");
		}
		
		// previous code to fracture before root:
//		Range range = RangeUtils.createRange(rootContainer, rootOffset, container, offset);
//		rootContainer.insertBefore(range.extractContents(), root);

		// extract contents from root node to boundary-point before root node
		Range range = RangeUtils.createRange(container, offset, rootContainer, rootOffset + 1);
		DocumentFragment fragment = range.extractContents();
		range.detach();

		// reinsert contents after root node
		rootContainer.insertBefore(fragment, root.getNextSibling());
	}
	
	/**
	 * Fractures the DOM tree at the specified range's boundary-points up to and including the given node.
	 * 
	 * @param range
	 *            the range that specifies the boundary-points to start the fracture from
	 * @param node
	 *            the node at which to stop the fracture at, which must be an ancestor-or-self of the range's root
	 *            container
	 * @throws IllegalArgumentException
	 *             if the node is not an ancestor-or-self of the range's root container
	 */
	public static void fracture(Range range, Node node)
	{
		fracture(range.getStartContainer(), range.getStartOffset(), node);
		
		fracture(range.getEndContainer(), range.getEndOffset(), node);
	}
	
	/**
	 * Splits the specified range into a minimal series of disjoint ranges that do not partially select any nodes.
	 * <p>
	 * <strong>WARNING:</strong> The caller is responsible for detaching the ranges that this method returns.
	 * </p>
	 * 
	 * @param range
	 *            the range to split
	 * @return a list of ranges that do not partially select any nodes
	 */
	public static List<Range> split(Range range)
	{
		return split(range, new ArrayList<Range>());
	}
	
	public static void snap(Range range, NodeFilter filter)
	{
		// TODO: be nice to generalise this better
		
		Node startContainer = range.getStartContainer();
		int startOffset = range.getStartOffset();
		Node endContainer = range.getEndContainer();
		int endOffset = range.getEndOffset();
		
		int startMaxOffset = getMaxOffset(startContainer);
		int endMaxOffset = getMaxOffset(endContainer);
		
		// only snap if both boundary-points are at edges
		if (!((startOffset == 0 || startOffset == startMaxOffset) && (endOffset == 0 || endOffset == endMaxOffset)))
		{
			return;
		}
		
		if (filter.acceptNode(getStartNode(range)) != NodeFilter.FILTER_ACCEPT)
		{
			Node node = null;
			
			if (startOffset == 0)
			{
				node = widen(startContainer, filter, false);
			}
			else if (startOffset == startMaxOffset)
			{
				node = jump(startContainer, filter, true);
			}
			
			if (node != null)
			{
				range.setStartBefore(node);
			}
		}
		
		if (filter.acceptNode(getEndNode(range)) != NodeFilter.FILTER_ACCEPT)
		{
			Node node = null;
			
			if (endOffset == 0)
			{
				node = jump(endContainer, filter, false);
			}
			else if (endOffset == endMaxOffset)
			{
				node = widen(endContainer, filter, true);
			}

			if (node != null)
			{
				range.setEndAfter(node);
			}
		}
	}
	
	/**
	 * Creates a tree walker over the subtree selected by the specified range.
	 * 
	 * All nodes are visited and entity references are not expanded.
	 * 
	 * @param range
	 *            the range to walk over
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Range range)
	{
		return getTreeWalker(range, null);
	}
	
	/**
	 * Creates a tree walker over the subtree selected by the specified range.
	 * 
	 * No node filtering is applied and entity references are not expanded.
	 * 
	 * @param range
	 *            the range to walk over
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Range range, int whatToShow)
	{
		return getTreeWalker(range, whatToShow, null);
	}
	
	/**
	 * Creates a tree walker over the subtree selected by the specified range.
	 * 
	 * All unfiltered nodes are visited and entity references are not expanded.
	 * 
	 * @param range
	 *            the range to walk over
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Range range, NodeFilter filter)
	{
		return getTreeWalker(range, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Creates a tree walker over the subtree selected by the specified range.
	 * 
	 * Entity references are not expanded.
	 * 
	 * @param range
	 *            the range to walk over
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Range range, int whatToShow, NodeFilter filter)
	{
		return getTreeWalker(range, whatToShow, filter, false);
	}
	
	/**
	 * Creates a tree walker over the subtree selected by the specified range.
	 * 
	 * @param range
	 *            the range to walk over
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @param entityReferenceExpansion
	 *            whether to visit <code>EntityReference</code> nodes
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Range range, int whatToShow, NodeFilter filter,
		boolean entityReferenceExpansion)
	{
		Node root = range.getCommonAncestorContainer();
		
		NodeFilter rangeFilter = new RangeNodeFilter(range);
		NodeFilter compoundFilter = (filter != null) ? NodeFilters.and(rangeFilter, filter) : rangeFilter;
		
		return TraversalUtils.getTreeWalker(root, whatToShow, compoundFilter, entityReferenceExpansion);
	}
	
	/**
	 * Pretty prints the nodes selected by the specified range to the standard output stream.
	 * 
	 * @param range
	 *            the range whose selection to print
	 */
	public static void print(Range range)
	{
		print(System.out, range);
	}
	
	/**
	 * Pretty prints the nodes selected by the specified range to the given output stream.
	 * 
	 * @param out
	 *            the output stream to print to
	 * @param range
	 *            the range whose selection to print
	 */
	public static void print(OutputStream out, Range range)
	{
		accept(range, new PrintNodeVisitor(out));
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the nodes selected by the given range.
	 * 
	 * All nodes are visited and entity references are not expanded.
	 * 
	 * @param range
	 *            the range whose selection to visit
	 * @param visitor
	 *            the node visitor to apply
	 * @return <code>true</code> if the range's common ancestor node's siblings should be visited
	 */
	public static boolean accept(Range range, NodeVisitor visitor)
	{
		return accept(range, null, visitor);
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the nodes selected by the given range.
	 * 
	 * No node filtering is applied and entity references are not expanded.
	 * 
	 * @param range
	 *            the range whose selection to visit
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param visitor
	 *            the node visitor to apply
	 * @return <code>true</code> if the range's common ancestor node's siblings should be visited
	 */
	public static boolean accept(Range range, int whatToShow, NodeVisitor visitor)
	{
		return accept(range, whatToShow, null, visitor);
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the nodes selected by the given range.
	 * 
	 * All unfiltered nodes are visited and entity references are not expanded.
	 * 
	 * @param range
	 *            the range whose selection to visit
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @param visitor
	 *            the node visitor to apply
	 * @return <code>true</code> if the range's common ancestor node's siblings should be visited
	 */
	public static boolean accept(Range range, NodeFilter filter, NodeVisitor visitor)
	{
		return accept(range, NodeFilter.SHOW_ALL, filter, visitor);
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the nodes selected by the given range.
	 * 
	 * Entity references are not expanded.
	 * 
	 * @param range
	 *            the range whose selection to visit
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @param visitor
	 *            the node visitor to apply
	 * @return <code>true</code> if the range's common ancestor node's siblings should be visited
	 */
	public static boolean accept(Range range, int whatToShow, NodeFilter filter, NodeVisitor visitor)
	{
		return accept(range, whatToShow, filter, false, visitor);
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the nodes selected by the given range.
	 * 
	 * @param range
	 *            the range whose selection to visit
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @param entityReferenceExpansion
	 *            whether to visit <code>EntityReference</code> nodes
	 * @param visitor
	 *            the node visitor to apply
	 * @return <code>true</code> if the range's common ancestor node's siblings should be visited
	 */
	public static boolean accept(Range range, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion,
		NodeVisitor visitor)
	{
		TreeWalker walker = getTreeWalker(range, whatToShow, filter, entityReferenceExpansion);
		
		return TraversalUtils.accept(walker, visitor);
	}
	
	// private methods --------------------------------------------------------
	
	/**
	 * Splits the specified range into a minimal series of ranges that do not partially select any nodes and appends
	 * them to the given range list.
	 * <p>
	 * This method is used internally by <code>split(Range)</code>.
	 * </p>
	 * <p>
	 * <strong>WARNING:</strong> The caller is responsible for detaching the ranges that this method returns.
	 * </p>
	 * 
	 * @param range
	 *            the range to split
	 * @param ranges
	 *            the list of ranges to append to
	 * @return the list of ranges appended to
	 * @see #split(Range)
	 */
	private static List<Range> split(Range range, List<Range> ranges)
	{
		// get non-text ancestor containers for start and end boundary-points
		NodeFilter filter = NodeFilters.not(NodeFilters.text());
		List<Node> startAncestors = TraversalUtils.getAncestorsAndSelf(range.getStartContainer(), filter);
		List<Node> endAncestors = TraversalUtils.getAncestorsAndSelf(range.getEndContainer(), filter);
		
		// locate ancestor index where ancestor containers deviate
		int index = getChangeIndex(startAncestors, endAncestors);

		// get number of start and end ancestor containers
		int startCount = startAncestors.size();
		int endCount = endAncestors.size();
		
		// get range boundary-points
		Node startContainer = range.getStartContainer();
		int startOffset = range.getStartOffset();
		Node endContainer = range.getEndContainer();
		int endOffset = range.getEndOffset();

		if (index == startCount && index == endCount)
		{
			// no partially selected nodes, range is split
			
			addRange(ranges, range);
		}
		else if (index == startCount)
		{
			// end partially selects node, split at start boundary-point
			
			Node splitEndContainer = endAncestors.get(index);
			
			Node splitStartNode = splitEndContainer.getParentNode();
			int splitStartOffset = NodeUtils.indexOf(splitEndContainer);
			
			addRange(ranges, createRange(startContainer, startOffset, splitStartNode, splitStartOffset));
			split(createRange(splitEndContainer, 0, endContainer, endOffset), ranges);
		}
		else if (index == endCount)
		{
			// start partially selects node, split at end boundary-point
			
			Node splitStartContainer = startAncestors.get(index);
			int splitStartOffset = getMaxOffset(splitStartContainer);
			
			Node splitEndContainer = splitStartContainer.getParentNode();
			int splitEndOffset = NodeUtils.indexOf(splitStartContainer) + 1;
			
			split(createRange(startContainer, startOffset, splitStartContainer, splitStartOffset), ranges);
			addRange(ranges, createRange(splitEndContainer, splitEndOffset, endContainer, endOffset));
		}
		else
		{
			// both start and end partially select nodes, split at common ancestor
			
			Node startAncestor = startAncestors.get(index);
			Node endAncestor = endAncestors.get(index);
			
			Node splitStartContainer = endAncestor.getParentNode();
			int splitStartOffset = NodeUtils.indexOf(endAncestor);
			
			Node splitEndContainer = startAncestor.getParentNode();
			int splitEndOffset = NodeUtils.indexOf(startAncestor) + 1;
			
			split(createRange(startContainer, startOffset, splitStartContainer, splitStartOffset), ranges);
			split(createRange(splitEndContainer, splitEndOffset, endContainer, endOffset), ranges);
		}
		
		return ranges;
	}
	
	/**
	 * Gets the first index within the specified lists where their elements are unequal.
	 * <p>
	 * This method is used internally by <code>getPartiallySelectedNodes(Range, NodeFilter)</code> and
	 * <code>split(Range, List<Range>)</code>.
	 * </p>
	 * 
	 * @param list1
	 *            the first list to inspect
	 * @param list2
	 *            the second list to inspect
	 * @return the first index where elements are unequal, or <code>0</code> if no unequal elements where found before
	 *         the end of the shortest list
	 * @see #getPartiallySelectedNodes(Range, NodeFilter)
	 * @see #split(Range, List)
	 */
	private static int getChangeIndex(List<?> list1, List<?> list2)
	{
		int index = 0;
		
		int size1 = list1.size();
		int size2 = list2.size();
		
		while (index < size1 && index < size2 && list1.get(index) == list2.get(index))
		{
			index++;
		}
		
		return index;
	}
	
	/**
	 * Appends the specified range to the end of the given range list if it is not equal to the last range in the list.
	 * <p>
	 * This method is used internally by <code>split(Range, List<Range>)</code> to filter out duplicate ranges.
	 * </p>
	 * 
	 * @param ranges
	 *            the list of ranges to append to
	 * @param range
	 *            the range to append
	 * @see #split(Range, List)
	 */
	private static void addRange(List<Range> ranges, Range range)
	{
		if (!ranges.isEmpty())
		{
			// obtain last range in list
			Range lastRange = ranges.get(ranges.size() - 1);
			
			// no-op if last range is equal to specified range
			if (isEqual(lastRange, range))
			{
				return;
			}
		}
		
		// append range to list
		ranges.add(range);
	}

	private static Node widen(Node node, NodeFilter filter, boolean forwards)
	{
		while (node != null)
		{
			if (filter.acceptNode(node) == NodeFilter.FILTER_ACCEPT)
			{
				return node;
			}
			
			if (getSibling(node, forwards) == null)
			{
				node = node.getParentNode();
			}
			else
			{
				node = null;
			}
		}
		
		return null;
	}
	
	private static Node jump(Node node, NodeFilter filter, boolean forwards)
	{
		while (getSibling(node, forwards) == null && node.getParentNode() != null)
		{
			node = node.getParentNode();
		}
		
		node = getSibling(node, forwards);
		
		if (node != null)
		{
			while (node != null)
			{
				if (filter.acceptNode(node) == NodeFilter.FILTER_ACCEPT)
				{
					return node;
				}
				
				node = forwards ? node.getFirstChild() : node.getLastChild();
			}
		}
		
		return null;
	}
	
	private static Node getSibling(Node node, boolean next)
	{
		return next ? node.getNextSibling() : node.getPreviousSibling();
	}
}
