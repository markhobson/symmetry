/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/closure/RemoveChildClosure.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.closure;

import org.hobsoft.symmetry.support.xml.dom.NodeClosure;
import org.w3c.dom.Node;

/**
 * A node closure that removes a child from it's parent.
 * 
 * @author Mark Hobson
 * @version $Id: RemoveChildClosure.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class RemoveChildClosure implements NodeClosure
{
	// NodeClosure methods ----------------------------------------------------
	
	/**
	 * Removes the specified node from it's parent, if it has one.
	 * 
	 * @param node
	 *            the node to remove from it's parent
	 */
	public void execute(Node node)
	{
		Node parent = node.getParentNode();
		
		if (parent != null)
		{
			parent.removeChild(node);
		}
	}
}
