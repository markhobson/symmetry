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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An abstract compound DTD element content model that consists of a number of other content models.
 * 
 * @author Mark Hobson
 */
public abstract class DTDContentContainer extends DTDCardinalContent implements Iterable<DTDContent>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_SEPARATOR = ",";
	
	// fields -----------------------------------------------------------------
	
	private final List<DTDContent> children;
	
	// constructors -----------------------------------------------------------
	
	public DTDContentContainer()
	{
		this(DTDCardinality.ONCE);
	}
	
	public DTDContentContainer(DTDCardinality cardinality)
	{
		super(cardinality);
		
		children = new ArrayList<DTDContent>();
	}
	
	// Iterable methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<DTDContent> iterator()
	{
		return getChildren().iterator();
	}
	
	// public methods ---------------------------------------------------------
	
	public void addChild(DTDContent child)
	{
		children.add(child);
	}
	
	public void removeChild(DTDContent child)
	{
		children.remove(child);
	}
	
	public DTDContent getChild(int index)
	{
		return children.get(index);
	}
	
	public int getChildCount()
	{
		return children.size();
	}
	
	public List<DTDContent> getChildren()
	{
		return Collections.unmodifiableList(children);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode() * 31 + children.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDContentContainer))
		{
			return false;
		}
		
		DTDContentContainer contentContainer = (DTDContentContainer) object;
		
		return super.equals(object) && children.equals(contentContainer.children);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("(");
		appendChildren(builder);
		builder.append(")");
		builder.append(getCardinality());
		
		return builder.toString();
	}
	
	// protected methods ------------------------------------------------------
	
	protected void appendChildren(StringBuilder builder)
	{
		for (int i = 0; i < getChildCount(); i++)
		{
			if (i > 0)
			{
				builder.append(getSeparator());
			}
			
			builder.append(getChild(i));
		}
	}
	
	protected String getSeparator()
	{
		return DEFAULT_SEPARATOR;
	}
}