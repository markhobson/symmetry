/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/ListBoxVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.ComboBox;

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
public interface ListBoxVisitor<T extends ComboBox<?>, P, E extends Exception>
	extends HierarchicalComponentVisitor<T, P, E>
{
	// Note: ComboBox used here since ListBoxVisitor is used for both ListBox and ComboBox. Perhaps rename interface?
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	Visit visitItem(T comboBox, P parameter, int itemIndex) throws E;
	
	EndVisit endVisitItem(T comboBox, P parameter, int itemIndex) throws E;
	// CHECKSTYLE:ON
}
