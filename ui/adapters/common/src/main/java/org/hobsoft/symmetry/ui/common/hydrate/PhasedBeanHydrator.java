/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedBeanHydrator.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.ui.Component;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_EVENTS;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators.beanEvents;
import static org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators.beanProperties;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedBeanHydrator<T extends Component> extends PhasedHierarchicalComponentHydrator<T>
{
	// constructors -----------------------------------------------------------

	public PhasedBeanHydrator()
	{
		setDelegate(REHYDRATE_PROPERTIES, beanProperties());
		setDelegate(REHYDRATE_EVENTS, beanEvents());
	}
}
