/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedBeanHydrator.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_EVENTS;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators.beanEvents;
import static uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators.beanProperties;

import uk.co.iizuka.kozo.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PhasedBeanHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
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
