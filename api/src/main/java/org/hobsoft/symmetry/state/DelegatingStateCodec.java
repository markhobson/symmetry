/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/state/DelegatingStateCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyDescriptor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class DelegatingStateCodec implements StateCodec
{
	// fields -----------------------------------------------------------------
	
	private final StateCodec stateCodec;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingStateCodec(StateCodec stateCodec)
	{
		this.stateCodec = stateCodec;
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EncodedState encodeState(State state) throws StateException
	{
		return getStateCodec().encodeState(state);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public State decodeState(EncodedState encodedState) throws StateException
	{
		return getStateCodec().decodeState(encodedState);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodeBean(Object bean) throws StateException
	{
		return getStateCodec().encodeBean(bean);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodeBean(String encodedBean) throws StateException
	{
		return getStateCodec().decodeBean(encodedBean);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodePropertyValue(PropertyState property) throws StateException
	{
		return getStateCodec().encodePropertyValue(property);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue)
		throws StateException
	{
		return getStateCodec().decodePropertyValue(bean, descriptor, encodedValue);
	}
	
	// public methods ---------------------------------------------------------
	
	public StateCodec getStateCodec()
	{
		return stateCodec;
	}
}
