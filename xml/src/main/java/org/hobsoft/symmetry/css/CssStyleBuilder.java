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
package org.hobsoft.symmetry.css;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class CssStyleBuilder
{
	// fields -----------------------------------------------------------------
	
	private final StringBuilder builder;
	
	// constructors -----------------------------------------------------------
	
	public CssStyleBuilder()
	{
		this(new StringBuilder());
	}
	
	public CssStyleBuilder(StringBuilder builder)
	{
		if (builder == null)
		{
			throw new IllegalArgumentException("builder cannot be null");
		}
		
		this.builder = builder;
	}
	
	// public methods ---------------------------------------------------------
	
	public CssStyleBuilder append(Css.Property property, Css.Value value)
	{
		append(property, value.toCss());
		
		return this;
	}
	
	public CssStyleBuilder append(Css.Property property, int value, Css.Unit unit)
	{
		append(property, Integer.toString(value));
		builder.append(unit.toCss());
		
		return this;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return builder.toString();
	}
	
	// private methods --------------------------------------------------------
	
	private CssStyleBuilder append(Css.Property property, String value)
	{
		if (builder.length() > 0)
		{
			builder.append("; ");
		}
		
		builder.append(property.toCss());
		builder.append(": ");
		builder.append(value);
		
		return this;
	}
}
