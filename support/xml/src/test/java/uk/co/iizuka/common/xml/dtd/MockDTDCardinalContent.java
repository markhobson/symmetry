/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/MockDTDCardinalContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * No-op <code>DTDCardinalContent</code> subclass to make non-abstract.
 * 
 * @author Mark Hobson
 * @version $Id: MockDTDCardinalContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class MockDTDCardinalContent extends DTDCardinalContent
{
	// constructors -----------------------------------------------------------
	
	public MockDTDCardinalContent()
	{
		super();
	}
	
	public MockDTDCardinalContent(DTDCardinality cardinality)
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
}
