/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/NodeClosures.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom;

import org.hobsoft.symmetry.support.xml.dom.closure.CollectorClosure;
import org.hobsoft.symmetry.support.xml.dom.closure.RemoveAttributeClosure;
import org.hobsoft.symmetry.support.xml.dom.closure.RemoveChildClosure;
import org.hobsoft.symmetry.support.xml.dom.closure.SetAttributeClosure;
import org.hobsoft.symmetry.support.xml.dom.closure.UnsurroundClosure;

/**
 * A factory to build various node closures.
 * 
 * @author Mark Hobson
 */
public final class NodeClosures
{
	// constants --------------------------------------------------------------
	
	private static final RemoveChildClosure REMOVE_CHILD_CLOSURE = new RemoveChildClosure();
	
	private static final UnsurroundClosure UNSURROUND_CLOSURE = new UnsurroundClosure();
	
	private static final UnsurroundClosure CLONING_UNSURROUND_CLOSURE = new UnsurroundClosure(true);
	
	// constructors -----------------------------------------------------------
	
	private NodeClosures()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets a node closure that collects nodes and makes them accessible for further processing.
	 * 
	 * @return the node closure
	 */
	public static CollectorClosure collector()
	{
		return new CollectorClosure();
	}
	
	/**
	 * Gets a node closure that sets the specified attribute on elements.
	 * 
	 * @param name
	 *            the name of the attribute to set
	 * @param value
	 *            the value of the attribute to set
	 * @return the node closure
	 */
	public static SetAttributeClosure setAttribute(String name, String value)
	{
		return new SetAttributeClosure(name, value);
	}
	
	/**
	 * Gets a node closure that sets the specified namespaced attribute on elements.
	 * 
	 * @param namespaceURI
	 *            the namespace URI of the attribute to set
	 * @param localName
	 *            the local name of the attribute to set
	 * @param value
	 *            the value of the attribute to set
	 * @return the node closure
	 */
	public static SetAttributeClosure setAttributeNS(String namespaceURI, String localName, String value)
	{
		return new SetAttributeClosure(namespaceURI, localName, value);
	}
	
	/**
	 * Gets a node closure that removes the specified attribute from elements.
	 * 
	 * @param name
	 *            the name of the attribute to remove
	 * @return the node closure
	 */
	public static RemoveAttributeClosure removeAttribute(String name)
	{
		return new RemoveAttributeClosure(name);
	}
	
	/**
	 * Gets a node closure that removes the specified namespaced attribute from elements.
	 * 
	 * @param namespaceURI
	 *            the namespace URI of the attribute to remove
	 * @param localName
	 *            the local name of the attribute to remove
	 * @return the node closure
	 */
	public static RemoveAttributeClosure removeAttributeNS(String namespaceURI, String localName)
	{
		return new RemoveAttributeClosure(namespaceURI, localName);
	}
	
	/**
	 * Gets a node closure that removes a child from it's parent.
	 * 
	 * @return the node closure
	 */
	public static RemoveChildClosure removeChild()
	{
		return REMOVE_CHILD_CLOSURE;
	}
	
	/**
	 * Gets a node closure that replaces nodes with their children.
	 * 
	 * @return the node closure
	 */
	public static UnsurroundClosure unsurround()
	{
		return unsurround(false);
	}
	
	/**
	 * Gets a node closure that replaces nodes with their children, optionally cloning them before replacement.
	 * 
	 * @param clone
	 *            <code>true</code> to clone children before replacement
	 * @return the node closure
	 */
	public static UnsurroundClosure unsurround(boolean clone)
	{
		return clone ? CLONING_UNSURROUND_CLOSURE : UNSURROUND_CLOSURE;
	}
}
