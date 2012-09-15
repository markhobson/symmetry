/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/visitor/ClosureNodeVisitor.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.visitor;

import org.hobsoft.symmetry.support.xml.dom.NodeClosure;
import org.w3c.dom.Node;

/**
 * A node visitor that executes a closure on visited nodes.
 * 
 * @author Mark Hobson
 * @version $Id: ClosureNodeVisitor.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class ClosureNodeVisitor extends AbstractNodeVisitor
{
	// fields -----------------------------------------------------------------
	
	/**
	 * The node closure to execute.
	 */
	private final NodeClosure closure;
	
	/**
	 * Whether to execute the closure on visited node's descendants.
	 */
	private final boolean deep;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a node visitor that executes the specified closure on visited nodes and their descendants.
	 * 
	 * @param closure
	 *            the node closure to execute
	 */
	public ClosureNodeVisitor(NodeClosure closure)
	{
		this(closure, true);
	}
	
	/**
	 * Creates a node visitor that executes the specified closure on visited nodes and optionally their descendants.
	 * 
	 * @param closure
	 *            the node closure to execute
	 * @param deep
	 *            whether to execute the closure on visited node's descendants
	 */
	public ClosureNodeVisitor(NodeClosure closure, boolean deep)
	{
		this.closure = closure;
		this.deep = deep;
	}

	// AbstractNodeVisitor methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean beginNode(Node node)
	{
		execute(node);
		
		return deep;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean visitNode(Node node)
	{
		execute(node);
		
		return super.visitNode(node);
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Executes the closure on the specified node.
	 * 
	 * @param node
	 *            the node to execute the closure upon
	 */
	protected void execute(Node node)
	{
		closure.execute(node);
	}
}
