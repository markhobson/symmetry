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
package org.hobsoft.symmetry.ui.html.state;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.state.PropertyValueStateCodec;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;

/**
 * Codec for checkbox controls in HTML form data sets.
 * 
 * @author Mark Hobson
 */
public class HtmlCheckboxControlStateCodec extends PropertyValueStateCodec<Boolean>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_ON_VALUE = "on";
	
	// fields -----------------------------------------------------------------
	
	private final String onValue;
	
	// constructors -----------------------------------------------------------
	
	public HtmlCheckboxControlStateCodec(StateCodec delegate, PropertyDescriptor descriptor)
	{
		this(delegate, descriptor, DEFAULT_ON_VALUE);
	}
	
	public HtmlCheckboxControlStateCodec(StateCodec delegate, PropertyDescriptor descriptor, String onValue)
	{
		super(delegate, descriptor, Boolean.class);
		
		this.onValue = onValue;
	}
	
	// PropertyValueStateCodec methods ----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String encodePropertyValue(Object bean, Boolean value) throws StateException
	{
		return Boolean.TRUE.equals(value) ? onValue : null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Boolean decodePropertyValue(Object bean, String encodedValue) throws StateException
	{
		return onValue.equals(encodedValue);
	}
}
