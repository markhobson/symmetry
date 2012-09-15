/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDNotationAttrTypeTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests <code>DTDNotationAttrType</code>.
 * 
 * @author Mark Hobson
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
