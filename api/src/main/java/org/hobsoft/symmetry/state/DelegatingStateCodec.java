/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
