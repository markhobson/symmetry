/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/MockDTDContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * No-op <code>DTDContent</code> implementation for use with tests.
 * 
 * @author Mark Hobson
 * @version $Id: MockDTDContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class MockDTDContent implements DTDContent
{
	// DTDContent methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public int validate(int index, String... elements)
	{
		throw new UnsupportedOperationException();
	}
}
