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
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * A compound DTD element content model whose comprising content models are mutually-exclusive.
 * 
 * @author Mark Hobson
 */
public class DTDChoiceContent extends DTDChoiceContainer
{
	// constructors -----------------------------------------------------------
	
	public DTDChoiceContent()
	{
		super();
	}
	
	public DTDChoiceContent(DTDCardinality cardinality)
	{
		super(cardinality);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDChoiceContent))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
