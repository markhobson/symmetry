/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDContent.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * Defines a DTD element content model.
 * 
 * @author Mark Hobson
 * @version $Id: DTDContent.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public interface DTDContent
{
	/**
	 * Validates the specified element names against this content model.
	 * <p>
	 * Starting from the specified index, this method should scan the specified element names until either: an element
	 * name invalidates this content model, in which case <code>-1</code> is returned; or the end of the element names
	 * is reached, in which case the length of the element names array is returned.
	 * </p>
	 * 
	 * @param index
	 *            the element name index to start validating from
	 * @param elements
	 *            the element names to validate against, where an array element of <code>null</code> represents
	 *            character data
	 * @return the element name index after this content model, or <code>-1</code> if this content model cannot be
	 *         satisfied
	 */
	int validate(int index, String... elements);
}
