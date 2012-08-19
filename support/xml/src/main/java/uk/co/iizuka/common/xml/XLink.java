/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/XLink.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml;

import org.w3c.dom.traversal.NodeFilter;

import uk.co.iizuka.common.xml.dom.NodeFilters;

/**
 * Provides constants related to the W3C XLink specification.
 * 
 * @author Mark Hobson
 * @version $Id: XLink.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 * @see <a href="http://www.w3.org/TR/xlink/">XML Linking Language (XLink) Version 1.0</a>
 */
public final class XLink
{
	// constants --------------------------------------------------------------
	
	/**
	 * The XLink namespace URI.
	 */
	public static final String XMLNS = "http://www.w3.org/1999/xlink";
	
	/**
	 * The XLink 1.0 doctype public identifier.
	 */
	public static final String PUBLIC_ID_1_0 = "-//IIZUKA//DTD XLink 1.0//EN";

	/**
	 * The XLink 1.0 doctype system identifier.
	 */
	public static final String SYSTEM_ID_1_0 = "http://www.iizuka.co.uk/dtd/xlink-1.0.dtd";
	
	/**
	 * The XLink default doctype public identifier.
	 */
	public static final String PUBLIC_ID = PUBLIC_ID_1_0;
	
	/**
	 * The XLink default doctype system identifier.
	 */
	public static final String SYSTEM_ID = SYSTEM_ID_1_0;
	
	// types ------------------------------------------------------------------
	
	/**
	 * The XLink attribute names.
	 */
	public static final class Attribute
	{
		// constants ----------------------------------------------------------
		
		/**
		 * The XLink href attribute name.
		 */
		public static final String HREF = "href";
		
		/**
		 * The XLink show attribute name.
		 */
		public static final String SHOW = "show";
		
		/**
		 * The XLink type attribute name.
		 */
		public static final String TYPE = "type";
		
		// constructors -------------------------------------------------------

		private Attribute()
		{
			throw new AssertionError();
		}
	}
	
	/**
	 * The XLink node filters.
	 */
	public static final class Filter
	{
		// constants ----------------------------------------------------------
		
		/**
		 * The XLink href attribute filter.
		 */
		public static final NodeFilter HREF = NodeFilters.attribute(XMLNS, Attribute.HREF);
		
		// constructors -------------------------------------------------------
		
		private Filter()
		{
			throw new AssertionError();
		}
	}
	
	// constructors -----------------------------------------------------------
	
	private XLink()
	{
		throw new AssertionError();
	}
}
