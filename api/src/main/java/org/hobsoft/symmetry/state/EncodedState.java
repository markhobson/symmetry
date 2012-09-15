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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class EncodedState
{
	// TODO: should this be mutable for ease of use?
	
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_STATE = "";
	
	private static final Map<String, List<Object>> DEFAULT_PARAMETERS = Collections.emptyMap();

	// fields -----------------------------------------------------------------
	
	private final String state;
	
	private final Map<String, List<Object>> parameters;
	
	// constructors -----------------------------------------------------------
	
	public EncodedState()
	{
		this(DEFAULT_STATE);
	}
	
	public EncodedState(String state)
	{
		this(state, DEFAULT_PARAMETERS);
	}
	
	public EncodedState(Map<String, List<Object>> parameters)
	{
		this(DEFAULT_STATE, parameters);
	}
	
	public EncodedState(String state, Map<String, List<Object>> parameters)
	{
		this.state = state;
		this.parameters = parameters;
	}
	
	public EncodedState(String state, EncodedState parameters)
	{
		this(state, parameters.parameters);
	}
	
	// public methods ---------------------------------------------------------
	
	public String getState()
	{
		return state;
	}
	
	public Set<String> getParameterNames()
	{
		return parameters.keySet();
	}
	
	public Object getParameterValue(String parameterName)
	{
		List<Object> parameterValues = getParameterValues(parameterName);
		
		Object parameterValue;
		
		if (parameterValues.isEmpty())
		{
			parameterValue = null;
		}
		else if (parameterValues.size() == 1)
		{
			parameterValue = parameterValues.get(0);
		}
		else
		{
			throw new IllegalArgumentException(String.format("Parameter '%s' is multi-valued: %s", parameterName,
				parameterValues));
		}
		
		return parameterValue;
	}
	
	public List<Object> getParameterValues(String parameterName)
	{
		List<Object> parameterValues = parameters.get(parameterName);
		
		if (parameterValues == null)
		{
			parameterValues = Collections.emptyList();
		}
		
		return parameterValues;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = state.hashCode();
		hashCode = (hashCode * 31) + parameters.hashCode();
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof EncodedState))
		{
			return false;
		}
		
		EncodedState encodedState = (EncodedState) object;
		
		return state.equals(encodedState.getState())
			&& parameters.equals(encodedState.parameters);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(getClass().getName()).append("[");
		builder.append("state=").append(state).append(", ");
		builder.append("parameters=").append(parameters).append("]");
		
		return builder.toString();
	}
}
