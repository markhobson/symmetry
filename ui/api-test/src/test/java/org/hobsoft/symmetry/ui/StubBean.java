/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/StubBean.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StubBean.java 86967 2011-04-19 08:50:19Z mark@IIZUKA.CO.UK $
 */
public class StubBean
{
	// fields -----------------------------------------------------------------
	
	private String name;

	// constructors -----------------------------------------------------------
	
	public StubBean()
	{
		// default constructor
	}
	
	// public methods ---------------------------------------------------------
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
