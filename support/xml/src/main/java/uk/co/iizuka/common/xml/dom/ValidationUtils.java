/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/ValidationUtils.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.dom;

import org.w3c.dom.Node;

/**
 * Provides various utility methods for working with the DOM Validation API.
 * 
 * @author Mark Hobson
 * @version $Id: ValidationUtils.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see <a href="http://www.w3.org/TR/2004/REC-DOM-Level-3-Val-20040127/">Document Object Model (DOM) Level 3 Validation
 *      Specification</a>
 */
public final class ValidationUtils
{
	// constants --------------------------------------------------------------
	
	/**
	 * The name of the DOM Validation feature.
	 */
	public static final String VALIDATION_FEATURE = "Validation";
	
	/**
	 * The version of the DOM Level 3 Validation feature.
	 */
	public static final String VALIDATION_VERSION_3_0 = "3.0";
	
	// constructors -----------------------------------------------------------
	
	private ValidationUtils()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets whether the specified node's DOM implementation supports Validation 3.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @return <code>true</code> if the node's DOM implementation supports Validation 3.0
	 */
	public static boolean hasValidation3(Node node)
	{
		return NodeUtils.hasFeature(node, VALIDATION_FEATURE, VALIDATION_VERSION_3_0);
	}
	
	/**
	 * Ensures that the specified node's DOM implementation supports Validation 3.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Validation 3.0
	 */
	public static void ensureValidation3(Node node)
	{
		NodeUtils.ensureFeature(node, VALIDATION_FEATURE, VALIDATION_VERSION_3_0);
	}
}
