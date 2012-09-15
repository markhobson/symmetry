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
package org.hobsoft.symmetry.support.xml.sax;

/**
 * Provides constants used by the SAX API.
 * 
 * @author Mark Hobson
 * @see org.xml.sax
 */
public final class SAX
{
	// TODO: add sax features
	
	// types ------------------------------------------------------------------
	
	/**
	 * 
	 */
	public static final class Property
	{
		// constants ----------------------------------------------------------
		
		/**
		 * The SAX property prefix.
		 */
		public static final String PREFIX = "http://xml.org/sax/properties/";
		
		/**
		 * Used to see most DTD declarations except those treated as lexical ("document element name is ...") or which
		 * are mandatory for all SAX parsers (<code>DTDHandler</code>).
		 * 
		 * The Object must implement <code>org.xml.sax.ext.DeclHandler</code>.
		 * 
		 * @see org.xml.sax.ext.DeclHandler
		 */
		public static final String DECLARATION_HANDLER = PREFIX + "declaration-handler";
		
		/**
		 * May be examined only during a parse, after the <code>startDocument()</code> callback has been completed;
		 * read-only. This property is a literal string describing the actual XML version of the document, such as "1.0"
		 * or "1.1".
		 */
		public static final String DOCUMENT_XML_VERSION = PREFIX + "document-xml-version";
		
		/**
		 * For "DOM Walker" style parsers, which ignore their <code>parser.parse()</code> parameters, this is used to
		 * specify the DOM (sub)tree being walked by the parser.
		 * 
		 * The Object must implement the <code>org.w3c.dom.Node</code> interface.
		 * 
		 * @see org.w3c.dom.Node
		 */
		public static final String DOM_NODE = PREFIX + "dom-node";
		
		/**
		 * Used to see some syntax events that are essential in some applications: comments, CDATA delimiters, selected
		 * general entity inclusions, and the start and end of the DTD (and declaration of document element name).
		 * 
		 * The Object must implement <code>org.xml.sax.ext.LexicalHandler</code>.
		 * 
		 * @see org.xml.sax.ext.LexicalHandler
		 */
		public static final String LEXICAL_HANDLER = PREFIX + "lexical-handler";
		
		/**
		 * Readable only during a parser callback, this exposes a TBS chunk of characters responsible for the current
		 * event.
		 */
		public static final String XML_STRING = PREFIX + "xml-string";

		// constructors -------------------------------------------------------
		
		private Property()
		{
			throw new AssertionError();
		}
	}
	
	// constructors -----------------------------------------------------------
	
	private SAX()
	{
		throw new AssertionError();
	}
}
