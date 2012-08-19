/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDAttrTypes.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * An enumeration of the basic DTD attribute types.
 * 
 * @author	Mark Hobson
 * @version	$Id: DTDAttrTypes.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public enum DTDAttrTypes implements DTDAttrType
{
	/**
	 * A DTD attribute type that corresponds to the <code>CDATA</code> type.
	 */
	CDATA,
	
	/**
	 * A DTD attribute type that corresponds to the <code>ID</code> type. 
	 */
	ID,

	/**
	 * A DTD attribute type that corresponds to the <code>IDREF</code> type.
	 */
	IDREF,

	/**
	 * A DTD attribute type that corresponds to the <code>IDREFS</code> type.
	 */
	IDREFS,

	/**
	 * A DTD attribute type that corresponds to the <code>ENTITY</code> type.
	 */
	ENTITY,

	/**
	 * A DTD attribute type that corresponds to the <code>ENTITIES</code> type.
	 */
	ENTITIES,

	/**
	 * A DTD attribute type that corresponds to the <code>NMTOKEN</code> type.
	 */
	NMTOKEN,

	/**
	 * A DTD attribute type that corresponds to the <code>NMTOKENS</code> type.
	 */
	NMTOKENS;
}
