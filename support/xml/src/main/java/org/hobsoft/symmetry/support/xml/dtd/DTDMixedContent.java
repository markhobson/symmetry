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
 * A compound DTD element content model whose comprising content models are mutually-exclusive together with character
 * data.
 * 
 * @author Mark Hobson
 */
public class DTDMixedContent extends DTDChoiceContainer
{
	// constructors -----------------------------------------------------------
	
	public DTDMixedContent()
	{
		super();
	}
	
	public DTDMixedContent(DTDCardinality cardinality)
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
		boolean pcdata = false;
		
		// skip any initial #PCDATA elements
		while (index < elements.length && elements[index] == null)
		{
			index++;
			pcdata = true;
		}
		
		// validate as per normal
		int nextIndex = super.validateOnce(index, elements);
		
		// if invalid but initial #PCDATA found then ensure valid
		if (nextIndex != -1 || !pcdata)
		{
			index = nextIndex;
		}
		
		// skip any trailing #PCDATA elements
		if (index != -1)
		{
			while (index < elements.length && elements[index] == null)
			{
				index++;
			}
		}
		
		return index;
	}
	
	// DTDContentContainer methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void appendChildren(StringBuilder builder)
	{
		builder.append("#PCDATA");
		
		if (getChildCount() > 0)
		{
			builder.append(getSeparator());
		}
		
		super.appendChildren(builder);
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
		if (!(object instanceof DTDMixedContent))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
