/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.xml;

import org.hobsoft.symmetry.support.xml.dom.NodeFilters;
import org.w3c.dom.traversal.NodeFilter;

/**
 * Provides constants related to the W3C XLink specification.
 * 
 * @author Mark Hobson
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
