/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDParseException.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dtd;

/**
 * Indicates that an error occurred whilst trying to parse a DTD.
 * 
 * @author Mark Hobson
 * @version $Id: DTDParseException.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class DTDParseException extends Exception
{
	// constants --------------------------------------------------------------
	
	/**
	 * The serialisation unique ID.
	 */
	private static final long serialVersionUID = 2785972999124337329L;
	
	// fields -----------------------------------------------------------------
	
	private final int column;
	
	// constructors -----------------------------------------------------------
	
	public DTDParseException(String message)
	{
		this(message, -1);
	}
	
	public DTDParseException(String message, int column)
	{
		super((column != -1) ? message + " at column " + column : message);
		
		this.column = column;
	}
	
	// public methods ---------------------------------------------------------
	
	public int getColumn()
	{
		return column;
	}
}
