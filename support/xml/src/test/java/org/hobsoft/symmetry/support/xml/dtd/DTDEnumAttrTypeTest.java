/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/dtd/DTDEnumAttrTypeTest.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests <code>DTDEnumAttrType</code>.
 * 
 * @author Mark Hobson
 * @see DTDEnumAttrType
 */
public class DTDEnumAttrTypeTest
{
	// equals tests -----------------------------------------------------------
	
	@Test
	public void equalsSame()
	{
		DTDEnumAttrType attrType1 = new DTDEnumAttrType();
		DTDEnumAttrType attrType2 = new DTDEnumAttrType();
		
		assertEquals(attrType1, attrType2);
	}
	
	@Test
	public void equalsSubclass()
	{
		DTDEnumAttrType attrType1 = new DTDEnumAttrType();
		DTDValuedAttrType attrType2 = new MockDTDValuedAttrType();
		
		assertFalse(attrType1.equals(attrType2));
	}
}
