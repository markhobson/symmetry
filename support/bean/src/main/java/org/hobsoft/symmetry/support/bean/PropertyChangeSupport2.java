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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

/**
 * Provides support for implementing bound JavaBeans.
 * <p>
 * This class differs from {@code PropertyChangeSupport} in that property change events are not fired when old and new
 * values are both {@code null}.
 * 
 * @author Mark Hobson
 * @see PropertyChangeSupport
 * @see <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4763463">Bug 4763463</a>
 */
public class PropertyChangeSupport2 extends PropertyChangeSupport
{
	// constructors -----------------------------------------------------------
	
	public PropertyChangeSupport2(Object sourceBean)
	{
		super(sourceBean);
	}

	// PropertyChangeSupport methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void firePropertyChange(PropertyChangeEvent event)
	{
		if (event.getOldValue() != null || event.getNewValue() != null)
		{
			super.firePropertyChange(event);
		}
	}
}
