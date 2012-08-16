/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common-test-support/src/main/java/uk/co/iizuka/kozo/ui/common/test/hydrate/StubPhasedUiComponentRenderKit.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.test.hydrate;

import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.common.hydrate.HierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitors;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StubPhasedUiComponentRenderKit.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the base component type this kit can render
 */
public class StubPhasedUiComponentRenderKit<T extends Component> extends StubUiComponentRenderKit<T>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_CONTENT_TYPE = "text/plain";
	
	private static final HydrationPhase[] DEHYDRATION_PHASES = new HydrationPhase[] {
		HydrationPhase.DEHYDRATE_INITIALIZE,
		HydrationPhase.DEHYDRATE,
	};
	
	private static final HydrationPhase[] REHYDRATION_PHASES = new HydrationPhase[] {
		HydrationPhase.REHYDRATE_PROPERTIES,
		HydrationPhase.REHYDRATE_PARAMETERS,
		HydrationPhase.REHYDRATE_EVENTS,
	};
	
	// constructors -----------------------------------------------------------
	
	public StubPhasedUiComponentRenderKit(Class<T> componentType, String contentType,
		HierarchicalComponentHydrator<T> subhydrator)
	{
		this(componentType, contentType, ComponentVisitors.adapt(componentType, subhydrator));
	}
	
	public StubPhasedUiComponentRenderKit(Class<T> componentType, String contentType,
		ComponentVisitor<HydrationContext, HydrationException> hydrator)
	{
		super(componentType, contentType, hydrator);
	}
	
	// ComponentRenderKit methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dehydrate(T component, DehydrationContext context) throws HydrationException
	{
		for (HydrationPhase phase : DEHYDRATION_PHASES)
		{
			context.set(HydrationPhase.class, phase);
			
			super.dehydrate(component, context);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rehydrate(T component, RehydrationContext context) throws HydrationException
	{
		for (HydrationPhase phase : REHYDRATION_PHASES)
		{
			context.set(HydrationPhase.class, phase);
			
			super.rehydrate(component, context);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends Component> StubPhasedUiComponentRenderKit<T> get(Class<T> componentType,
		HierarchicalComponentHydrator<T> subhydrator)
	{
		return new StubPhasedUiComponentRenderKit<T>(componentType, DEFAULT_CONTENT_TYPE, subhydrator);
	}
	
	public static <T extends Component> StubPhasedUiComponentRenderKit<T> get(Class<T> componentType,
		ComponentVisitor<HydrationContext, HydrationException> hydrator)
	{
		return new StubPhasedUiComponentRenderKit<T>(componentType, DEFAULT_CONTENT_TYPE, hydrator);
	}
}
