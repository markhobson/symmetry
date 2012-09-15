/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDChoiceContainer.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import java.util.Iterator;

/**
 * An abstract compound DTD element content model whose comprising content models are mutually-exclusive.
 * 
 * @author Mark Hobson
 * @version $Id: DTDChoiceContainer.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public abstract class DTDChoiceContainer extends DTDContentContainer
{
	// constructors -----------------------------------------------------------
	
	public DTDChoiceContainer()
	{
		super();
	}
	
	public DTDChoiceContainer(DTDCardinality cardinality)
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
		int returnIndex = -1;
		
		Iterator<DTDContent> children = iterator();
		
		while (returnIndex == -1 && children.hasNext())
		{
			DTDContent child = children.next();
			
			int childIndex = child.validate(index, elements);
			
			if (childIndex != -1)
			{
				returnIndex = childIndex;
			}
		}
		
		return returnIndex;
	}

	// DTDContentContainer methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getSeparator()
	{
		return "|";
	}
}
