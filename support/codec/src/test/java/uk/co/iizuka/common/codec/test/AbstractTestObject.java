/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/test/AbstractTestObject.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.test;

/**
 * Base for test objects used to test codecs.
 * 
 * @author	Mark Hobson
 * @version	$Id: AbstractTestObject.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractTestObject
{
	// fields -----------------------------------------------------------------
	
	private final String id;

	// constructors -----------------------------------------------------------
	
	public AbstractTestObject(String id)
	{
		this.id = id;
	}

	// public methods ---------------------------------------------------------
	
	public String getId()
	{
		return id;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object == null || !object.getClass().equals(getClass()))
		{
			return false;
		}
		
		AbstractTestObject testObject = (AbstractTestObject) object;
		
		return getId().equals(testObject.getId());
	}
}
