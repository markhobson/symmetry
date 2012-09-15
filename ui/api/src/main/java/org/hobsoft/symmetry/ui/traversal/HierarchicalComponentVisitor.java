/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/HierarchicalComponentVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public interface HierarchicalComponentVisitor<T extends Component, P, E extends Exception>
{
	// TODO: rename as we no longer extend ComponentVisitor
	
	// types ------------------------------------------------------------------
	
	/**
	 * Traversal action when visiting a component.
	 */
	enum Visit
	{
		VISIT_CHILDREN,
		SKIP_CHILDREN;
	}
	
	/**
	 * Traversal action after visiting a component.
	 */
	enum EndVisit
	{
		VISIT_SIBLINGS,
		SKIP_SIBLINGS;
	}
	
	// public methods ---------------------------------------------------------
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	Visit visit(T component, P parameter) throws E;
	// CHECKSTYLE:ON
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	EndVisit endVisit(T component, P parameter) throws E;
	// CHECKSTYLE:ON
}
