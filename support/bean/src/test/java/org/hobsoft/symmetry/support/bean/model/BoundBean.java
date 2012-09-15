/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/model/BoundBean.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: BoundBean.java 86812 2011-04-12 09:01:17Z mark@IIZUKA.CO.UK $
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
