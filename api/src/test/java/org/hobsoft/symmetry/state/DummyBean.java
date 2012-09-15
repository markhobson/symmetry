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
package org.hobsoft.symmetry.state;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DummyBean
{
	// types ------------------------------------------------------------------
	
	/**
	 * Dummy listener.
	 */
	public interface FooListener extends EventListener
	{
		// dummy listener
	}

	/**
	 * Dummy listener.
	 */
	public interface BarListener extends EventListener
	{
		// dummy listener
	}
	
	// fields -----------------------------------------------------------------
	
	private String foo;
	
	private String bar;
	
	private int baz;
	
	private String[] foos;
	
	private String[] bars;
	
	private int[] bazs;
	
	private final EventListenerList listeners;

	// constructors -----------------------------------------------------------
	
	public DummyBean()
	{
		listeners = new EventListenerList();
	}
	
	// public methods ---------------------------------------------------------
	
	public String getFoo()
	{
		return foo;
	}
	
	public void setFoo(String foo)
	{
		this.foo = foo;
	}
	
	public String getBar()
	{
		return bar;
	}

	public void setBar(String bar)
	{
		this.bar = bar;
	}
	
	public int getBaz()
	{
		return baz;
	}
	
	public void setBaz(int baz)
	{
		this.baz = baz;
	}
	
	public String[] getFoos()
	{
		return foos;
	}
	
	public void setFoos(String[] foos)
	{
		this.foos = foos;
	}
	
	public String getFoos(int index)
	{
		return foos[index];
	}
	
	public void setFoos(int index, String foo)
	{
		foos[index] = foo;
	}
	
	public String[] getBars()
	{
		return bars;
	}
	
	public void setBars(String[] bars)
	{
		this.bars = bars;
	}

	public String getBars(int index)
	{
		return bars[index];
	}
	
	public void setBars(int index, String bar)
	{
		bars[index] = bar;
	}
	
	public int[] getBazs()
	{
		return bazs;
	}
	
	public void setBazs(int[] bazs)
	{
		this.bazs = bazs;
	}
	
	public int getBazs(int index)
	{
		return bazs[index];
	}
	
	public void setBazs(int index, int baz)
	{
		bazs[index] = baz;
	}
	
	public void addFooListener(FooListener listener)
	{
		listeners.add(FooListener.class, listener);
	}
	
	public void removeFooListener(FooListener listener)
	{
		listeners.remove(FooListener.class, listener);
	}
	
	public void addBarListener(BarListener listener)
	{
		listeners.add(BarListener.class, listener);
	}
	
	public void removeBarListener(BarListener listener)
	{
		listeners.remove(BarListener.class, listener);
	}
}
