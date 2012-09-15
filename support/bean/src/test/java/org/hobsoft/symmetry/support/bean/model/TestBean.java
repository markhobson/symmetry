/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/model/TestBean.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.model;

import javax.swing.event.EventListenerList;

import org.junit.Ignore;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TestBean.java 86783 2011-04-11 18:18:45Z mark@IIZUKA.CO.UK $
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
