/*
 * $HeadURL: https://svn.iizuka.co.uk/common/test/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/test/DummyBean.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.test;

/**
 * Dummy object for use by tests.
 *
 * @author	Mark Hobson
 * @version	$Id: DummyBean.java 69784 2010-01-21 11:40:46Z mark@IIZUKA.CO.UK $
 */
public class DummyBean
{
	// constants --------------------------------------------------------------
	
	public static final String NAME = "name";
	
	// fields -----------------------------------------------------------------
	
	private String name;

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
