/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/TraversalUtils.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import uk.co.iizuka.common.xml.dom.visitor.PrintNodeVisitor;

/**
 * Provides various utility methods for working with the DOM Traversal API.
 * 
 * @author Mark Hobson
 * @version $Id: TraversalUtils.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see <a href="http://www.w3.org/TR/2000/REC-DOM-Level-2-Traversal-Range-20001113/">Document Object Model (DOM) Level
 *      2 Traversal and Range Specification</a>
 */
public final class TraversalUtils
{
	// TODO: Revisit the getXXXOrSelf() methods - the self-check needs to support FILTER_REJECT by short-circuiting the
	// method.
	
	// constants --------------------------------------------------------------
	
	/**
	 * The name of the DOM Traversal feature.
	 */
	public static final String TRAVERSAL_FEATURE = "Traversal";
	
	/**
	 * The version of the DOM Level 2 Traversal feature.
	 */
	public static final String TRAVERSAL_VERSION_2_0 = "2.0";
	
	/**
	 * A string representation of the <code>NodeFilter.SHOW_ALL</code> node type mask.
	 * 
	 * @see NodeFilter#SHOW_ALL
	 */
	private static final String SHOW_ALL_NAME = "SHOW_ALL";
	
	/**
	 * An array of node type mask names in the same order as the <code>NodeFilter.SHOW_*</code> constants.
	 * 
	 * @see NodeFilter
	 */
	private static final String[] SHOW_NAMES = new String[] {
		"SHOW_ELEMENT",
		"SHOW_ATTRIBUTE",
		"SHOW_TEXT",
		"SHOW_CDATA_SECTION",
		"SHOW_ENTITY_REFERENCE",
		"SHOW_ENTITY",
		"SHOW_PROCESSING_INSTRUCTION",
		"SHOW_COMMENT",
		"SHOW_DOCUMENT",
		"SHOW_DOCUMENT_TYPE",
		"SHOW_DOCUMENT_FRAGMENT",
		"SHOW_NOTATION",
	};
	
	// constructors -----------------------------------------------------------
	
	private TraversalUtils()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets whether the specified node's DOM implementation supports Traversal 2.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @return <code>true</code> if the node's DOM implementation supports Traversal 2.0
	 */
	public static boolean hasTraversal2(Node node)
	{
		return NodeUtils.hasFeature(node, TRAVERSAL_FEATURE, TRAVERSAL_VERSION_2_0);
	}
	
	/**
	 * Ensures that the specified node's DOM implementation supports Traversal 2.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Traversal 2.0
	 */
	public static void ensureTraversal2(Node node)
	{
		NodeUtils.ensureFeature(node, TRAVERSAL_FEATURE, TRAVERSAL_VERSION_2_0);
	}
	
	/**
	 * Gets whether the specified node's type is visible for the specified node type mask.
	 * 
	 * @param node
	 *            the node whose type to test
	 * @param whatToShow
	 *            which node types to show; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return <code>true</code> if the node's type is visible for the node type mask
	 */
	public static boolean isVisible(Node node, int whatToShow)
	{
		if (node == null)
		{
			return false;
		}

		return isVisible(node.getNodeType(), whatToShow);
	}
	
	/**
	 * Gets whether the specified node type is visible for the specified node type mask.
	 * 
	 * @param type
	 *            the node type to test
	 * @param whatToShow
	 *            which node types to show; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return <code>true</code> if the node type is visible for the node type mask
	 * @throws IllegalArgumentException
	 *             if the specified node type is invalid
	 */
	public static boolean isVisible(short type, int whatToShow)
	{
		return ((whatToShow & getWhatToShow(type)) > 0);
	}
	
	/**
	 * Gets the node type mask that only shows the specified node's type.
	 * 
	 * @param node
	 *            the node whose type to show
	 * @return the node type mask; produced by bitwise oring the <code>NodeFilter.SHOW_*</code> constants together
	 */
	public static int getWhatToShow(Node node)
	{
		return getWhatToShow(node.getNodeType());
	}
	
	/**
	 * Gets the node type mask that only shows the specified node type.
	 * 
	 * @param type
	 *            the node type to show
	 * @return the node type mask; produced by bitwise oring the <code>NodeFilter.SHOW_*</code> constants together
	 * @throws IllegalArgumentException
	 *             if the specified node type is invalid
	 */
	public static int getWhatToShow(short type)
	{
		NodeUtils.validateNodeType(type);
		
		return (1 << (type - 1));
	}
	
	/**
	 * Gets a string representation of the specified node type mask.
	 * <p>
	 * The returned string is produced by concatenating the string representations of the
	 * <code>NodeFilter.SHOW_*</code> constants that are set in the node type mask using the "<code>|</code>" separator,
	 * unless the node type mask is <code>NodeFilter.SHOW_ALL</code> then "<code>SHOW_ALL</code>" is returned instead.
	 * </p>
	 * <p>
	 * For example, the node type mask value <code>0x5</code> would be returned as "<code>SHOW_ELEMENT|SHOW_TEXT</code>
	 * ".
	 * </p>
	 * 
	 * @param whatToShow
	 *            which node types to show; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return a string representation of the node type mask, as described above
	 */
	public static String getWhatToShowName(int whatToShow)
	{
		if (whatToShow == NodeFilter.SHOW_ALL)
		{
			return SHOW_ALL_NAME;
		}
		
		StringBuilder builder = new StringBuilder();
		
		for (short type : NodeUtils.getNodeTypes())
		{
			if (isVisible(type, whatToShow))
			{
				if (builder.length() > 0)
				{
					builder.append('|');
				}
				
				builder.append(SHOW_NAMES[type - 1]);
			}
		}
		
		return builder.toString();
	}
	
	// getDocumentTreeWalker() ------------------------------------------------
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node's document. The tree walker's current
	 * node is set to the specified node.
	 * 
	 * All nodes are visited and entity references are not expanded.
	 * 
	 * @param node
	 *            the tree walker current node
	 * @return the tree walker
	 */
	public static TreeWalker getDocumentTreeWalker(Node node)
	{
		return getDocumentTreeWalker(node, NodeFilter.SHOW_ALL);
	}
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node's document. The tree walker's current
	 * node is set to the specified node.
	 * 
	 * No node filtering is applied and entity references are not expanded.
	 * 
	 * @param node
	 *            the tree walker current node
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return the tree walker
	 */
	public static TreeWalker getDocumentTreeWalker(Node node, int whatToShow)
	{
		return getDocumentTreeWalker(node, whatToShow, null);
	}
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node's document. The tree walker's current
	 * node is set to the specified node.
	 * 
	 * All unfiltered nodes are visited and entity references are not expanded.
	 * 
	 * @param node
	 *            the tree walker current node
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @return the tree walker
	 */
	public static TreeWalker getDocumentTreeWalker(Node node, NodeFilter filter)
	{
		return getDocumentTreeWalker(node, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node's document. The tree walker's current
	 * node is set to the specified node.
	 * 
	 * Entity references are not expanded.
	 * 
	 * @param node
	 *            the tree walker current node
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @return the tree walker
	 */
	public static TreeWalker getDocumentTreeWalker(Node node, int whatToShow, NodeFilter filter)
	{
		return getDocumentTreeWalker(node, whatToShow, filter, false);
	}
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node's document. The tree walker's current
	 * node is set to the specified node.
	 * 
	 * @param node
	 *            the tree walker current node
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @param entityReferenceExpansion
	 *            whether to visit <code>EntityReference</code> nodes
	 * @return the tree walker
	 */
	public static TreeWalker getDocumentTreeWalker(Node node, int whatToShow, NodeFilter filter,
		boolean entityReferenceExpansion)
	{
		Document document = NodeUtils.getDocument(node);
		TreeWalker walker = getTreeWalker(document, whatToShow, filter, entityReferenceExpansion);
		walker.setCurrentNode(node);
		return walker;
	}
	
	// getTreeWalker() --------------------------------------------------------
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node.
	 * 
	 * All nodes are visited and entity references are not expanded.
	 * 
	 * @param root
	 *            the tree root node
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Node root)
	{
		return getTreeWalker(root, null);
	}
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node.
	 * 
	 * No node filtering is applied and entity references are not expanded.
	 * 
	 * @param root
	 *            the tree root node
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Node root, int whatToShow)
	{
		return getTreeWalker(root, whatToShow, null);
	}
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node.
	 * 
	 * All unfiltered nodes are visited and entity references are not expanded.
	 * 
	 * @param root
	 *            the tree root node
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Node root, NodeFilter filter)
	{
		return getTreeWalker(root, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node.
	 * 
	 * Entity references are not expanded.
	 * 
	 * @param root
	 *            the tree root node
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @return the tree walker
	 */
	public static TreeWalker getTreeWalker(Node root, int whatToShow, NodeFilter filter)
	{
		return getTreeWalker(root, whatToShow, filter, false);
	}
	
	/**
	 * Creates a new tree walker over the subtree rooted at the specified node.
	 * 
	 * @param root
	 *            the tree root node
	 * @param whatToShow
	 *            which node types to visit; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use, or <code>null</code> for none
	 * @param entityReferenceExpansion
	 *            whether to visit <code>EntityReference</code> nodes
	 * @return the tree walker
	 * @throws IllegalArgumentException
	 *             if the tree root node is <code>null</code>
	 */
	public static TreeWalker getTreeWalker(Node root, int whatToShow, NodeFilter filter,
		boolean entityReferenceExpansion)
	{
		AssertUtils.assertNotNull("root", root);
		
		ensureTraversal2(root);
		
		Document document = NodeUtils.getDocument(root);
		DocumentTraversal traversal = (DocumentTraversal) document;
		return traversal.createTreeWalker(root, whatToShow, filter, entityReferenceExpansion);
	}

	// getAncestor() ----------------------------------------------------------
	
	/**
	 * Gets the first ancestor of the specified node that is of the given type.
	 * 
	 * @param node
	 *            the node to obtain the ancestor of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return the ancestor node, or <code>null</code> if none found
	 */
	public static Node getAncestor(Node node, int whatToShow)
	{
		return getAncestor(node, whatToShow, null);
	}
	
	/**
	 * Gets the first ancestor of the specified node that is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to obtain the ancestor of
	 * @param filter
	 *            the filter to use
	 * @return the ancestor node, or <code>null</code> if none found
	 */
	public static Node getAncestor(Node node, NodeFilter filter)
	{
		return getAncestor(node, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Gets the first ancestor of the specified node that is of the given type and accepted by the given filter.
	 * 
	 * @param node
	 *            the node to obtain the ancestor of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use
	 * @return the ancestor node, or <code>null</code> if none found
	 */
	public static Node getAncestor(Node node, int whatToShow, NodeFilter filter)
	{
		return getAncestor(NodeUtils.getDocument(node), node, whatToShow, filter);
	}
	
	/**
	 * Gets the first ancestor of the specified node that is of the given type and accepted by the given filter in a
	 * given sub-tree.
	 * 
	 * @param root
	 *            the node that the sub-tree is rooted at
	 * @param node
	 *            the node to obtain the ancestor of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use
	 * @return the ancestor node, or <code>null</code> if none found
	 */
	public static Node getAncestor(Node root, Node node, int whatToShow, NodeFilter filter)
	{
		TreeWalker walker = getTreeWalker(root, whatToShow, filter);
		walker.setCurrentNode(node);
		return walker.parentNode();
	}
	
	// getAncestors() ---------------------------------------------------------
	
	/**
	 * Gets a list of ancestors of the specified node.
	 * 
	 * @param node
	 *            the node to obtain ancestors of
	 * @return the list of ancestors, in document order
	 */
	public static List<Node> getAncestors(Node node)
	{
		return getAncestors(node, null);
	}
	
	/**
	 * Gets a list of ancestors of the specified node that are of the given type.
	 * 
	 * @param node
	 *            the node to obtain ancestors of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return the list of ancestors, in document order
	 */
	public static List<Node> getAncestors(Node node, int whatToShow)
	{
		return getAncestors(node, whatToShow, null);
	}
	
	/**
	 * Gets a list of ancestors of the specified node that are accepted by the given filter.
	 * 
	 * @param node
	 *            the node to obtain ancestors of
	 * @param filter
	 *            the filter to use
	 * @return the list of ancestors, in document order
	 */
	public static List<Node> getAncestors(Node node, NodeFilter filter)
	{
		return getAncestors(node, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Gets a list of ancestors of the specified node that are of the given type and accepted by the given filter.
	 * 
	 * @param node
	 *            the node to obtain ancestors of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use
	 * @return the list of ancestors, in document order
	 */
	public static List<Node> getAncestors(Node node, int whatToShow, NodeFilter filter)
	{
		return getAncestors(getDocumentTreeWalker(node, whatToShow, filter));
	}
	
	/**
	 * Gets a list of ancestors of the specified tree walker's current node in the logical view.
	 * 
	 * @param walker
	 *            the tree walker to use
	 * @return the list of ancestors, in document order
	 */
	public static List<Node> getAncestors(TreeWalker walker)
	{
		List<Node> ancestors = new ArrayList<Node>();
		
		while (walker.parentNode() != null)
		{
			ancestors.add(0, walker.getCurrentNode());
		}
		
		return ancestors;
	}
	
	// getAncestorCount() -----------------------------------------------------
	
	/**
	 * Gets the number of ancestors of the specified node.
	 * 
	 * @param node
	 *            the node to count ancestors of
	 * @return the number of ancestors
	 */
	public static int getAncestorCount(Node node)
	{
		return getAncestorCount(node, null);
	}
	
	/**
	 * Gets the number of ancestors of the specified node that are of the given type.
	 * 
	 * @param node
	 *            the node to count ancestors of
	 * @param whatToShow
	 *            which node types to count; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return the number of ancestors
	 */
	public static int getAncestorCount(Node node, int whatToShow)
	{
		return getAncestorCount(node, whatToShow, null);
	}
	
	/**
	 * Gets the number of ancestors of the specified node that are accepted by the given filter.
	 * 
	 * @param node
	 *            the node to count ancestors of
	 * @param filter
	 *            the filter to use
	 * @return the number of ancestors
	 */
	public static int getAncestorCount(Node node, NodeFilter filter)
	{
		return getAncestorCount(node, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Gets the number of ancestors of the specified node that are of the given type and accepted by the given filter.
	 * 
	 * @param node
	 *            the node to count ancestors of
	 * @param whatToShow
	 *            which node types to count; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use
	 * @return the number of ancestors
	 */
	public static int getAncestorCount(Node node, int whatToShow, NodeFilter filter)
	{
		return getAncestorCount(getDocumentTreeWalker(node, whatToShow, filter));
	}
	
	/**
	 * Gets the number of ancestors of the specified tree walker's current node in the logical view.
	 * 
	 * @param walker
	 *            the tree walker to use
	 * @return the number of ancestors
	 */
	public static int getAncestorCount(TreeWalker walker)
	{
		int count = 0;
		
		while (walker.parentNode() != null)
		{
			count++;
		}
		
		return count;
	}
	
	// getAncestorOrSelf() ----------------------------------------------------
	
	/**
	 * Gets either the specified node or its first ancestor that is of the given type.
	 * 
	 * @param node
	 *            the node to obtain the ancestor or self of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return either the specified node, an ancestor node, or <code>null</code> if none found
	 */
	public static Node getAncestorOrSelf(Node node, int whatToShow)
	{
		return getAncestorOrSelf(node, whatToShow, null);
	}
	
	/**
	 * Gets either the specified node or its first ancestor that is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to obtain the ancestor or self of
	 * @param filter
	 *            the filter to use
	 * @return either the specified node, an ancestor node, or <code>null</code> if none found
	 */
	public static Node getAncestorOrSelf(Node node, NodeFilter filter)
	{
		return getAncestorOrSelf(node, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Gets either the specified node or its first ancestor that is of the given type and accepted by the given filter.
	 * 
	 * @param node
	 *            the node to obtain the ancestor or self of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use
	 * @return either the specified node, an ancestor node, or <code>null</code> if none found
	 */
	public static Node getAncestorOrSelf(Node node, int whatToShow, NodeFilter filter)
	{
		if (isVisible(node, whatToShow) && isSelf(node, filter))
		{
			return node;
		}
		
		return getAncestor(node, whatToShow, filter);
	}
	
	// getAncestorsAndSelf() --------------------------------------------------
	
	/**
	 * Gets a list containing the specified node and its ancestors.
	 * 
	 * @param node
	 *            the node to obtain ancestors of
	 * @return a list containing the specified node and its ancestors, in document order
	 */
	public static List<Node> getAncestorsAndSelf(Node node)
	{
		return getAncestorsAndSelf(node, null);
	}
	
	/**
	 * Gets a list containing the specified node and its ancestors that are of the given type.
	 * 
	 * @param node
	 *            the node to obtain ancestors of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return a list containing the specified node and its ancestors, in document order
	 */
	public static List<Node> getAncestorsAndSelf(Node node, int whatToShow)
	{
		return getAncestorsAndSelf(node, whatToShow, null);
	}
	
	/**
	 * Gets a list containing the specified node and its ancestors that are accepted by the given filter.
	 * 
	 * @param node
	 *            the node to obtain ancestors of
	 * @param filter
	 *            the filter to use
	 * @return a list containing the specified node and its ancestors, in document order
	 */
	public static List<Node> getAncestorsAndSelf(Node node, NodeFilter filter)
	{
		return getAncestorsAndSelf(node, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Gets a list containing the specified node and its ancestors that are of the given type and accepted by the given
	 * filter.
	 * 
	 * @param node
	 *            the node to obtain ancestors of
	 * @param whatToShow
	 *            which node types to return; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use
	 * @return a list containing the specified node and its ancestors, in document order
	 */
	public static List<Node> getAncestorsAndSelf(Node node, int whatToShow, NodeFilter filter)
	{
		TreeWalker walker = getDocumentTreeWalker(node, whatToShow, filter);

		return getAncestorsAndSelf(walker);
	}
	
	/**
	 * Gets a list containing the specified tree walker's current node and its ancestors in the logical view.
	 * 
	 * @param walker
	 *            the tree walker to use
	 * @return a list containing the current node and its ancestors, in document order
	 */
	public static List<Node> getAncestorsAndSelf(TreeWalker walker)
	{
		Node self = walker.getCurrentNode();
		
		List<Node> ancestors = getAncestors(walker);
		
		if (isVisible(self, walker))
		{
			ancestors.add(self);
		}
		
		return ancestors;
	}
	
	// hasAncestorOrSelf() ----------------------------------------------------
	
	/**
	 * Gets whether the specified node or an ancestor is of the given type.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param whatToShow
	 *            which node types to consider; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return <code>true</code> if the specified node or an ancestor was of the given type
	 */
	public static boolean hasAncestorOrSelf(Node node, int whatToShow)
	{
		return hasAncestorOrSelf(node, whatToShow, null);
	}
	
	/**
	 * Gets whether the specified node or an ancestor is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return <code>true</code> if the specified node or an ancestor was accepted by the filter
	 */
	public static boolean hasAncestorOrSelf(Node node, NodeFilter filter)
	{
		return hasAncestorOrSelf(node, NodeFilter.SHOW_ALL, filter);
	}
	
	/**
	 * Gets whether the specified node or an ancestor is of the given type and accepted by the given filter.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param whatToShow
	 *            which node types to consider; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @param filter
	 *            the filter to use
	 * @return <code>true</code> if the specified node or an ancestor was of the given type and accepted by the filter
	 */
	public static boolean hasAncestorOrSelf(Node node, int whatToShow, NodeFilter filter)
	{
		return getAncestorOrSelf(node, whatToShow, filter) != null;
	}
	
	// getFirstChild() --------------------------------------------------------
	
	/**
	 * Gets the first child of the specified node that is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the first child, or <code>null</code> if none found
	 * @see TreeWalker#firstChild()
	 */
	public static Node getFirstChild(Node node, NodeFilter filter)
	{
		return getFirstChild(NodeUtils.getDocument(node), node, filter);
	}
	
	/**
	 * Gets the first child of the specified node that is accepted by the given filter in a given sub-tree.
	 * 
	 * @param root
	 *            the node that the sub-tree is rooted at
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the first child, or <code>null</code> if none found
	 * @see TreeWalker#firstChild()
	 */
	public static Node getFirstChild(Node root, Node node, NodeFilter filter)
	{
		TreeWalker walker = getTreeWalker(root, filter);
		walker.setCurrentNode(node);
		return walker.firstChild();
	}
	
	// getFirstChildOrSelf() --------------------------------------------------
	
	/**
	 * Gets either the specified node or its first child that is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return either the specified node, the first child, or <code>null</code> if none found
	 * @see TreeWalker#firstChild()
	 */
	public static Node getFirstChildOrSelf(Node node, NodeFilter filter)
	{
		if (isSelf(node, filter))
		{
			return node;
		}
		
		return getFirstChild(node, filter);
	}
	
	// getLastChild() ---------------------------------------------------------
	
	/**
	 * Gets the last child of the specified node that is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the last child, or <code>null</code> if none found
	 * @see TreeWalker#lastChild()
	 */
	public static Node getLastChild(Node node, NodeFilter filter)
	{
		return getLastChild(NodeUtils.getDocument(node), node, filter);
	}
	
	/**
	 * Gets the last child of the specified node that is accepted by the given filter in a given sub-tree.
	 * 
	 * @param root
	 *            the node that the sub-tree is rooted at
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the last child, or <code>null</code> if none found
	 * @see TreeWalker#lastChild()
	 */
	public static Node getLastChild(Node root, Node node, NodeFilter filter)
	{
		TreeWalker walker = getTreeWalker(root, filter);
		walker.setCurrentNode(node);
		return walker.lastChild();
	}
	
	// getLastChildOrSelf() ---------------------------------------------------
	
	/**
	 * Gets either the specified node or it's last child that is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return either the specified node, the last child, or <code>null</code> if none found
	 * @see TreeWalker#lastChild()
	 */
	public static Node getLastChildOrSelf(Node node, NodeFilter filter)
	{
		if (isSelf(node, filter))
		{
			return node;
		}
		
		return getLastChild(node, filter);
	}
	
	// getPreviousSibling() ---------------------------------------------------
	
	/**
	 * Gets the first previous sibling of the specified node that is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the previous sibling, or <code>null</code> if none found
	 * @see TreeWalker#previousSibling()
	 */
	public static Node getPreviousSibling(Node node, NodeFilter filter)
	{
		return getPreviousSibling(NodeUtils.getDocument(node), node, filter);
	}
	
	/**
	 * Gets the first previous sibling of the specified node that is accepted by the given filter in a given sub-tree.
	 * 
	 * @param root
	 *            the node that the sub-tree is rooted at
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the previous sibling, or <code>null</code> if none found
	 * @see TreeWalker#previousSibling()
	 */
	public static Node getPreviousSibling(Node root, Node node, NodeFilter filter)
	{
		TreeWalker walker = getTreeWalker(root, filter);
		walker.setCurrentNode(node);
		return walker.previousSibling();
	}
	
	// getNextSibling() -------------------------------------------------------
	
	/**
	 * Gets the first next sibling of the specified node that is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the next sibling, or <code>null</code> if none found
	 * @see TreeWalker#nextSibling()
	 */
	public static Node getNextSibling(Node node, NodeFilter filter)
	{
		return getNextSibling(NodeUtils.getDocument(node), node, filter);
	}
	
	/**
	 * Gets the first next sibling of the specified node that is accepted by the given filter in a given sub-tree.
	 * 
	 * @param root
	 *            the node that the sub-tree is rooted at
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the next sibling, or <code>null</code> if none found
	 * @see TreeWalker#nextSibling()
	 */
	public static Node getNextSibling(Node root, Node node, NodeFilter filter)
	{
		TreeWalker walker = getTreeWalker(root, filter);
		walker.setCurrentNode(node);
		return walker.nextSibling();
	}
	
	// getPreviousNode() ------------------------------------------------------
	
	/**
	 * Gets the node that is located before the specified node according to document order.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @return the previous node, or <code>null</code> if none found
	 * @see #getPreviousNode(Node, NodeFilter)
	 */
	public static Node getPreviousNode(Node node)
	{
		return getPreviousNode(node, null);
	}
	
	/**
	 * Gets the first node that is accepted by the given filter and located before the specified node according to
	 * document order.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the previous node, or <code>null</code> if none found
	 * @see TreeWalker#previousNode()
	 */
	public static Node getPreviousNode(Node node, NodeFilter filter)
	{
		return getDocumentTreeWalker(node, filter).previousNode();
	}
	
	// getNextNode() ----------------------------------------------------------
	
	/**
	 * Gets the node that is located after the specified node according to document order.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @return the next node, or <code>null</code> if none found
	 * @see #getNextNode(Node, NodeFilter)
	 */
	public static Node getNextNode(Node node)
	{
		return getNextNode(node, null);
	}
	
	/**
	 * Gets the first node that is accepted by the given filter and located after the specified node according to
	 * document order.
	 * 
	 * @param node
	 *            the node to traverse from
	 * @param filter
	 *            the filter to use
	 * @return the next node, or <code>null</code> if none found
	 * @see TreeWalker#nextNode()
	 */
	public static Node getNextNode(Node node, NodeFilter filter)
	{
		return getDocumentTreeWalker(node, filter).nextNode();
	}
	
	// isSelf() ---------------------------------------------------------------
	
	/**
	 * Gets whether the specified node is accepted by the given filter.
	 * 
	 * @param node
	 *            the node to test
	 * @param filter
	 *            the filter to use, possibly <code>null</code>
	 * @return <code>true</code> if the specified node was accepted by the filter, or the filter was <code>null</code>
	 */
	public static boolean isSelf(Node node, NodeFilter filter)
	{
		return (filter == null || filter.acceptNode(node) == NodeFilter.FILTER_ACCEPT);
	}
	
	// isVisible() ------------------------------------------------------------
	
	/**
	 * Gets whether the specified node exists within the given tree walker's logical view.
	 * 
	 * @param node
	 *            the node to test
	 * @param walker
	 *            the tree walker to use
	 * @return <code>true</code> if the specified node exists within the given tree walker's logical view
	 */
	public static boolean isVisible(Node node, TreeWalker walker)
	{
		// ensure node is of a visible type
		if (!isVisible(node, walker.getWhatToShow()))
		{
			return false;
		}
		
		// ensure node is accepted by the walker's filter, if any
		NodeFilter filter = walker.getFilter();
		if (filter != null && filter.acceptNode(node) != NodeFilter.FILTER_ACCEPT)
		{
			return false;
		}
		
		// ensure node is a descendant-or-self of the walker's root node
		if (!NodeUtils.isAncestorOrSelf(walker.getRoot(), node))
		{
			return false;
		}
		
		// node exists in walker's logical view
		return true;
	}
	
	// print() ----------------------------------------------------------------
	
	/**
	 * Pretty prints the specified tree walker's logical view to the standard output stream.
	 * 
	 * @param walker
	 *            the tree walker whose logical view to print
	 */
	public static void print(TreeWalker walker)
	{
		print(System.out, walker);
	}
	
	/**
	 * Pretty prints the specified tree walker's logical view to the given output stream.
	 * 
	 * @param out
	 *            the output stream to print to
	 * @param walker
	 *            the tree walker whose logical view to print
	 */
	public static void print(OutputStream out, TreeWalker walker)
	{
		accept(walker, new PrintNodeVisitor(out));
	}
	
	// accept() ---------------------------------------------------------------
	
	/**
	 * Applies the specified hierarchical node visitor to the given tree walker's logical view.
	 * 
	 * @param walker
	 *            the tree walker whose logical view to visit
	 * @param visitor
	 *            the node visitor to apply
	 * @return <code>true</code> if the tree walker's current node's siblings should be visited
	 */
	public static boolean accept(TreeWalker walker, NodeVisitor visitor)
	{
		Node node = walker.getCurrentNode();
		
		boolean visible = isVisible(node, walker);
		
		// visit node and return if it's a leaf type
		if (NodeUtils.isLeafType(node))
		{
			return visible ? NodeUtils.visit(node, visitor) : true;
		}
		
		// begin node visit
		if (!visible || NodeUtils.beginVisit(node, visitor))
		{
			// accept child nodes
			if (walker.firstChild() != null)
			{
				do
				{
					Node child = walker.getCurrentNode();
					
					if (!accept(walker, visitor))
					{
						break;
					}
					
					walker.setCurrentNode(child);
				}
				while (walker.nextSibling() != null);
			}
		}
		
		// end node visit
		return visible ? NodeUtils.endVisit(node, visitor) : true;
	}
}
