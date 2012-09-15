/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/AbstractRenderKit.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractRenderKit.java 95658 2011-11-29 15:18:50Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the base component type this kit can render
 */
public abstract class AbstractRenderKit<T> implements ComponentRenderKit<T>
{
	// TODO: pull out phased and bean state functionality
	
	// constants --------------------------------------------------------------
	
	private static final HydrationPhase[] DEHYDRATION_PHASES = new HydrationPhase[] {
		HydrationPhase.DEHYDRATE_INITIALIZE,
		HydrationPhase.DEHYDRATE,
	};
	
	private static final HydrationPhase[] REHYDRATION_PHASES = new HydrationPhase[] {
		HydrationPhase.REHYDRATE_PROPERTIES,
		HydrationPhase.REHYDRATE_PARAMETERS,
		HydrationPhase.REHYDRATE_EVENTS,
	};
	
	// fields -----------------------------------------------------------------
	
	private final Class<T> componentType;
	
	private final String contentType;
	
	// constructors -----------------------------------------------------------
	
	public AbstractRenderKit(Class<T> componentType, String contentType)
	{
		this.componentType = componentType;
		this.contentType = contentType;
	}

	// ComponentRenderKit methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Class<T> getComponentType()
	{
		return componentType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getContentType()
	{
		return contentType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void dehydrate(T component, DehydrationContext context) throws HydrationException
	{
		preDehydrate(component, context);
		
		for (HydrationPhase phase : DEHYDRATION_PHASES)
		{
			context.set(HydrationPhase.class, phase);
			
			dehydrateImpl(component, context);
		}
		
		postDehydrate(component, context);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void rehydrate(T component, RehydrationContext context) throws HydrationException
	{
		preRehydrate(component, context);
		
		for (HydrationPhase phase : REHYDRATION_PHASES)
		{
			context.set(HydrationPhase.class, phase);
			
			rehydrateImpl(component, context);
		}
		
		postRehydrate(component, context);
	}

	// protected methods ------------------------------------------------------
	
	protected final String getCharset()
	{
		return MediaTypes.getParameterValue(contentType, "charset");
	}
	
	protected void preDehydrate(T component, DehydrationContext context) throws HydrationException
	{
		StateCodec stateCodec = createStateCodec(component, context);
		context.set(StateCodec.class, stateCodec);
	}
	
	protected void postDehydrate(T component, DehydrationContext context) throws HydrationException
	{
		// no-op
	}
	
	protected void preRehydrate(T component, RehydrationContext context) throws HydrationException
	{
		StateCodec stateCodec = createStateCodec(component, context);
		context.set(StateCodec.class, stateCodec);
		
		EncodedState encodedState = context.getEncodedState();
		State decodedState;
		try
		{
			decodedState = stateCodec.decodeState(encodedState);
		}
		catch (StateException exception)
		{
			throw new HydrationException(exception);
		}
		context.set(State.class, decodedState);
	}
	
	protected void postRehydrate(T component, RehydrationContext context) throws HydrationException
	{
		// no-op
	}
	
	protected abstract StateCodec createStateCodec(T component, HydrationContext context);
	
	protected abstract void dehydrateImpl(T component, DehydrationContext context) throws HydrationException;
	
	protected abstract void rehydrateImpl(T component, RehydrationContext context) throws HydrationException;
}
