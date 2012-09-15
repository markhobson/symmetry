/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/ComponentVisitor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

/**
 * Defines a hierarchical visitor pattern for components.
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 * @see <a href="http://c2.com/cgi/wiki?HierarchicalVisitorPattern">HierarchicalVisitorPattern</a>
 */
public interface ComponentVisitor<P, E extends Exception>
{
	// public methods ---------------------------------------------------------
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	<T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType, T component,
		P parameter) throws E;
	// CHECKSTYLE:ON
}
