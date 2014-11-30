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
package org.hobsoft.symmetry.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.hobsoft.symmetry.Reflector;
import org.hobsoft.symmetry.ReflectorException;

/**
 * JSP tag that serialises a component.
 */
public class ComponentTag extends SimpleTagSupport
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private String name;
	
	private String reflectorName;

	// ----------------------------------------------------------------------------------------------------------------
	// SimpleTag methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public void doTag() throws JspException, IOException
	{
		JspContext context = getJspContext();
		
		Object component = context.getAttribute(name);
		Reflector<Object> reflector = (Reflector<Object>) context.getAttribute(reflectorName);
		
		try
		{
			reflector.reflect(component, context.getOut());
		}
		catch (ReflectorException exception)
		{
			throw new JspException("Error writing component", exception);
		}
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getReflectorName()
	{
		return reflectorName;
	}
	
	public void setReflectorName(String reflectorName)
	{
		this.reflectorName = reflectorName;
	}
}
