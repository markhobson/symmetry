/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/model/Bean.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.model;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Bean.java 97397 2011-12-30 11:53:48Z mark@IIZUKA.CO.UK $
 */
public class Bean
{
	// fields -----------------------------------------------------------------
	
	private String foo;
	
	private String bar;
	
	private int baz;
	
	// constructors -----------------------------------------------------------
	
	public Bean()
	{
		// for JavaBean
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
}
