/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/closure/CollectorClosure.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom.closure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import uk.co.iizuka.common.xml.dom.NodeClosure;
import uk.co.iizuka.common.xml.dom.NodeUtils;
import uk.co.iizuka.common.xml.dom.NodeVisitor;

/**
 * A node closure that collects nodes and makes them accessible for further processing.
 * 
 * @author Mark Hobson
 * @version $Id: CollectorClosure.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class CollectorClosure implements NodeClosure
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The list of collected nodes.
	 */
	private List<Node> nodes;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new <code>CollectorClosure</code>.
	 */
	public CollectorClosure()
	{
		nodes = new ArrayList<Node>();
	}

	// NodeClosure methods ----------------------------------------------------
	
	/**
	 * Collects the specified node.
	 * 
	 * @param node
	 *            the node to collect
	 */
	public void execute(Node node)
	{
		nodes.add(node);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Gets the collected nodes.
	 * 
	 * @return a read-only collection of nodes that have been visited
	 */
	public List<Node> getNodes()
	{
		return Collections.unmodifiableList(nodes);
	}
	
	/**
	 * Applies the specified hierarchical node visitor to the collected nodes.
	 * 
	 * @param visitor
	 *            the node visitor to apply
	 */
	public void accept(NodeVisitor visitor)
	{
		for (Node node : nodes)
		{
			if (!NodeUtils.accept(node, visitor))
			{
				break;
			}
		}
	}
}
