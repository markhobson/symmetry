/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/test/java/uk/co/iizuka/kozo/state/DummyBean.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.state;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyBean.java 98171 2012-01-30 19:46:18Z mark@IIZUKA.CO.UK $
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
