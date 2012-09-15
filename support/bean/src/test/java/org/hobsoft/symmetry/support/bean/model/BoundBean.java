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
package org.hobsoft.symmetry.support.bean.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class BoundBean
{
	// fields -----------------------------------------------------------------
	
	private PropertyChangeSupport support;
	
	private String foo;
	
	private String bar;
	
	private int baz;
	
	// constructors -----------------------------------------------------------
	
	public BoundBean()
	{
		support = new PropertyChangeSupport(this);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(listener);
	}
	
	public PropertyChangeListener[] getPropertyChangeListeners()
	{
		return support.getPropertyChangeListeners();
	}
	
	public PropertyChangeListener[] getPropertyChangeListeners(String propertyName)
	{
		return support.getPropertyChangeListeners(propertyName);
	}
	
	public String getFoo()
	{
		return foo;
	}
	
	public void setFoo(String foo)
	{
		String oldFoo = this.foo;
		this.foo = foo;
		support.firePropertyChange("foo", oldFoo, foo);
	}
	
	public String getBar()
	{
		return bar;
	}
	
	public void setBar(String bar)
	{
		String oldBar = this.bar;
		this.bar = bar;
		support.firePropertyChange("bar", oldBar, bar);
	}
	
	public int getBaz()
	{
		return baz;
	}
	
	public void setBaz(int baz)
	{
		int oldBaz = this.baz;
		this.baz = baz;
		support.firePropertyChange("baz", oldBaz, baz);
	}
}