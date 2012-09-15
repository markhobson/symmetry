/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/state/FakeBean.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: FakeBean.java 101083 2012-05-04 14:50:47Z mark@IIZUKA.CO.UK $
 */
public class FakeBean
{
	// fields -----------------------------------------------------------------
	
	private boolean booleanPrimitive;
	
	private int integerPrimitive;
	
	private Boolean booleanObject;
	
	private Integer integer;
	
	private String string;
	
	private int[] integerPrimitiveArray;
	
	private Integer[] integerArray;

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
	
	public int getIntegerPrimitive()
	{
		return integerPrimitive;
	}
	
	public void setIntegerPrimitive(int integerPrimitive)
	{
		this.integerPrimitive = integerPrimitive;
	}
	
	public Boolean getBoolean()
	{
		return booleanObject;
	}
	
	public void setBoolean(Boolean booleanObject)
	{
		this.booleanObject = booleanObject;
	}
	
	public Integer getInteger()
	{
		return integer;
	}
	
	public void setInteger(Integer integer)
	{
		this.integer = integer;
	}
	
	public String getString()
	{
		return string;
	}
	
	public void setString(String string)
	{
		this.string = string;
	}
	
	public int[] getIntegerPrimitiveArray()
	{
		return integerPrimitiveArray;
	}
	
	public void setIntegerPrimitiveArray(int[] integerPrimitiveArray)
	{
		this.integerPrimitiveArray = integerPrimitiveArray;
	}
	
	public Integer[] getIntegerArray()
	{
		return integerArray;
	}
	
	public void setIntegerArray(Integer[] integerArray)
	{
		this.integerArray = integerArray;
	}
}
