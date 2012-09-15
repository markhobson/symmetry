/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/test/java/uk/co/iizuka/kozo/ui/common/FakeBean.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: FakeBean.java 98421 2012-02-13 16:38:55Z mark@IIZUKA.CO.UK $
 */
public class FakeBean
{
	// fields -----------------------------------------------------------------

	private String name;
	
	private String[] nickNames;
	
	private Object data;
	
	// public methods ---------------------------------------------------------

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String[] getNickNames()
	{
		return nickNames;
	}
	
	public void setNickNames(String[] nickNames)
	{
		this.nickNames = nickNames;
	}
	
	public String getNickNames(int index)
	{
		return nickNames[index];
	}
	
	public void setNickNames(int index, String nickName)
	{
		nickNames[index] = nickName;
	}
	
	public Object getData()
	{
		return data;
	}
	
	public void setData(Object data)
	{
		this.data = data;
	}
}
