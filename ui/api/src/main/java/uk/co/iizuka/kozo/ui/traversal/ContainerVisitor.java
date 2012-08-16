/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/ContainerVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import uk.co.iizuka.kozo.ui.Container;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ContainerVisitor.java 100084 2012-03-30 10:23:59Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public interface ContainerVisitor<T extends Container, P, E extends Exception>
	extends HierarchicalComponentVisitor<T, P, E>
{
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	Visit visitChild(T container, P parameter, int childIndex) throws E;
	
	EndVisit endVisitChild(T container, P parameter, int childIndex) throws E;
	// CHECKSTYLE:ON
}
