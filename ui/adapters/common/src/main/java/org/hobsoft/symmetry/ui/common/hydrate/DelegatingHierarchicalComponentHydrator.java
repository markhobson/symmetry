/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/DelegatingHierarchicalComponentHydrator.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.DelegatingHierarchicalComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;

/**
 * Convenience class that adapts a {@code DelegatingHierarchicalComponentVisitor} to a
 * {@code HierarchicalComponentHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingHierarchicalComponentHydrator.java 98863 2012-02-29 10:48:53Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
public abstract class DelegatingHierarchicalComponentHydrator<T extends Component>
	extends DelegatingHierarchicalComponentVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingHierarchicalComponentHydrator(
		HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		super(delegate);
	}
}
