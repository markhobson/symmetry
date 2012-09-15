/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDContentContainer.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An abstract compound DTD element content model that consists of a number of other content models.
 * 
 * @author Mark Hobson
 */
public abstract class DTDContentContainer extends DTDCardinalContent implements Iterable<DTDContent>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_SEPARATOR = ",";
	
	// fields -----------------------------------------------------------------
	
	private final List<DTDContent> children;
	
	// constructors -----------------------------------------------------------
	
	public DTDContentContainer()
	{
		this(DTDCardinality.ONCE);
	}
	
	public DTDContentContainer(DTDCardinality cardinality)
	{
		super(cardinality);
		
		children = new ArrayList<DTDContent>();
	}
	
	// Iterable methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<DTDContent> iterator()
	{
		return getChildren().iterator();
	}
	
	// public methods ---------------------------------------------------------
	
	public void addChild(DTDContent child)
	{
		children.add(child);
	}
	
	public void removeChild(DTDContent child)
	{
		children.remove(child);
	}
	
	public DTDContent getChild(int index)
	{
		return children.get(index);
	}
	
	public int getChildCount()
	{
		return children.size();
	}
	
	public List<DTDContent> getChildren()
	{
		return Collections.unmodifiableList(children);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode() * 31 + children.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDContentContainer))
		{
			return false;
		}
		
		DTDContentContainer contentContainer = (DTDContentContainer) object;
		
		return super.equals(object) && children.equals(contentContainer.children);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("(");
		appendChildren(builder);
		builder.append(")");
		builder.append(getCardinality());
		
		return builder.toString();
	}
	
	// protected methods ------------------------------------------------------
	
	protected void appendChildren(StringBuilder builder)
	{
		for (int i = 0; i < getChildCount(); i++)
		{
			if (i > 0)
			{
				builder.append(getSeparator());
			}
			
			builder.append(getChild(i));
		}
	}
	
	protected String getSeparator()
	{
		return DEFAULT_SEPARATOR;
	}
}
