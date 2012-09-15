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

import java.util.Iterator;

/**
 * An abstract compound DTD element content model whose comprising content models are mutually-exclusive.
 * 
 * @author Mark Hobson
 */
public abstract class DTDChoiceContainer extends DTDContentContainer
{
	// constructors -----------------------------------------------------------
	
	public DTDChoiceContainer()
	{
		super();
	}
	
	public DTDChoiceContainer(DTDCardinality cardinality)
	{
		super(cardinality);
	}
	
	// DTDCardinalContent methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int validateOnce(int index, String... elements)
	{
		int returnIndex = -1;
		
		Iterator<DTDContent> children = iterator();
		
		while (returnIndex == -1 && children.hasNext())
		{
			DTDContent child = children.next();
			
			int childIndex = child.validate(index, elements);
			
			if (childIndex != -1)
			{
				returnIndex = childIndex;
			}
		}
		
		return returnIndex;
	}

	// DTDContentContainer methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getSeparator()
	{
		return "|";
	}
}
