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
 * A compound DTD element content model whose comprising content models must occur sequentially.
 * 
 * @author Mark Hobson
 */
public class DTDSequenceContent extends DTDContentContainer
{
	// constructors -----------------------------------------------------------
	
	public DTDSequenceContent()
	{
		super();
	}
	
	public DTDSequenceContent(DTDCardinality cardinality)
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
		if (getChildCount() == 0)
		{
			return -1;
		}
		
		Iterator<DTDContent> children = iterator();
		
		while (index != -1 && children.hasNext())
		{
			DTDContent child = children.next();
			
			index = child.validate(index, elements);
		}
		
		return index;
	}
	
	// DTDContentContainer methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getSeparator()
	{
		return ",";
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
		if (!(object instanceof DTDSequenceContent))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
