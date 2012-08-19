/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDAttribute.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * Represents an attribute list item declaration within a DTD.
 * 
 * @author Mark Hobson
 * @version $Id: DTDAttribute.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
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
