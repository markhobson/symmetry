/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/TreeVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;

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
public interface TreeVisitor<T extends Tree, P, E extends Exception> extends HierarchicalComponentVisitor<T, P, E>
{
	// TODO: do we want *visitNodeChildren methods?
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	Visit visitNode(T tree, P parameter, TreePath path) throws E;
	
	Visit visitNodeChildren(T tree, P parameter, TreePath path) throws E;
	
	EndVisit endVisitNodeChildren(T tree, P parameter, TreePath path) throws E;
	
	EndVisit endVisitNode(T tree, P parameter, TreePath path) throws E;
	// CHECKSTYLE:ON
}
