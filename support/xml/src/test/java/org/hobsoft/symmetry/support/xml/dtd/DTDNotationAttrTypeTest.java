/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDNotationAttrTypeTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Tests <code>DTDNotationAttrType</code>.
 * 
 * @author Mark Hobson
 * @version $Id: DTDNotationAttrTypeTest.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 * @see DTDNotationAttrType
 */
public class DTDNotationAttrTypeTest
{
	// equals tests -----------------------------------------------------------
	
	@Test
	public void equalsSame()
	{
		DTDNotationAttrType attrType1 = new DTDNotationAttrType();
		DTDNotationAttrType attrType2 = new DTDNotationAttrType();
		
		assertEquals(attrType1, attrType2);
	}
	
	@Test
	public void equalsSubclass()
	{
		DTDNotationAttrType attrType1 = new DTDNotationAttrType();
		DTDValuedAttrType attrType2 = new MockDTDValuedAttrType();
		
		assertFalse(attrType1.equals(attrType2));
	}
}
