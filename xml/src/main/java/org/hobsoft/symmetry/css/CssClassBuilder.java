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
public class CssClassBuilder
{
	// fields -----------------------------------------------------------------
	
	private final StringBuilder builder;
	
	// constructors -----------------------------------------------------------
	
	public CssClassBuilder()
	{
		this(new StringBuilder());
	}
	
	public CssClassBuilder(StringBuilder builder)
	{
		if (builder == null)
		{
			throw new IllegalArgumentException("builder cannot be null");
		}
		
		this.builder = builder;
	}
	
	public CssClassBuilder(String cssClass)
	{
		this();
		
		append(cssClass);
	}
	
	// public methods ---------------------------------------------------------
	
	public CssClassBuilder append(String cssClass)
	{
		if (builder.length() > 0)
		{
			builder.append(' ');
		}
		
		builder.append(cssClass);
		
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
}
