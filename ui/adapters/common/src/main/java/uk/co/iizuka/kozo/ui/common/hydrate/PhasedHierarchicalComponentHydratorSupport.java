/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedHierarchicalComponentHydratorSupport.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.hydrate;

import java.util.HashMap;
import java.util.Map;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PhasedHierarchicalComponentHydratorSupport.java 98885 2012-02-29 17:37:56Z mark@IIZUKA.CO.UK $
 * @param <V>
 *            the visitor type
 */
public class PhasedHierarchicalComponentHydratorSupport<V extends HierarchicalComponentVisitor<?, HydrationContext,
	HydrationException>>
{
	// TODO: move default delegate functionality out of here without complicating phased visitor implementations
	
	// fields -----------------------------------------------------------------
	
	private final V defaultDelegate;
	
	private final Map<HydrationPhase, V> delegatesByPhase;

	// constructors -----------------------------------------------------------
	
	public PhasedHierarchicalComponentHydratorSupport(V defaultDelegate)
	{
		this.defaultDelegate = defaultDelegate;
		
		delegatesByPhase = new HashMap<HydrationPhase, V>();
	}
	
	// public methods ---------------------------------------------------------
	
	public V getDelegate(HydrationContext context)
	{
		HydrationPhase phase = context.get(HydrationPhase.class);

		return getDelegate(phase);
	}
	
	public V getDelegate(HydrationPhase phase)
	{
		V delegate = delegatesByPhase.get(phase);
		
		return (delegate != null) ? delegate : defaultDelegate;
	}
	
	public void setDelegate(HydrationPhase phase, V delegate)
	{
		if (phase == null)
		{
			throw new NullPointerException("phase cannot be null");
		}
		
		if (delegate == null)
		{
			delegatesByPhase.remove(phase);
		}
		else
		{
			delegatesByPhase.put(phase, delegate);
		}
	}
}
