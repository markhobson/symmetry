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

import javax.swing.event.EventListenerList;

import org.junit.Ignore;

/**
 * 
 * 
 * @author Mark Hobson
 */
// TODO: remove @Ignore when Maven no longer tries to run this as a test
@Ignore
public class TestBean
{
	// constants --------------------------------------------------------------
	
	public static final String FOO_EVENT = "foo";

	public static final String FOO_PROPERTY = "foo";
	
	public static final String FOO_BOOLEAN_PROPERTY = "fooBoolean";
	
	public static final String FOO_FLUENT_PROPERTY = "fooFluent";
	
	public static final String FOO_READ_ONLY_PROPERTY = "fooReadOnly";
	
	public static final String FOO_WRITE_ONLY_PROPERTY = "fooWriteOnly";
	
	public static final String FOO_FLUENT_WRITE_ONLY_PROPERTY = "fooFluentWriteOnly";
	
	// fields -----------------------------------------------------------------
	
	private final EventListenerList listeners;
	
	private String foo;
	
	private boolean fooBoolean;
	
	private String fooFluent;
	
	// constructors -----------------------------------------------------------
	
	public TestBean()
	{
		listeners = new EventListenerList();
	}
	
	// event methods ----------------------------------------------------------
	
	public void addFooListener(FooListener listener)
	{
		listeners.add(FooListener.class, listener);
	}
	
	public void removeFooListener(FooListener listener)
	{
		listeners.remove(FooListener.class, listener);
	}
	
	public FooListener[] getFooListeners()
	{
		return listeners.getListeners(FooListener.class);
	}
	
	public int getFooListenerCount()
	{
		return listeners.getListenerCount(FooListener.class);
	}
	
	// property methods -------------------------------------------------------
	
	public String getFoo()
	{
		return foo;
	}
	
	public void setFoo(String foo)
	{
		this.foo = foo;
	}
	
	// boolean property methods -----------------------------------------------
	
	public boolean isFooBoolean()
	{
		return fooBoolean;
	}
	
	public void setFooBoolean(boolean fooBoolean)
	{
		this.fooBoolean = fooBoolean;
	}
	
	// fluent property methods ------------------------------------------------
	
	public String getFooFluent()
	{
		return fooFluent;
	}
	
	public TestBean setFooFluent(String fooFluent)
	{
		this.fooFluent = fooFluent;
		
		return this;
	}
	
	// read-only property methods ---------------------------------------------
	
	public String getFooReadOnly()
	{
		return "foo";
	}
	
	// write-only property methods --------------------------------------------
	
	@SuppressWarnings("unused")
	public void setFooWriteOnly(String fooWriteOnly)
	{
		// no-op
	}
	
	// fluent write-only property methods --------------------------------------
	
	@SuppressWarnings("unused")
	public TestBean setFooFluentWriteOnly(String fooFluentWriteOnly)
	{
		// no-op
		
		return this;
	}
	
	// invalid property methods -----------------------------------------------
	
	public String getFooReadOnlyArg(String arg)
	{
		return arg;
	}
	
	public void getFooReadOnlyVoid()
	{
		// no-op
	}
	
	@SuppressWarnings("unused")
	public void setFooWriteOnlyArg(String fooWriteOnlyArg, String arg)
	{
		// no-op
	}
	
	@SuppressWarnings("unused")
	public String setFooFluentWriteOnlyReturn(String fooFluentWriteOnlyReturn)
	{
		return "foo";
	}
}
