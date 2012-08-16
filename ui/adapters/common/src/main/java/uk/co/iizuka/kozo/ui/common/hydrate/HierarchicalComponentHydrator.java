/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/HierarchicalComponentHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.hydrate;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HierarchicalComponentHydrator.java 95595 2011-11-28 12:38:59Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
public interface HierarchicalComponentHydrator<T extends Component>
	extends HierarchicalComponentVisitor<T, HydrationContext, HydrationException>
{
	// HierarchicalComponentVisitor methods -----------------------------------

	/**
	 * {@inheritDoc}
	 * 
	 * Overridden to rename argument.
	 */
	@Override
	Visit visit(T component, HydrationContext context) throws HydrationException;
	
	/**
	 * {@inheritDoc}
	 * 
	 * Overridden to rename argument.
	 */
	@Override
	EndVisit endVisit(T component, HydrationContext context) throws HydrationException;
}
