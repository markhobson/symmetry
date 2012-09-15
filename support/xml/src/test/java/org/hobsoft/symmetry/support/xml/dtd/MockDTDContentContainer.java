/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/MockDTDContentContainer.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * No-op <code>DTDContentContainer</code> implementation to make non-abstract.
 * 
 * @author Mark Hobson
 * @version $Id: MockDTDContentContainer.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class MockDTDContentContainer extends DTDContentContainer
{
	// constructors -----------------------------------------------------------
	
	public MockDTDContentContainer()
	{
		super();
	}
	
	public MockDTDContentContainer(DTDCardinality cardinality)
	{
		super(cardinality);
	}
	
	// DTDCardinalContent methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int validateOnce(int index, String... elements)
	{
		throw new UnsupportedOperationException();
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof MockDTDContentContainer))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
