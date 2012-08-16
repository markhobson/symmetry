/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/SelectedComponentDeckVisitor.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

import uk.co.iizuka.kozo.ui.Deck;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SelectedComponentDeckVisitor.java 100650 2012-04-23 10:07:01Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class SelectedComponentDeckVisitor<T extends Deck, P, E extends Exception> extends DelegatingContainerVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public SelectedComponentDeckVisitor(ContainerVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}
	
	// ContainerVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitChild(T deck, P parameter, int childIndex) throws E
	{
		// skip unselected components
		if (childIndex != deck.getSelectedIndex())
		{
			return SKIP_CHILDREN;
		}
		
		return super.visitChild(deck, parameter, childIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitChild(T deck, P parameter, int childIndex) throws E
	{
		// skip siblings after selected component
		if (childIndex >= deck.getSelectedIndex())
		{
			return SKIP_SIBLINGS;
		}
		
		return super.endVisitChild(deck, parameter, childIndex);
	}
}
