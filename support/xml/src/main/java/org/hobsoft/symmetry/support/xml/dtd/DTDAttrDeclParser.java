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

/**
 * Builds <code>DTDAttrDecl</code> instances from their DTD string representations.
 * 
 * @author Mark Hobson
 * @see DTDAttrDecl
 */
public final class DTDAttrDeclParser
{
	// constructors -----------------------------------------------------------
	
	private DTDAttrDeclParser()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------

	public static DTDAttrDecl parse(String mode, String value)
	{
		if ("#REQUIRED".equals(mode))
		{
			return DTDAttrDecls.REQUIRED;
		}
		
		if ("#IMPLIED".equals(mode))
		{
			return DTDAttrDecls.IMPLIED;
		}
		
		if ("#FIXED".equals(mode))
		{
			return new DTDFixedAttrDecl(value);
		}
		
		return new DTDDefaultAttrDecl(value);
	}
}
