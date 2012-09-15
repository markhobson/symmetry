/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDMixedContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * A compound DTD element content model whose comprising content models are mutually-exclusive together with character
 * data.
 * 
 * @author Mark Hobson
 * @version $Id: DTDMixedContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class DTDMixedContent extends DTDChoiceContainer
{
	// constructors -----------------------------------------------------------
	
	public DTDMixedContent()
	{
		super();
	}
	
	public DTDMixedContent(DTDCardinality cardinality)
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
		boolean pcdata = false;
		
		// skip any initial #PCDATA elements
		while (index < elements.length && elements[index] == null)
		{
			index++;
			pcdata = true;
		}
		
		// validate as per normal
		int nextIndex = super.validateOnce(index, elements);
		
		// if invalid but initial #PCDATA found then ensure valid
		if (nextIndex != -1 || !pcdata)
		{
			index = nextIndex;
		}
		
		// skip any trailing #PCDATA elements
		if (index != -1)
		{
			while (index < elements.length && elements[index] == null)
			{
				index++;
			}
		}
		
		return index;
	}
	
	// DTDContentContainer methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void appendChildren(StringBuilder builder)
	{
		builder.append("#PCDATA");
		
		if (getChildCount() > 0)
		{
			builder.append(getSeparator());
		}
		
		super.appendChildren(builder);
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
		if (!(object instanceof DTDMixedContent))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
