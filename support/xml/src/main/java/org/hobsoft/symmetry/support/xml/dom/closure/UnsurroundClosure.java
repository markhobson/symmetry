/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/closure/UnsurroundClosure.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.closure;

import org.hobsoft.symmetry.support.xml.dom.NodeClosure;
import org.hobsoft.symmetry.support.xml.dom.NodeUtils;
import org.w3c.dom.Node;

/**
 * A node closure that replaces nodes with their children.
 * 
 * @author Mark Hobson
 */
public class UnsurroundClosure implements NodeClosure
{
	// fields -----------------------------------------------------------------
	
	/**
	 * Whether to clone the children before replacement.
	 */
	private final boolean clone;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new <code>UnsurroundClosure</code> that does not clone children.
	 */
	public UnsurroundClosure()
	{
		this(false);
	}
	
	/**
	 * Creates a new <code>UnsurroundClosure</code> that clones children if specified.
	 * 
	 * @param clone
	 *            <code>true</code> to clone children before replacement
	 */
	public UnsurroundClosure(boolean clone)
	{
		this.clone = clone;
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Replaces the specified node with its children.
	 * 
	 * @param node
	 *            the node to replace with its children
	 */
	public void execute(Node node)
	{
		NodeUtils.unsurround(node, clone);
	}
}
