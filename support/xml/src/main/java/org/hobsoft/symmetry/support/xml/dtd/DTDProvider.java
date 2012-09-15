/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dtd/DTDProvider.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * Defines an API for objects to provide DTD instances.
 * 
 * @author Mark Hobson
 * @see DTD
 */
public interface DTDProvider
{
	// TODO: would be nice to remove the need for this
	
	DTD getDTD();
}
