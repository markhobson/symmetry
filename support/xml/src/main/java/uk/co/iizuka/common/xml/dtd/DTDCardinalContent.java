/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDCardinalContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * An abstract DTD element content model that provides support for occurring a different number of times.
 * 
 * @author Mark Hobson
 * @version $Id: DTDCardinalContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public abstract class DTDCardinalContent implements DTDContent
{
	// fields -----------------------------------------------------------------
	
	private DTDCardinality cardinality;
	
	// constructors -----------------------------------------------------------
	
	public DTDCardinalContent()
	{
		this(DTDCardinality.ONCE);
	}
	
	public DTDCardinalContent(DTDCardinality cardinality)
	{
		setCardinality(cardinality);
	}
	
	// DTDContent methods -----------------------------------------------------
	
	/**
	 * Validates the specified element names against this content model.
	 * <p>
	 * This implementation provides support for this content model's cardinality by delegating to the
	 * <code>validateOnce</code> method, which is only required to consider the singular case. Thus that method should
	 * be implemented in preference to overriding this one.
	 * </p>
	 * 
	 * @see #validateOnce(int, String[])
	 */
	public int validate(int index, String... elements)
	{
		switch (getCardinality())
		{
			case ONCE:
				// simply validate once
				index = validateOnce(index, elements);
				break;
				
			case ONE_OR_MORE:
				// validate the mandatory one time
				index = validateOnce(index, elements);
				
				// continue to validate until invalid
				if (index != -1)
				{
					index = validateMany(index, elements);
				}
				
				break;
				
			case ZERO_OR_MORE:
				// validate until invalid
				index = validateMany(index, elements);
				break;
				
			case ZERO_OR_ONE:
				// validate once if valid
				int nextIndex = validateOnce(index, elements);
				
				if (nextIndex != -1)
				{
					index = nextIndex;
				}
				
				break;
				
			default:
				throw new IllegalStateException("Unknown cardinality: " + getCardinality());
		}
		
		return index;
	}
	
	// public methods ---------------------------------------------------------
	
	public DTDCardinality getCardinality()
	{
		return cardinality;
	}
	
	public void setCardinality(DTDCardinality cardinality)
	{
		// TODO: can we avoid this being mutable?
		
		if (cardinality == null)
		{
			throw new IllegalArgumentException("cardinality: null");
		}
		
		this.cardinality = cardinality;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return cardinality.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof DTDCardinalContent))
		{
			return false;
		}
		
		DTDCardinalContent cardinalContent = (DTDCardinalContent) object;
		
		return cardinality.equals(cardinalContent.cardinality);
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Validates the specified element names against this content model with a cardinality of <code>ONCE</code>.
	 * <p>
	 * This method is used by the <code>validate</code> method which provides support for all cardinalities.
	 * </p>
	 * <p>
	 * Starting from the specified index, this method should scan the specified element names until either: an element
	 * name invalidates this content model with a cardinality of <code>ONCE</code>, in which case <code>-1</code> is
	 * returned; or the end of the element names is reached, in which case the length of the element names array is
	 * returned.
	 * </p>
	 * 
	 * @param index
	 *            the element name index to start validating from
	 * @param elements
	 *            the element names to validate against, where an array element of <code>null</code> represents
	 *            character data
	 * @return the element name index after this content model with a cardinality of <code>ONCE</code>, or
	 *         <code>-1</code> if this content model with a cardinality of <code>ONCE</code> cannot be satisfied
	 * @see #validate(int, String[])
	 */
	protected abstract int validateOnce(int index, String... elements);
	
	// private methods --------------------------------------------------------
	
	/**
	 * Validates the specified element names against this content model repeatedly until invalid.
	 * <p>
	 * This method is used internally by the <code>validate</code> method.
	 * </p>
	 * 
	 * @param index
	 *            the element name index to start validating from
	 * @param elements
	 *            the element names to validate against, where an array element of <code>null</code> represents
	 *            character data
	 * @return the element name index after this content model, or <code>-1</code> if this content model cannot be
	 *         satisfied
	 * @see #validate(int, String[])
	 */
	private int validateMany(int index, String... elements)
	{
		int nextIndex = validateOnce(index, elements);
		
		while (nextIndex != -1 && nextIndex > index)
		{
			index = nextIndex;
			
			nextIndex = validateOnce(index, elements);
		}

		return index;
	}
}
