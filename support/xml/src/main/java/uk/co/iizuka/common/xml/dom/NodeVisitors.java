/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/NodeVisitors.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom;

import uk.co.iizuka.common.xml.dom.visitor.ClosureNodeVisitor;

/**
 * A factory to build various node visitors.
 * 
 * @author Mark Hobson
 * @version $Id: NodeVisitors.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public final class NodeVisitors
{
	// constructors -----------------------------------------------------------
	
	private NodeVisitors()
	{
		// private constructor for utility class
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets a node visitor that executes the specified closure on visited nodes and their descendants.
	 * 
	 * @param closure
	 *            the node closure to execute
	 * @return the node visitor
	 */
	public static ClosureNodeVisitor closure(NodeClosure closure)
	{
		return closure(closure, true);
	}
	
	/**
	 * Gets a node visitor that executes the specified closure on visited nodes and optionally their descendants.
	 * 
	 * @param closure
	 *            the node closure to execute
	 * @param deep
	 *            whether to execute the closure on visited node's descendants
	 * @return the node visitor
	 */
	public static ClosureNodeVisitor closure(NodeClosure closure, boolean deep)
	{
		return new ClosureNodeVisitor(closure, deep);
	}
}
