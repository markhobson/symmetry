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
public class NullStateCodec implements StateCodec
{
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EncodedState encodeState(State state) throws StateException
	{
		return new EncodedState();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public State decodeState(EncodedState encodedState) throws StateException
	{
		return new State();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodeBean(Object bean) throws StateException
	{
		return "";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodeBean(String encodedBean) throws StateException
	{
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encodePropertyValue(PropertyState property) throws StateException
	{
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue)
		throws StateException
	{
		return null;
	}
}
