/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
