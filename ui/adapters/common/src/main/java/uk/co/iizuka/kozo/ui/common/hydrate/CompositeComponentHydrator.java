/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/CompositeComponentHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.hydrate;

import java.util.Collection;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.traversal.CompositeComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;

/**
 * Convenience class that adapts a {@code CompositeComponentVisitor} to a {@code ComponentHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: CompositeComponentHydrator.java 98863 2012-02-29 10:48:53Z mark@IIZUKA.CO.UK $
 */
public class CompositeComponentHydrator
	extends CompositeComponentVisitor<HydrationContext, HydrationException>
	implements ComponentHydrator
{
	// simple subtype
	
	// constructors -----------------------------------------------------------
	
	public CompositeComponentHydrator()
	{
		super();
	}
	
	// TODO: should be Collection<? extends HierarchicalComponentVisitor<? extends Component, HydrationContext,
	// HydrationException>> when CCONTAINER-57 fixed
	public CompositeComponentHydrator(Collection<HierarchicalComponentVisitor<? extends Component,
		HydrationContext, HydrationException>> delegates)
	{
		super(delegates);
	}
}
