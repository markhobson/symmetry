/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/HierarchicalComponentHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
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
