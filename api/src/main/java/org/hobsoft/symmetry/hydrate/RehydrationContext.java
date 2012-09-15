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
package org.hobsoft.symmetry.hydrate;

import org.hobsoft.symmetry.state.EncodedState;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class RehydrationContext extends HydrationContext
{
	// TODO: fold into HydrationContext and remove
	// TODO: should encoded state be mutable?
	
	// fields -----------------------------------------------------------------
	
	private EncodedState encodedState;
	
	// constructors -----------------------------------------------------------
	
	public RehydrationContext()
	{
		this.encodedState = new EncodedState();
	}
	
	// public methods ---------------------------------------------------------
	
	public EncodedState getEncodedState()
	{
		return encodedState;
	}
	
	public void setEncodedState(EncodedState encodedState)
	{
		this.encodedState = encodedState;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = super.hashCode();
		hashCode = (hashCode * 31) + encodedState.hashCode();
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof RehydrationContext))
		{
			return false;
		}
		
		RehydrationContext context = (RehydrationContext) object;
		
		return super.equals(context)
			&& encodedState.equals(context.getEncodedState());
	}
}
