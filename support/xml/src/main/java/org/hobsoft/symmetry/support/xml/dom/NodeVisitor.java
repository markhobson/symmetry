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
package org.hobsoft.symmetry.support.xml.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/**
 * A hierarchical visitor for DOM nodes.
 * 
 * @author Mark Hobson
 * @see NodeUtils#accept(org.w3c.dom.Node, NodeVisitor)
 * @see <a href="http://c2.com/cgi/wiki?HierarchicalVisitorPattern">HierarchicalVisitorPattern</a>
 */
public interface NodeVisitor
{
	// Element ----------------------------------------------------------------
	
	/**
	 * Begins the visit to the specified element node.
	 * 
	 * @param element
	 *            the element node being visited
	 * @return whether to visit the element node's children
	 */
	boolean beginElement(Element element);
	
	/**
	 * Ends the visit to the specified element node.
	 * 
	 * @param element
	 *            the element node being visited
	 * @return whether to visit the element node's next siblings
	 */
	boolean endElement(Element element);
	
	// Attr -------------------------------------------------------------------
	
	/**
	 * Begins the visit to the specified attribute node.
	 * 
	 * @param attr
	 *            the attribute node being visited
	 * @return whether to visit the attribute node's children
	 */
	boolean beginAttr(Attr attr);
	
	/**
	 * Ends the visit to the specified attribute node.
	 * 
	 * @param attr
	 *            the attribute node being visited
	 * @return whether to visit the attribute node's next siblings
	 */
	boolean endAttr(Attr attr);
	
	// Text -------------------------------------------------------------------
	
	/**
	 * Visits the specified text node.
	 * 
	 * @param text
	 *            the text node being visited
	 * @return whether to visit the text node's next siblings
	 */
	boolean visitText(Text text);
	
	// CDATASection -----------------------------------------------------------
	
	/**
	 * Visits the specified CDATA section node.
	 * 
	 * @param cdata
	 *            the CDATA section node being visited
	 * @return whether to visit the CDATA section node's next siblings
	 */
	boolean visitCDATASection(CDATASection cdata);
	
	// EntityReference --------------------------------------------------------
	
	/**
	 * Begins the visit to the specified entity reference node.
	 * 
	 * @param reference
	 *            the entity reference node being visited
	 * @return whether to visit the entity reference node's children
	 */
	boolean beginEntityReference(EntityReference reference);
	
	/**
	 * Ends the visit to the specified entity reference node.
	 * 
	 * @param reference
	 *            the entity reference node being visited
	 * @return whether to visit the entity reference node's next siblings
	 */
	boolean endEntityReference(EntityReference reference);
	
	// Entity -----------------------------------------------------------------
	
	/**
	 * Begins the visit to the specified entity node.
	 * 
	 * @param entity
	 *            the entity node being visited
	 * @return whether to visit the entity node's children
	 */
	boolean beginEntity(Entity entity);
	
	/**
	 * Ends the visit to the specified entity node.
	 * 
	 * @param entity
	 *            the entity node being visited
	 * @return whether to visit the entity node's next siblings
	 */
	boolean endEntity(Entity entity);
	
	// ProcessingInstruction --------------------------------------------------
	
	/**
	 * Visits the specified processing instruction node.
	 * 
	 * @param pi
	 *            the processing instruction node being visited
	 * @return whether to visit the processing instruction node's next siblings
	 */
	boolean visitProcessingInstruction(ProcessingInstruction pi);
	
	// Comment ----------------------------------------------------------------
	
	/**
	 * Visits the specified comment node.
	 * 
	 * @param comment
	 *            the comment node being visited
	 * @return whether to visit the comment node's next siblings
	 */
	boolean visitComment(Comment comment);
	
	// Document ---------------------------------------------------------------
	
	/**
	 * Begins the visit to the specified document node.
	 * 
	 * @param document
	 *            the document node being visited
	 * @return whether to visit the document node's children
	 */
	boolean beginDocument(Document document);
	
	/**
	 * Ends the visit to the specified document node.
	 * 
	 * @param document
	 *            the document node being visited
	 * @return whether to visit the document node's next siblings
	 */
	boolean endDocument(Document document);
	
	// DocumentType -----------------------------------------------------------
	
	/**
	 * Visits the specified document type node.
	 * 
	 * @param doctype
	 *            the document type node being visited
	 * @return whether to visit the document type node's next siblings
	 */
	boolean visitDocumentType(DocumentType doctype);
	
	// DocumentFragment -------------------------------------------------------
	
	/**
	 * Begins the visit to the specified document fragment node.
	 * 
	 * @param fragment
	 *            the document fragment node being visited
	 * @return whether to visit the document fragment node's children
	 */
	boolean beginDocumentFragment(DocumentFragment fragment);
	
	/**
	 * Ends the visit to the specified document fragment node.
	 * 
	 * @param fragment
	 *            the document fragment node being visited
	 * @return whether to visit the document fragment node's next siblings
	 */
	boolean endDocumentFragment(DocumentFragment fragment);
	
	// Notation ---------------------------------------------------------------
	
	/**
	 * Visits the specified notation node.
	 * 
	 * @param notation
	 *            the notation node being visited
	 * @return whether to visit the notation node's next siblings
	 */
	boolean visitNotation(Notation notation);
}
