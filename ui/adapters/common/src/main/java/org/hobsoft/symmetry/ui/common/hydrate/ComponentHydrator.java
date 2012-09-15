/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/ComponentHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;

import com.googlecode.jtype.Generic;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComponentHydrator.java 95595 2011-11-28 12:38:59Z mark@IIZUKA.CO.UK $
 */
public interface ComponentHydrator extends ComponentVisitor<HydrationContext, HydrationException>
{
	// ComponentVisitor methods -----------------------------------------------

	/**
	 * {@inheritDoc}
	 * 
	 * Overridden to rename argument.
	 */
	@Override
	<T extends Component> HierarchicalComponentVisitor<T, HydrationContext, HydrationException> visit(
		Generic<T> componentType, T component, HydrationContext context) throws HydrationException;
}
