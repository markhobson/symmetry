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
 * Represents an attribute list item declaration within a DTD.
 * 
 * @author Mark Hobson
 * @see <a href="http://www.w3.org/TR/REC-xml/#attdecls">3.3 Attribute-List Declarations</a>
 */
public class DTDAttribute
{
	// fields -----------------------------------------------------------------
	
	private final DTDElement element;
	
	private final String name;
	
	private final DTDAttrType type;
	
	private final DTDAttrDecl declaration;
	
	// constructors -----------------------------------------------------------
	
	public DTDAttribute(DTDElement element, String name, String type, String mode, String value)
		throws DTDParseException
	{
		this(element, name, DTDAttrTypeParser.parse(type), DTDAttrDeclParser.parse(mode, value));
	}
	
	public DTDAttribute(DTDElement element, String name, DTDAttrType type, DTDAttrDecl declaration)
	{
		this.element = element;
		this.name = name;
		this.type = type;
		this.declaration = declaration;
	}
	
	// public methods ---------------------------------------------------------
	
	public DTDElement getElement()
	{
		return element;
	}
	
	public String getName()
	{
		return name;
	}
	
	public DTDAttrType getType()
	{
		return type;
	}
	
	public DTDAttrDecl getDeclaration()
	{
		return declaration;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("<!ATTLIST ").append(element.getName());
		
		appendAttribute(builder);
		
		builder.append(">");
		
		return builder.toString();
	}
	
	// protected methods ------------------------------------------------------
	
	protected StringBuilder appendAttribute(StringBuilder builder)
	{
		builder.append(name);
		builder.append(' ').append(type);
		builder.append(' ').append(declaration);
		
		return builder;
	}
}
