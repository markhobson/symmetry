/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/NodeFilters.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

import uk.co.iizuka.common.xml.dom.filter.AncestorFilter;
import uk.co.iizuka.common.xml.dom.filter.AttrFilter;
import uk.co.iizuka.common.xml.dom.filter.ConjunctionFilter;
import uk.co.iizuka.common.xml.dom.filter.ConstantFilter;
import uk.co.iizuka.common.xml.dom.filter.DisjunctionFilter;
import uk.co.iizuka.common.xml.dom.filter.ElementFilter;
import uk.co.iizuka.common.xml.dom.filter.EmptyCharacterDataFilter;
import uk.co.iizuka.common.xml.dom.filter.LeafFilter;
import uk.co.iizuka.common.xml.dom.filter.NegationFilter;
import uk.co.iizuka.common.xml.dom.filter.ParentFilter;
import uk.co.iizuka.common.xml.dom.filter.ShowFilter;

/**
 * A factory to build various node filters.
 * 
 * @author Mark Hobson
 * @version $Id: NodeFilters.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public final class NodeFilters
{
	// constants --------------------------------------------------------------
	
	/**
	 * Show all <code>CharacterData</code> nodes.
	 */
	public static final int SHOW_CHARACTER_DATA = NodeFilter.SHOW_CDATA_SECTION
		| NodeFilter.SHOW_COMMENT
		| NodeFilter.SHOW_TEXT;
	
	private static final NodeFilter ATTRIBUTE_FILTER = new ShowFilter(NodeFilter.SHOW_ATTRIBUTE);
	
	private static final NodeFilter CDATA_SECTION_FILTER = new ShowFilter(NodeFilter.SHOW_CDATA_SECTION);
	
	private static final NodeFilter CHARACTER_DATA_FILTER = new ShowFilter(SHOW_CHARACTER_DATA);
	
	private static final NodeFilter COMMENT_FILTER = new ShowFilter(NodeFilter.SHOW_COMMENT);
	
	private static final NodeFilter DOCUMENT_FILTER = new ShowFilter(NodeFilter.SHOW_DOCUMENT);
	
	private static final NodeFilter DOCUMENT_FRAGMENT_FILTER = new ShowFilter(NodeFilter.SHOW_DOCUMENT_FRAGMENT);
	
	private static final NodeFilter DOCUMENT_TYPE_FILTER = new ShowFilter(NodeFilter.SHOW_DOCUMENT_TYPE);
	
	private static final NodeFilter ELEMENT_FILTER = new ShowFilter(NodeFilter.SHOW_ELEMENT);
	
	private static final NodeFilter ENTITY_FILTER = new ShowFilter(NodeFilter.SHOW_ENTITY);
	
	private static final NodeFilter ENTITY_REFERENCE_FILTER = new ShowFilter(NodeFilter.SHOW_ENTITY_REFERENCE);
	
	private static final NodeFilter NOTATION_FILTER = new ShowFilter(NodeFilter.SHOW_NOTATION);
	
	private static final NodeFilter PROCESSING_INSTRUCTION_FILTER = new ShowFilter(
		NodeFilter.SHOW_PROCESSING_INSTRUCTION);
	
	private static final NodeFilter TEXT_FILTER = new ShowFilter(NodeFilter.SHOW_TEXT);
	
	private static final NodeFilter EMPTY_CHARACTER_DATA_FILTER = new EmptyCharacterDataFilter();
	
	private static final NodeFilter LEAF_FILTER = new LeafFilter();
	
	// constructors -----------------------------------------------------------
	
	private NodeFilters()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Gets a node filter that accepts every node.
	 * 
	 * @return the filter
	 */
	public static NodeFilter accept()
	{
		return ConstantFilter.ACCEPT;
	}
	
	/**
	 * Gets a node filter that rejects every node.
	 * 
	 * @return the filter
	 */
	public static NodeFilter reject()
	{
		return ConstantFilter.REJECT;
	}
	
	/**
	 * Gets a node filter that skips every node.
	 * 
	 * @return the filter
	 */
	public static NodeFilter skip()
	{
		return ConstantFilter.SKIP;
	}
	
	/**
	 * Gets a node filter that only accepts attribute nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter attribute()
	{
		return ATTRIBUTE_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts CDATA section nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter cdataSection()
	{
		return CDATA_SECTION_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts character data nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter characterData()
	{
		return CHARACTER_DATA_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts comment nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter comment()
	{
		return COMMENT_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts document nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter document()
	{
		return DOCUMENT_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts document fragment nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter documentFragment()
	{
		return DOCUMENT_FRAGMENT_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts document type nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter documentType()
	{
		return DOCUMENT_TYPE_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts element nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter element()
	{
		return ELEMENT_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts entity nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter entity()
	{
		return ENTITY_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts entity reference nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter entityReference()
	{
		return ENTITY_REFERENCE_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts notation nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter notation()
	{
		return NOTATION_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts processing instruction nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter processingInstruction()
	{
		return PROCESSING_INSTRUCTION_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts text nodes.
	 * 
	 * @return the filter
	 */
	public static NodeFilter text()
	{
		return TEXT_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts the specified types of nodes.
	 * 
	 * @param whatToShow
	 *            which node types to accept; specified by bitwise oring the <code>NodeFilter.SHOW_*</code> constants
	 *            together
	 * @return the filter
	 * @see NodeFilter
	 */
	public static NodeFilter show(int whatToShow)
	{
		return new ShowFilter(whatToShow);
	}
	
	/**
	 * Gets a node filter that only accepts attributes with the specified local name.
	 * 
	 * @param localName
	 *            the attribute local name to accept
	 * @return the filter
	 */
	public static NodeFilter attribute(String localName)
	{
		return new AttrFilter(localName);
	}
	
	/**
	 * Gets a node filter that only accepts attributes within the specified namespace with the specified local name.
	 * 
	 * @param namespaceURI
	 *            the attribute namespace URI to accept
	 * @param localName
	 *            the attribute local name to accept
	 * @return the filter
	 */
	public static NodeFilter attribute(String namespaceURI, String localName)
	{
		return new AttrFilter(namespaceURI, localName);
	}
	
	/**
	 * Gets a node filter that only accepts attributes within the specified namespace with the specified local name and
	 * value.
	 * 
	 * @param namespaceURI
	 *            the attribute namespace URI to accept
	 * @param localName
	 *            the attribute local name to accept
	 * @param value
	 *            the attribute value to accept
	 * @return the filter
	 */
	public static NodeFilter attribute(String namespaceURI, String localName, String value)
	{
		return new AttrFilter(namespaceURI, localName, value);
	}
	
	/**
	 * Gets a node filter that only accepts elements within the specified namespace.
	 * 
	 * @param namespaceURI
	 *            the element namespace URI to accept, or <code>"*"</code> to accept all namespace URIs
	 * @return the filter
	 */
	public static NodeFilter element(String namespaceURI)
	{
		return new ElementFilter(namespaceURI);
	}
	
	/**
	 * Gets a node filter that only accepts elements within the specified namespace with the specified local names.
	 * 
	 * @param namespaceURI
	 *            the element namespace URI to accept, or <code>"*"</code> to accept all namespace URIs
	 * @param localNames
	 *            the element local names to accept, or a <code>{"*"}</code> to accept all local names
	 * @return the filter
	 */
	public static NodeFilter element(String namespaceURI, String... localNames)
	{
		return new ElementFilter(namespaceURI, localNames);
	}
	
	/**
	 * Gets a node filter that only accepts <code>CharacterData</code> nodes with no content.
	 * 
	 * @return the filter
	 */
	public static NodeFilter emptyCharacterData()
	{
		return EMPTY_CHARACTER_DATA_FILTER;
	}
	
	/**
	 * Gets a node filter that only accepts nodes that are an ancestor of the specified node.
	 * 
	 * @param node
	 *            the node to accept ancestors of
	 * @return the filter
	 */
	public static NodeFilter ancestor(Node node)
	{
		return new AncestorFilter(node);
	}
	
	/**
	 * Gets a node filter that only accepts nodes without any children.
	 * 
	 * @return the filter
	 */
	public static NodeFilter leaf()
	{
		return LEAF_FILTER;
	}
	
	/**
	 * Gets a node filter that logically negates the specified filter.
	 * 
	 * @param filter
	 *            the filter to negate
	 * @return the filter
	 * @see NegationFilter
	 */
	public static NodeFilter negate(NodeFilter filter)
	{
		return new NegationFilter(filter);
	}
	
	/**
	 * Gets a node filter that logically ANDs the specified filters together.
	 * 
	 * @param filters
	 *            the filters
	 * @return the filter
	 * @see ConjunctionFilter
	 */
	public static NodeFilter conjunction(NodeFilter... filters)
	{
		return new ConjunctionFilter(filters);
	}
	
	/**
	 * Gets a node filter that logically ORs the specified filters together.
	 * 
	 * @param filters
	 *            the filters
	 * @return the filter
	 * @see DisjunctionFilter
	 */
	public static NodeFilter disjunction(NodeFilter... filters)
	{
		return new DisjunctionFilter(filters);
	}
	
	/**
	 * Gets a node filter that logically negates the specified filter.
	 * <p>
	 * This is a synonym for <code>negate</code>.
	 * </p>
	 * 
	 * @param filter
	 *            the filter to negate
	 * @return the filter
	 * @see #negate(NodeFilter)
	 */
	public static NodeFilter not(NodeFilter filter)
	{
		return negate(filter);
	}
	
	/**
	 * Gets a node filter that logically ANDs the specified filters together.
	 * <p>
	 * This is a synonym for <code>conjunction</code>.
	 * </p>
	 * 
	 * @param filters
	 *            the filters
	 * @return the filter
	 * @see #conjunction(NodeFilter[])
	 */
	public static NodeFilter and(NodeFilter... filters)
	{
		return conjunction(filters);
	}
	
	/**
	 * Gets a node filter that logically ORs the specified filters together.
	 * <p>
	 * This is a synonym for <code>disjunction</code>.
	 * </p>
	 * 
	 * @param filters
	 *            the filters
	 * @return the filter
	 * @see #disjunction(NodeFilter[])
	 */
	public static NodeFilter or(NodeFilter... filters)
	{
		return disjunction(filters);
	}
	
	/**
	 * Gets a node filter that accepts a node if and only if the specified filter accepts it's parent node.
	 * 
	 * @param filter
	 *            the filter to apply to the parent node
	 * @return the filter
	 */
	public static NodeFilter parent(NodeFilter filter)
	{
		return new ParentFilter(filter);
	}
}
