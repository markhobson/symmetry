/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/state/FakeBean.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: FakeBean.java 98109 2012-01-27 13:04:55Z mark@IIZUKA.CO.UK $
 */
public class FakeBean
{
	// fields -----------------------------------------------------------------
	
	private boolean booleanPrimitive;
	
	private int intPrimitive;
	
	private String string;

	// constructors -----------------------------------------------------------
	
	public FakeBean()
	{
		// for JavaBean
	}
	
	// public methods ---------------------------------------------------------
	
	public boolean isBooleanPrimitive()
	{
		return booleanPrimitive;
	}
	
	public void setBooleanPrimitive(boolean booleanPrimitive)
	{
		this.booleanPrimitive = booleanPrimitive;
	}
	
	public int getIntPrimitive()
	{
		return intPrimitive;
	}
	
	public void setIntPrimitive(int intPrimitive)
	{
		this.intPrimitive = intPrimitive;
	}
	
	public String getString()
	{
		return string;
	}
	
	public void setString(String string)
	{
		this.string = string;
	}
}
