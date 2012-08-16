/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/NullListBoxVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import uk.co.iizuka.kozo.ui.ComboBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: NullListBoxVisitor.java 100086 2012-03-30 10:28:05Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public class NullListBoxVisitor<T extends ComboBox<?>, P, E extends Exception>
	extends NullHierarchicalComponentVisitor<T, P, E>
	implements ListBoxVisitor<T, P, E>
{
	// ListBoxVisitor methods -------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitItem(T comboBox, P parameter, int itemIndex) throws E
	{
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitItem(T comboBox, P parameter, int itemIndex) throws E
	{
		return VISIT_SIBLINGS;
	}
}
