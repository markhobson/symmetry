/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/NodeUtils.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.hobsoft.symmetry.support.xml.dom.visitor.PrintNodeVisitor;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.traversal.NodeFilter;

/**
 * Provides various utility methods for working with the DOM Core API.
 * 
 * @author Mark Hobson
 * @version $Id: NodeUtils.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see org.w3c.dom
 * @see <a href="http://www.w3.org/TR/2000/REC-DOM-Level-2-Core-20001113/">Document Object Model (DOM) Level 2 Core
 *      Specification</a>
 */
public final class NodeUtils
{
	// TODO: rename to CoreUtils?
	
	// constants --------------------------------------------------------------

	/**
	 * The name of the DOM Core feature.
	 */
	public static final String CORE_FEATURE = "Core";
	
	/**
	 * The version of the DOM Level 2 Core feature.
	 */
	public static final String CORE_VERSION_2_0 = "2.0";
	
	/**
	 * The version of the DOM Level 3 Core feature.
	 */
	public static final String CORE_VERSION_3_0 = "3.0";
	
	/**
	 * An array of node types in the same order as the <code>Node.*_NODE</code> constants.
	 * 
	 * @see Node
	 */
	private static final short[] NODE_TYPES = {
		Node.ELEMENT_NODE,
		Node.ATTRIBUTE_NODE,
		Node.TEXT_NODE,
		Node.CDATA_SECTION_NODE,
		Node.ENTITY_REFERENCE_NODE,
		Node.ENTITY_NODE,
		Node.PROCESSING_INSTRUCTION_NODE,
		Node.COMMENT_NODE,
		Node.DOCUMENT_NODE,
		Node.DOCUMENT_TYPE_NODE,
		Node.DOCUMENT_FRAGMENT_NODE,
		Node.NOTATION_NODE,
	};
	
	/**
	 * An array of node type names in the same order as the <code>Node.*_NODE</code> constants.
	 * 
	 * @see Node
	 */
	private static final String[] NODE_TYPE_NAMES = {
		"ELEMENT_NODE",
		"ATTRIBUTE_NODE",
		"TEXT_NODE",
		"CDATA_SECTION_NODE",
		"ENTITY_REFERENCE_NODE",
		"ENTITY_NODE",
		"PROCESSING_INSTRUCTION_NODE",
		"COMMENT_NODE",
		"DOCUMENT_NODE",
		"DOCUMENT_TYPE_NODE",
		"DOCUMENT_FRAGMENT_NODE",
		"NOTATION_NODE",
	};
	
	/**
	 * An array of the valid node types that can exist as children of <code>Document</code> nodes within the DOM tree.
	 */
	private static final short[] DOCUMENT_CHILD_TYPES = {
		Node.ELEMENT_NODE,
		Node.PROCESSING_INSTRUCTION_NODE,
		Node.COMMENT_NODE,
		Node.DOCUMENT_TYPE_NODE,
	};
	
	/**
	 * An array of the valid node types that can exist as children of <code>Element</code> nodes within the DOM tree.
	 */
	private static final short[] ELEMENT_CHILD_TYPES = {
		Node.ELEMENT_NODE,
		Node.TEXT_NODE,
		Node.CDATA_SECTION_NODE,
		Node.ENTITY_REFERENCE_NODE,
		Node.PROCESSING_INSTRUCTION_NODE,
		Node.COMMENT_NODE,
	};
	
	/**
	 * An array of the valid node types that can exist as children of <code>Attr</code> nodes within the DOM tree.
	 */
	private static final short[] ATTR_CHILD_TYPES = {
		Node.TEXT_NODE,
		Node.ENTITY_REFERENCE_NODE,
	};
	
	/**
	 * An empty array of node types.
	 */
	private static final short[] EMPTY_CHILD_TYPES = new short[0];
	
	/**
	 * A two-dimensional array that contains valid node types that can exist as children of other node types within the
	 * DOM tree.
	 * <p>
	 * For example, the index <code>Node.DOCUMENT_NODE - 1</code> contains an array of the valid node types that can
	 * exist as children of <code>Document</code> nodes within the DOM tree.
	 * </p>
	 */
	private static final short[][] NODE_CHILD_TYPES = {
		// Element
		ELEMENT_CHILD_TYPES,
		// Attr
		ATTR_CHILD_TYPES,
		// Text
		EMPTY_CHILD_TYPES,
		// CDATASection
		EMPTY_CHILD_TYPES,
		// EntityReference
		ELEMENT_CHILD_TYPES,
		// Entity
		ELEMENT_CHILD_TYPES,
		// ProcessingInstruction
		EMPTY_CHILD_TYPES,
		// Comment
		EMPTY_CHILD_TYPES,
		// Document
		DOCUMENT_CHILD_TYPES,
		// DocumentType
		EMPTY_CHILD_TYPES,
		// DocumentFragment
		ELEMENT_CHILD_TYPES,
		// Notation
		EMPTY_CHILD_TYPES,
	};

	private static final Node[] EMPTY_NODE_PATH = new Node[0];

	// constructors -----------------------------------------------------------

	private NodeUtils()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets whether the specified node's DOM implementation supports Core 2.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @return <code>true</code> if the node's DOM implementation supports Core 2.0
	 */
	public static boolean hasCore2(Node node)
	{
		return NodeUtils.hasFeature(node, CORE_FEATURE, CORE_VERSION_2_0);
	}
	
	/**
	 * Gets whether the specified node's DOM implementation supports Core 3.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @return <code>true</code> if the node's DOM implementation supports Core 3.0
	 */
	public static boolean hasCore3(Node node)
	{
		return NodeUtils.hasFeature(node, CORE_FEATURE, CORE_VERSION_3_0);
	}
	
	/**
	 * Ensures that the specified node's DOM implementation supports Core 2.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @throws DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Core 2.0
	 */
	public static void ensureCore2(Node node)
	{
		NodeUtils.ensureFeature(node, CORE_FEATURE, CORE_VERSION_2_0);
	}
	
	/**
	 * Ensures that the specified node's DOM implementation supports Core 3.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @throws DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Core 3.0
	 */
	public static void ensureCore3(Node node)
	{
		NodeUtils.ensureFeature(node, CORE_FEATURE, CORE_VERSION_3_0);
	}
	
	/**
	 * Gets the owner document for the specified node, or the node itself if it is a document.
	 * 
	 * @param node
	 *            the node to obtain the owner document for, not <code>null</code>
	 * @return the owner document of the specified node, or the node itself if it is a document
	 * @throws IllegalArgumentException
	 *             if the node is <code>null</code>
	 */
	public static Document getDocument(Node node)
	{
		AssertUtils.assertNotNull("node", node);
		
		return (node instanceof Document) ? (Document) node : node.getOwnerDocument();
	}
	
	/**
	 * Gets whether the specified node's DOM implementation supports the named feature.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @param feature
	 *            the name of the feature to check for
	 * @param version
	 *            the version of the feature to check for
	 * @return <code>true</code> if the node's DOM implementation supports the named feature
	 */
	public static boolean hasFeature(Node node, String feature, String version)
	{
		Document document = getDocument(node);
		DOMImplementation implementation = document.getImplementation();
		return implementation.hasFeature(feature, version);
	}
	
	/**
	 * Ensures that the specified node's DOM implementation supports the named feature.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @param feature
	 *            the name of the feature to check for
	 * @param version
	 *            the version of the feature to check for
	 * @throws DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support the named feature
	 */
	public static void ensureFeature(Node node, String feature, String version)
	{
		if (!hasFeature(node, feature, version))
		{
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "DOM implementation does not support " + feature
				+ " " + version);
		}
	}
	
	/**
	 * Gets an array of all node types as defined by the <code>Node.*_NODE</code> constants in order.
	 * 
	 * @return an array of all node types
	 */
	public static short[] getNodeTypes()
	{
		return NODE_TYPES;
	}
	
	/**
	 * Gets a string representation of the specified node's type.
	 * 
	 * @param node
	 *            the node
	 * @return a string representation of the specified node's type
	 */
	public static String getNodeTypeName(Node node)
	{
		return getNodeTypeName(node.getNodeType());
	}
	
	/**
	 * Gets a string representation of the specified node type.
	 * 
	 * @param type
	 *            the node type
	 * @return a string representation of the specified node type
	 * @throws IllegalArgumentException
	 *             if the specified node type is invalid
	 */
	public static String getNodeTypeName(short type)
	{
		validateNodeType(type);

		return NODE_TYPE_NAMES[type - 1];
	}
	
	public static String getQualifiedName(Node node)
	{
		return getQualifiedName(node.getPrefix(), node.getLocalName());
	}
	
	public static String getQualifiedName(String prefix, String localName)
	{
		return (prefix == null) ? localName : prefix + ":" + localName;
	}

	public static String getPrefix(String qualifiedName)
	{
		if (qualifiedName == null)
		{
			return null;
		}

		int index = qualifiedName.indexOf(':');
		return (index != -1) ? qualifiedName.substring(0, index) : null;
	}

	public static String getLocalName(String qualifiedName)
	{
		if (qualifiedName == null)
		{
			return null;
		}

		int index = qualifiedName.indexOf(':');
		return (index != -1) ? qualifiedName.substring(index + 1) : qualifiedName;
	}

	public static boolean isElement(Node node, String namespaceURI, String localName)
	{
		return (node instanceof Element
			&& equals(namespaceURI, node.getNamespaceURI())
			&& localName.equals(node.getLocalName())
		);
	}

	public static boolean isAttr(Node node, String namespaceURI, String localName, String value)
	{
		return (node instanceof Attr
			&& equals(namespaceURI, node.getNamespaceURI())
			&& localName.equals(node.getLocalName())
			&& (value == null || value.equals(node.getNodeValue()))
		);
	}
	
	/**
	 * Gets whether the specified node is an ancestor of the other.
	 * 
	 * @param ancestor
	 *            the ancestor node to look for
	 * @param node
	 *            the node whose ancestry to check
	 * @return <code>true</code> if the specified ancestor node is indeed an ancestor of the specified node
	 */
	public static boolean isAncestor(Node ancestor, Node node)
	{
		if (node == null)
		{
			return false;
		}
		
		return isAncestorOrSelf(ancestor, node.getParentNode());
	}
	
	/**
	 * Gets whether the specified node is either an ancestor of the other or the same node.
	 * 
	 * @param ancestor
	 *            the ancestor node to look for
	 * @param node
	 *            the node whose ancestry to check
	 * @return <code>true</code> if the specified ancestor node is either an ancestor of the specified node or the same
	 *         node
	 */
	public static boolean isAncestorOrSelf(Node ancestor, Node node)
	{
		while (node != null && node != ancestor)
		{
			node = node.getParentNode();
		}
		
		return (node != null && node == ancestor);
	}
	
	/**
	 * Gets the index of the specified node within it's siblings.
	 * 
	 * @param node
	 *            the node to obtain the index of, possibly <code>null</code>
	 * @return the node's index within it's siblings, or <code>-1</code> if the node is <code>null</code>
	 */
	public static int indexOf(Node node)
	{
		int index = -1;
		
		while (node != null)
		{
			index++;
			node = node.getPreviousSibling();
		}
		
		return index;
	}
	
	/**
	 * Gets the index of the specified node within the given node list.
	 * 
	 * @param list
	 *            the node list to obtain the index in
	 * @param node
	 *            the node to obtain the index of, possibly <code>null</code>
	 * @return the index of the node within the node list, or <code>-1</code> if the node is <code>null</code> or not
	 *         found within the node list
	 */
	public static int indexOf(NodeList list, Node node)
	{
		for (int i = 0; i < list.getLength(); i++)
		{
			if (list.item(i).equals(node))
			{
				return i;
			}
		}
		
		return -1;
	}

	public static int getLength(NodeList list, NodeFilter filter)
	{
		int count = 0;
		for (int i = 0; i < list.getLength(); i++)
		{
			if (filter.acceptNode(list.item(i)) == NodeFilter.FILTER_ACCEPT)
			{
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Gets the depth of the specified node relative to its most distant ancestor.
	 * 
	 * @param node
	 *            the node to obtain the depth of
	 * @return the depth
	 */
	public static int getDepth(Node node)
	{
		return getDepth(node, null);
	}
	
	/**
	 * Gets the depth of the specified node relative to the specified ancestor.
	 * 
	 * @param node
	 *            the node to obtain the depth of
	 * @param ancestor
	 *            the ancestor node to count the depth from, or <code>null</code> for the deepest ancestor
	 * @return the depth, or <code>-1</code> if the node's ancestry does not contain the specified ancestor
	 */
	public static int getDepth(Node node, Node ancestor)
	{
		int depth = 0;
		
		while (node != ancestor && node != null)
		{
			node = node.getParentNode();
			depth++;
		}
		
		return (node == ancestor) ? depth : -1;
	}
	
	/**
	 * Gets the deepest of the specified nodes.
	 * 
	 * @param node1
	 *            the first node
	 * @param node2
	 *            the second node
	 * @return the deepest node from the two specified
	 */
	public static Node deepest(Node node1, Node node2)
	{
		int depth1 = getDepth(node1);
		int depth2 = getDepth(node2);
		
		return (depth2 > depth1) ? node2 : node1;
	}

	public static boolean canAppendChild(Node parent, Node child)
	{
		if (!canAppendChild(parent, child.getNodeType()))
		{
			return false;
		}
		
		// reject document elements that invalidate the document type
		if (parent instanceof Document && child instanceof Element)
		{
			DocumentType doctype = (DocumentType) getChild(parent, Node.DOCUMENT_TYPE_NODE);
		
			if (doctype != null && !doctype.getName().equals(child.getNodeName()))
			{
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean canAppendChild(Node parent, short childType)
	{
		short parentType = parent.getNodeType();

		if (!canAppendChild(parentType, childType))
		{
			return false;
		}

		// handle context sensitive hierarchy rules
		if (parent instanceof Document)
		{
			if (childType == Node.ELEMENT_NODE && hasChild(parent, Node.ELEMENT_NODE))
			{
				return false;
			}
			
			if (childType == Node.DOCUMENT_TYPE_NODE && hasChild(parent, Node.DOCUMENT_TYPE_NODE))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean canAppendChild(short parentType, short childType)
	{
		validateNodeType(parentType);
		validateNodeType(childType);
		
		short[] childTypes = NODE_CHILD_TYPES[parentType - 1];
		
		for (int i = 0; i < childTypes.length; i++)
		{
			if (childTypes[i] == childType)
			{
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Gets whether the specified node has a child node of the specified type.
	 * 
	 * @param parent
	 *            the node whose children to test
	 * @param childType
	 *            the node type of the child to test for
	 * @return <code>true</code> if the specified node has a child node of the specified type
	 */
	public static boolean hasChild(Node parent, short childType)
	{
		NodeList children = parent.getChildNodes();
		
		for (int i = 0; i < children.getLength(); i++)
		{
			if (children.item(i).getNodeType() == childType)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets whether the specified node is of a type that disallows child nodes.
	 * 
	 * @param node
	 *            the node whose type to test
	 * @return <code>true</code> if the specified node is of a type that disallows child nodes
	 * @see #isLeafType(short)
	 */
	public static boolean isLeafType(Node node)
	{
		return isLeafType(node.getNodeType());
	}
	
	/**
	 * Gets whether the specified node type disallows child nodes.
	 * <p>
	 * According to the DOM 2 Core Specification, the following node types satisfy this condition:
	 * </p>
	 * <ul>
	 * <li><code>Element</code></li>
	 * <li><code>Attr</code></li>
	 * <li><code>EntityReference</code></li>
	 * <li><code>Entity</code></li>
	 * <li><code>Document</code></li>
	 * <li><code>DocumentFragment</code></li>
	 * </ul>
	 * 
	 * @param type
	 *            the node type to test
	 * @return <code>true</code> if the specified node type disallows child nodes
	 * @throws IllegalArgumentException
	 *             if the specified node type is invalid
	 */
	public static boolean isLeafType(short type)
	{
		validateNodeType(type);
		
		return (NODE_CHILD_TYPES[type - 1].length == 0);
	}
	
	/**
	 * Gets the deepest ancestor of the specified node.
	 * 
	 * @param node
	 *            the node to obtain the root of
	 * @return the root node of the specified node
	 * @throws IllegalArgumentException
	 *             if the specified node is null
	 */
	public static Node getRoot(Node node)
	{
		AssertUtils.assertNotNull("node", node);
		
		while (node.getParentNode() != null)
		{
			node = node.getParentNode();
		}
		
		return node;
	}
	
	/**
	 * Gets the specified node and its ancestors as a list.
	 * 
	 * @param node
	 *            the node to obtain the ancestors of
	 * @return a list containing the specified node and it's ancestors, in document order
	 */
	public static List<Node> getAncestorsAndSelf(Node node)
	{
		List<Node> ancestors = new ArrayList<Node>();

		while (node != null)
		{
			// insert ancestor-or-self at start of list to preserve document order
			ancestors.add(0, node);
			
			node = node.getParentNode();
		}

		return ancestors;
	}

	public static Node getCommonAncestor(Node node1, Node node2)
	{
		// quick shortcut
		if (node1 == node2)
		{
			return node1;
		}

		// get ancestors
		List<Node> ancestors1 = getAncestorsAndSelf(node1);
		List<Node> ancestors2 = getAncestorsAndSelf(node2);

		Node commonAncestor = null;

		for (int i = 0; i < ancestors1.size() && i < ancestors2.size(); i++)
		{
			Node ancestor1 = ancestors1.get(i);
			Node ancestor2 = ancestors2.get(i);

			if (ancestor1 != ancestor2)
			{
				break;
			}

			commonAncestor = ancestor1;
		}

		return commonAncestor;
	}
	
	/**
	 * Replaces the specified node with its children.
	 * 
	 * @param node
	 *            the node to replace with it's children
	 */
	public static void unsurround(Node node)
	{
		unsurround(node, false);
	}
	
	/**
	 * Replaces the specified node with its children, which may be optionally cloned deeply.
	 * 
	 * @param node
	 *            the node to replace with it's children
	 * @param clone
	 *            <code>true</code> to clone the children before replacement
	 */
	public static void unsurround(Node node, boolean clone)
	{
		AssertUtils.assertNotNull("node", node);
		
		// create fragment
		Document document = getDocument(node);
		DocumentFragment fragment = document.createDocumentFragment();
		
		// add node's children to fragment
		// reverse iteration to handle non-cloned children moving when appended
		NodeList children = node.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--)
		{
			Node child = children.item(i);
			
			// clone if required
			if (clone)
			{
				child = child.cloneNode(true);
			}
			
			fragment.insertBefore(child, fragment.getFirstChild());
		}
		
		// replace node with fragment
		Node parent = node.getParentNode();
		parent.replaceChild(fragment, node);
	}
	
	/**
	 * Removes the highest single-child ancestor of the specified node from its parent, or just the specified node from
	 * its parent if it's not an only child.
	 * 
	 * @param node
	 *            the node to collapse
	 */
	public static void collapseNode(Node node)
	{
		Node parent = node.getParentNode();
		
		while (parent != null && parent.getChildNodes().getLength() == 1)
		{
			node = parent;
			parent = parent.getParentNode();
		}
		
		if (parent != null)
		{
			parent.removeChild(node);
		}
	}
	
	/**
	 * Removes any children from the specified node that the given filter accepts.
	 * 
	 * @param node
	 *            the node whose children to remove
	 * @param filter
	 *            the filter that determines which children to remove
	 */
	public static void removeChildren(Node node, NodeFilter filter)
	{
		// TODO: replace with a visitor that uses remove child closure
		
		NodeList children = node.getChildNodes();

		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);

			if (filter.acceptNode(child) == NodeFilter.FILTER_ACCEPT)
			{
				node.removeChild(child);
			}
		}
	}

	public static int getNodeIndex(Node parentNode, Node childNode)
	{
		Node tempNode = childNode;

		while (tempNode != null && !(tempNode instanceof Document))
		{
			Node currentParent = tempNode.getParentNode();

			if (currentParent == parentNode)
			{
				return indexOf(currentParent.getChildNodes(), tempNode);
			}

			tempNode = currentParent;
		}

		if (tempNode != null)
		{
			return 0;
		}

		throw new IllegalArgumentException(childNode + " is not a child of " + parentNode);
	}

	public static int getNodeIndex(Node child)
	{
		return getNodeIndex(child.getParentNode(), child);
	}

	public static Node[] getPath(Node parent, Node child)
	{
		AssertUtils.assertNotNull("parent", parent);
		AssertUtils.assertNotNull("child", child);
		assertSameDocument(parent, child);

		if (parent == child)
		{
			return EMPTY_NODE_PATH;
		}

		List<Node> path = new ArrayList<Node>();
		Node currentNode = child;

		while (currentNode != null && currentNode != parent)
		{
			path.add(0, currentNode);
			currentNode = currentNode.getParentNode();
		}
		
		if (currentNode != parent)
		{
			throw new IllegalArgumentException(child + " is not a child of " + parent);
		}

		return path.toArray(new Node[path.size()]);
	}

	public static int[] getPathIndex(Node parent, Node child)
	{
		Node[] path = getPath(parent, child);
		
		int[] pathIndex = new int[path.length];
		
		for (int i = 0; i < path.length; i++)
		{
			pathIndex[i] = getNodeIndex(path[i]);
		}

		return pathIndex;
	}
	
	/**
	 * Compares two positions within a DOM tree. The positions are specified as per DOM Range.
	 * 
	 * @param node1
	 *            the first position's container
	 * @param offset1
	 *            the first position's offset
	 * @param node2
	 *            the second position's container
	 * @param offset2
	 *            the second position's offset
	 * @return zero if the two positions are equal; negative if the first position appears before the second position in
	 *         the DOM tree; positive if the first position appears after the second position in the DOM tree
	 */
	public static int compare(Node node1, int offset1, Node node2, int offset2)
	{
		// TODO: optimise, since used heavily by RangeNoteFilter
		
		if (node1.equals(node2))
		{
			return (int) Math.signum(offset1 - offset2);
		}

		// ok, not the same node, compare paths
		Node commonNode = NodeUtils.getCommonAncestor(node1, node2);
		
		if (commonNode == null)
		{
			return 0;
		}

		int[] path1 = NodeUtils.getPathIndex(commonNode, node1);
		int[] path2 = NodeUtils.getPathIndex(commonNode, node2);

		int i = 0;
		for ( ; i < path1.length && i < path2.length; i++)
		{
			int index1 = path1[i];
			int index2 = path2[i];

			if (index1 < index2)
			{
				return -1;
			}
			
			if (index1 > index2)
			{
				return 1;
			}
		}

		if (i == path1.length)
		{
			if (offset1 <= path2[i == 0 ? 0 : i + 1])
			{
				return -1;
			}
		}
		else
		{
			if (path1[i == 0 ? 0 : i + 1] < offset2)
			{
				return -1;
			}
		}

		return 1;
	}
	
	/**
	 * Gets the unique XPath expression for the specified node.
	 * 
	 * @param node
	 *            the node to obtain the expression for
	 * @return the XPath expression
	 */
	public static String toXPath(Node node)
	{
		return toXPath(node, new StringBuilder()).insert(0, '/').toString();
	}
	
	/**
	 * Gets the node that the specified XPath expression refers to given a context node.
	 * 
	 * The following subset of XPath syntax is currently supported:
	 * 
	 * <ul>
	 * <li>absolute and relative paths</li>
	 * <li>element and attribute particle syntax</li>
	 * <li>position index predicates, e.g. <code>[1]</code></li>
	 * </ul>
	 * 
	 * In addition, the following extensions to XPath are supported:
	 * 
	 * <ul>
	 * <li>referencing text nodes using <code>#text</code></li>
	 * </ul>
	 * 
	 * @param node
	 *            the context node to evaluate the XPath expression from
	 * @param xpath
	 *            the XPath expression to evaluate
	 * @return the node
	 */
	public static Node evaluateXPath(Node node, String xpath)
	{
		if (xpath == null || xpath.length() == 0)
		{
			return node;
		}

		StringBuilder builder = new StringBuilder(xpath);

		// move context node to document if path is absolute
		if (builder.charAt(0) == '/')
		{
			builder.deleteCharAt(0);
			node = getDocument(node);
		}

		return evaluateXPath(node, builder);
	}
	
	/**
	 * Pretty prints the specified node to the standard output stream.
	 * 
	 * @param node
	 *            the node to print
	 */
	public static void print(Node node)
	{
		print(System.out, node);
	}
	
	/**
	 * Pretty prints the specified node to the given print stream.
	 * 
	 * @param out
	 *            the print stream to print to
	 * @param node
	 *            the node to print
	 */
	public static void print(PrintStream out, Node node)
	{
		print((Appendable) out, node);
	}
	
	/**
	 * Pretty prints the specified node to the given output stream.
	 * 
	 * @param out
	 *            the output stream to print to
	 * @param node
	 *            the node to print
	 */
	public static void print(OutputStream out, Node node)
	{
		print(new OutputStreamWriter(out), node);
	}
	
	/**
	 * Pretty prints the specified node to the given appendable.
	 * 
	 * @param appendable
	 *            the appendable to print to
	 * @param node
	 *            the node to print
	 */
	public static void print(Appendable appendable, Node node)
	{
		accept(node, new PrintNodeVisitor(appendable));
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the given node.
	 * 
	 * @param node
	 *            the node to apply the visitor to
	 * @param visitor
	 *            the node visitor to apply
	 * @return <code>true</code> if the node's next siblings should be visited
	 */
	public static boolean accept(Node node, NodeVisitor visitor)
	{
		if (isLeafType(node))
		{
			return visit(node, visitor);
		}

		if (beginVisit(node, visitor))
		{
			accept(node.getChildNodes(), visitor);
		}
		
		return endVisit(node, visitor);
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the given node list.
	 * 
	 * @param nodes
	 *            the node list to apply the visitor to
	 * @param visitor
	 *            the node visitor to apply
	 */
	public static void accept(NodeList nodes, NodeVisitor visitor)
	{
		for (int i = 0; i < nodes.getLength(); i++)
		{
			if (!accept(nodes.item(i), visitor))
			{
				break;
			}
		}
	}
	
	/**
	 * Invokes the corresponding begin visit method on the specified node visitor for the given node.
	 * 
	 * @param node
	 *            the node that's being visited
	 * @param visitor
	 *            the node visitor to invoke
	 * @return <code>true</code> if the node's children should be visited
	 * @throws IllegalArgumentException
	 *             if the specified node's type is a leaf type or is not recognised
	 */
	public static boolean beginVisit(Node node, NodeVisitor visitor)
	{
		if (isLeafType(node))
		{
			throw new IllegalArgumentException("Node is a leaf type: " + node);
		}
			
		switch (node.getNodeType())
		{
			case Node.ELEMENT_NODE:
				return visitor.beginElement((Element) node);
			
			case Node.ATTRIBUTE_NODE:
				return visitor.beginAttr((Attr) node);
			
			case Node.ENTITY_REFERENCE_NODE:
				return visitor.beginEntityReference((EntityReference) node);
			
			case Node.ENTITY_NODE:
				return visitor.beginEntity((Entity) node);
			
			case Node.DOCUMENT_NODE:
				return visitor.beginDocument((Document) node);
			
			case Node.DOCUMENT_FRAGMENT_NODE:
				return visitor.beginDocumentFragment((DocumentFragment) node);
				
			default:
				throw new IllegalArgumentException("Unknown node type: " + node);
		}
	}
	
	/**
	 * Invokes the corresponding end visit method on the specified node visitor for the given node.
	 * 
	 * @param node
	 *            the node that's being visited
	 * @param visitor
	 *            the node visitor to invoke
	 * @return <code>true</code> if the node's next siblings should be visited
	 * @throws IllegalArgumentException
	 *             if the specified node's type is a leaf type or is not recognised
	 */
	public static boolean endVisit(Node node, NodeVisitor visitor)
	{
		if (isLeafType(node))
		{
			throw new IllegalArgumentException("Node is a leaf type: " + node);
		}
			
		switch (node.getNodeType())
		{
			case Node.ELEMENT_NODE:
				return visitor.endElement((Element) node);
			
			case Node.ATTRIBUTE_NODE:
				return visitor.endAttr((Attr) node);
			
			case Node.ENTITY_REFERENCE_NODE:
				return visitor.endEntityReference((EntityReference) node);
			
			case Node.ENTITY_NODE:
				return visitor.endEntity((Entity) node);
			
			case Node.DOCUMENT_NODE:
				return visitor.endDocument((Document) node);
			
			case Node.DOCUMENT_FRAGMENT_NODE:
				return visitor.endDocumentFragment((DocumentFragment) node);
				
			default:
				throw new IllegalArgumentException("Unknown node type: " + node);
		}
	}
	
	/**
	 * Invokes the corresponding visit method on the specified node visitor for the given node.
	 * 
	 * @param node
	 *            the node that's being visited
	 * @param visitor
	 *            the node visitor to invoke
	 * @return <code>true</code> if the node's next siblings should be visited
	 * @throws IllegalArgumentException
	 *             if the specified node's type is not a leaf type or is not recognised
	 */
	public static boolean visit(Node node, NodeVisitor visitor)
	{
		if (!isLeafType(node))
		{
			throw new IllegalArgumentException("Node is not a leaf type: " + node);
		}
			
		switch (node.getNodeType())
		{
			case Node.TEXT_NODE:
				return visitor.visitText((Text) node);
				
			case Node.CDATA_SECTION_NODE:
				return visitor.visitCDATASection((CDATASection) node);
			
			case Node.PROCESSING_INSTRUCTION_NODE:
				return visitor.visitProcessingInstruction((ProcessingInstruction) node);
			
			case Node.COMMENT_NODE:
				return visitor.visitComment((Comment) node);
			
			case Node.DOCUMENT_TYPE_NODE:
				return visitor.visitDocumentType((DocumentType) node);
			
			case Node.NOTATION_NODE:
				return visitor.visitNotation((Notation) node);
				
			default:
				throw new IllegalArgumentException("Unknown node type: " + node);
		}
	}
	
	public static void validateNodeType(short type)
	{
		if (type < 1 || type > NODE_TYPE_NAMES.length + 1)
		{
			throw new IllegalArgumentException("Unknown node type: " + type);
		}
	}

	// private methods --------------------------------------------------------
	
	private static boolean equals(Object a, Object b)
	{
		return (a == null && b == null) || (a != null && b != null && a.equals(b));
	}
	
	private static void assertSameDocument(Node node1, Node node2)
	{
		if (getDocument(node1) != getDocument(node2))
		{
			throw new IllegalArgumentException("Nodes are owned by different documents");
		}
	}
	
	private static Node getChild(Node parent, short childType)
	{
		NodeList children = parent.getChildNodes();
		
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			
			if (child.getNodeType() == childType)
			{
				return child;
			}
		}
		
		return null;
	}
	
	/**
	 * Inserts the unique XPath expression for the specified node into the specified <code>StringBuilder</code>.
	 * 
	 * Used internally by <code>toXPath(Node)</code>.
	 * 
	 * @param node
	 *            the node to obtain the expression for
	 * @param builder
	 *            the builder to prepend the expression to
	 * @return the specified builder
	 * @see #toXPath(Node)
	 */
	private static StringBuilder toXPath(Node node, StringBuilder builder)
	{
		// ignore document nodes
		if (node instanceof Document)
		{
			return builder;
		}

		// prepend slash if path not empty
		if (builder.length() > 0)
		{
			builder.insert(0, '/');
		}

		// prepend index if required
		int index = getXPathIndex(node);
		if (index != -1)
		{
			builder.insert(0, ']').insert(0, index).insert(0, '[');
		}

		// prepend node name
		builder.insert(0, node.getNodeName());

		// prepend attribute symbol is required
		if (node instanceof Attr)
		{
			builder.insert(0, "@");
		}

		// recurse to parent if any
		Node parent = (node instanceof Attr) ? ((Attr) node).getOwnerElement() : node.getParentNode();
		if (parent != null)
		{
			toXPath(parent, builder);
		}

		return builder;
	}
	
	/**
	 * Gets the XPath index of the given node within its siblings. This can be used as a positional predicate with the
	 * node's XPath element to differentiate it from its siblings.
	 * 
	 * Used internally by <code>toXPath(Node)</code>.
	 * 
	 * @param node
	 *            the node to get the index for
	 * @return the node's XPath index within its siblings, or -1 if the node does not require a positional predicate
	 * @see #toXPath(Node)
	 */
	private static int getXPathIndex(Node node)
	{
		// if node has no parent then it doesn't require an index
		Node parent = node.getParentNode();
		if (parent == null)
		{
			return -1;
		}

		// get node's siblings
		NodeList siblings = parent.getChildNodes();

		// find number of equivalent siblings and this node's index within them
		int index = 0;
		int count = 1;
		for (int i = 0; i < siblings.getLength(); i++)
		{
			Node sibling = siblings.item(i);

			if (sibling == node)
			{
				index = count;
			}
			else if (isXPathEqual(sibling, node))
			{
				count++;
			}
		}

		// return index if node is ambiguous
		return (count > 1) ? index : -1;
	}
	
	/**
	 * Gets whether the specified nodes are equivalent from an XPath syntax point-of-view. More specifically, this
	 * method returns true if these nodes require a positional predicate when they occur as siblings to avoid ambiguity.
	 * 
	 * Used internally by <code>toXPath(Node)</code>.
	 * 
	 * @param node1
	 *            the first node
	 * @param node2
	 *            the second node
	 * @return whether the specified nodes require a positional predicate when they occur as siblings
	 * @see #toXPath(Node)
	 */
	private static boolean isXPathEqual(Node node1, Node node2)
	{
		if (node1.getNodeType() != node2.getNodeType())
		{
			return false;
		}

		if (!node1.getNodeName().equals(node2.getNodeName()))
		{
			return false;
		}

		return true;
	}
	
	/**
	 * Gets the node that the specified relative XPath expression refers to given a context node.
	 * 
	 * Used internally by <code>evaluateXPath(Node, String)</code>.
	 * 
	 * @param node
	 *            the context node to evaluate the XPath expression from
	 * @param xpath
	 *            the relative XPath expression to evaluate
	 * @return the node
	 * @see #evaluateXPath(Node, String)
	 */
	private static Node evaluateXPath(Node node, StringBuilder xpath)
	{
		if (node == null)
		{
			return null;
		}

		// found the referenced node
		if (xpath.length() == 0)
		{
			return node;
		}

		// find end of next path particle
		int end = xpath.indexOf("/");
		if (end == -1)
		{
			end = xpath.length();
		}

		// get next particle and remove it from the current path
		String particle = xpath.substring(0, end);
		xpath.delete(0, end + 1);

		// find predicate, if any
		int open = particle.indexOf('[');
		int close = particle.indexOf(']');

		// parse predicate and truncate particle as necessary
		int index = -1;
		if (open != -1 && close != -1)
		{
			index = Integer.parseInt(particle.substring(open + 1, close));
			particle = particle.substring(0, open);
		}

		// obtain the child node referenced by the particle
		Node nextNode = getXPathNode(node, particle, index);

		// repeat process on child
		return evaluateXPath(nextNode, xpath);
	}
	
	/**
	 * Gets the node that the specified XPath particle refers to given a context node.
	 * 
	 * Used internally by <code>evaluateXPath(Node, String)</code>.
	 * 
	 * @param node
	 *            the context node to evaluate the XPath particle on
	 * @param particle
	 *            the XPath particle to evaluate
	 * @param index
	 *            the index within the matched nodes of the node to return, or <code>-1</code> for unspecified
	 * @return the node
	 * @see #evaluateXPath(Node, String)
	 */
	private static Node getXPathNode(Node node, String particle, int index)
	{
		// handle attribute nodes
		if (particle.startsWith("@"))
		{
			String name = particle.substring(1);
			return getXPathAttribute(node, name);
		}

		// handle text nodes
		if ("#text".equals(particle))
		{
			return getXPathNode(node, Node.TEXT_NODE, null, index);
		}

		// handle element nodes
		return getXPathNode(node, Node.ELEMENT_NODE, particle, index);
	}
	
	/**
	 * Gets the child node of specified type, name and index.
	 * 
	 * Used internally by <code>evaluateXPath(Node, String)</code>.
	 * 
	 * @param node
	 *            the parent node
	 * @param type
	 *            the type of node to return
	 * @param name
	 *            the name of the node to return, or <code>null</code> to ignore the node name
	 * @param index
	 *            the index within the matched nodes of the node to return, or <code>-1</code> for unspecified
	 * @return the node
	 * @see #evaluateXPath(Node, String)
	 */
	private static Node getXPathNode(Node node, short type, String name, int index)
	{
		NodeList children = node.getChildNodes();
		int matchedIndex = 0;
		Node matchedChild = null;

		// find the corresponding indexed node of the specified type
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);

			if (child.getNodeType() == type && (name == null || child.getNodeName().equals(name)))
			{
				matchedIndex++;

				if (index == -1 || matchedIndex == index)
				{
					matchedChild = child;
				}
			}
		}
		
		// TODO: support document fragments when no index specified and sub-tree matched
		if (index == -1 && matchedIndex > 1)
		{
			return null;
		}

		return matchedChild;
	}
	
	/**
	 * Gets the attribute node with the specified name.
	 * 
	 * Used internally by <code>evaluateXPath(Node, String)</code>.
	 * 
	 * @param node
	 *            the node
	 * @param name
	 *            the name of the attribute node to return
	 * @return the attribute node
	 * @see #evaluateXPath(Node, String)
	 */
	private static Node getXPathAttribute(Node node, String name)
	{
		NamedNodeMap attributes = node.getAttributes();
		return (attributes != null) ? attributes.getNamedItem(name) : null;
	}
}
