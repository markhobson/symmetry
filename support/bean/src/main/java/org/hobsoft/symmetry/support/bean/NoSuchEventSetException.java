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

import java.beans.BeanInfo;

/**
 * Indicates that a named event set could not be found.
 * 
 * @author Mark Hobson
 */
public final class NoSuchEventSetException extends BeanException
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 355842021918911446L;

	// fields -----------------------------------------------------------------
	
	private final BeanInfo beanInfo;
	
	private final String eventSetName;

	// constructors -----------------------------------------------------------
	
	public NoSuchEventSetException(BeanInfo beanInfo, String eventSetName)
	{
		this.beanInfo = beanInfo;
		this.eventSetName = eventSetName;
	}
	
	// Throwable methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage()
	{
		return getBeanInfo().getBeanDescriptor().getName() + "." + getEventSetName();
	}
	
	// public methods ---------------------------------------------------------
	
	public BeanInfo getBeanInfo()
	{
		return beanInfo;
	}
	
	public String getEventSetName()
	{
		return eventSetName;
	}
}
