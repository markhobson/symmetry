/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/NodeClosure.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import org.w3c.dom.Node;

/**
 * Defines a closure that operates upon nodes.
 * 
 * @author Mark Hobson
 */
public interface NodeClosure
{
	/**
	 * Executes this closure for the specified node.
	 * 
	 * @param node
	 *            the node to execute this closure upon
	 */
	void execute(Node node);
}
