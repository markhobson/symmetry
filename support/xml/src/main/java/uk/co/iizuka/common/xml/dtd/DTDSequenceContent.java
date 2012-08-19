/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDSequenceContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

import java.util.Iterator;

/**
 * A compound DTD element content model whose comprising content models must occur sequentially.
 * 
 * @author Mark Hobson
 * @version $Id: DTDSequenceContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class DTDSequenceContent extends DTDContentContainer
{
	// constructors -----------------------------------------------------------
	
	public DTDSequenceContent()
	{
		super();
	}
	
	public DTDSequenceContent(DTDCardinality cardinality)
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
		if (getChildCount() == 0)
		{
			return -1;
		}
		
		Iterator<DTDContent> children = iterator();
		
		while (index != -1 && children.hasNext())
		{
			DTDContent child = children.next();
			
			index = child.validate(index, elements);
		}
		
		return index;
	}
	
	// DTDContentContainer methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getSeparator()
	{
		return ",";
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
		if (!(object instanceof DTDSequenceContent))
		{
			return false;
		}
		
		return super.equals(object);
	}
}
