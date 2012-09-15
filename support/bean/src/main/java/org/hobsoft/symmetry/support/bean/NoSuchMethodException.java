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
package org.hobsoft.symmetry.support.bean;

import java.beans.EventSetDescriptor;

/**
 * Indicates that a named method could not be found.
 * 
 * @author Mark Hobson
 */
public final class NoSuchMethodException extends BeanException
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = -4902537550270320721L;

	// fields -----------------------------------------------------------------
	
	private final EventSetDescriptor descriptor;
	
	private final String methodName;

	// constructors -----------------------------------------------------------
	
	public NoSuchMethodException(EventSetDescriptor descriptor, String methodName)
	{
		this.descriptor = descriptor;
		this.methodName = methodName;
	}
	
	// Throwable methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage()
	{
		return getEventSetDescriptor().getName() + "." + getMethodName();
	}
	
	// public methods ---------------------------------------------------------
	
	public EventSetDescriptor getEventSetDescriptor()
	{
		return descriptor;
	}
	
	public String getMethodName()
	{
		return methodName;
	}
}
