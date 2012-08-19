/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/NodeClosure.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom;

import org.w3c.dom.Node;

/**
 * Defines a closure that operates upon nodes.
 * 
 * @author Mark Hobson
 * @version $Id: NodeClosure.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
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
