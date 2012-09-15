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
package org.hobsoft.symmetry.support.xml.dom.visitor;

import org.hobsoft.symmetry.support.xml.dom.NodeVisitor;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/**
 * A no-op implementation of <code>NodeVisitor</code>.
 * <p>
 * This implementation delegates all <code>NodeVisitor</code> methods to their respective node versions defined within
 * this class:
 * </p>
 * <ul>
 * <li>{@link #beginNode(Node)}</li>
 * <li>{@link #visitNode(Node)}</li>
 * <li>{@link #endNode(Node)}</li>
 * </ul>
 * <p>
 * This allows subclasses to easily capture all nodes by overriding the above methods, or capture specific node types by
 * overriding the normal <code>NodeVisitor</code> methods.
 * </p>
 * 
 * @author Mark Hobson
 */
public class AbstractNodeVisitor implements NodeVisitor
{
	// NodeVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public boolean beginElement(Element element)
	{
		return beginNode(element);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean endElement(Element element)
	{
		return endNode(element);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean beginAttr(Attr attr)
	{
		return beginNode(attr);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean endAttr(Attr attr)
	{
		return endNode(attr);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean visitText(Text text)
	{
		return visitNode(text);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean visitCDATASection(CDATASection cdata)
	{
		return visitNode(cdata);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean beginEntityReference(EntityReference reference)
	{
		return beginNode(reference);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean endEntityReference(EntityReference reference)
	{
		return endNode(reference);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean beginEntity(Entity entity)
	{
		return beginNode(entity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean endEntity(Entity entity)
	{
		return endNode(entity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean visitProcessingInstruction(ProcessingInstruction pi)
	{
		return visitNode(pi);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean visitComment(Comment comment)
	{
		return visitNode(comment);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean beginDocument(Document document)
	{
		return beginNode(document);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean endDocument(Document document)
	{
		return endNode(document);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean visitDocumentType(DocumentType doctype)
	{
		return visitNode(doctype);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean beginDocumentFragment(DocumentFragment fragment)
	{
		return beginNode(fragment);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean endDocumentFragment(DocumentFragment fragment)
	{
		return endNode(fragment);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean visitNotation(Notation notation)
	{
		return visitNode(notation);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Begins the visit to the specified node.
	 * <p>
	 * By default, all <code>NodeVisitor.beginXXX</code> methods delegate to this method.
	 * </p>
	 * 
	 * @param node
	 *            the node being visited
	 * @return whether to visit the node's children
	 */
	public boolean beginNode(Node node)
	{
		return true;
	}
	
	/**
	 * Visits to the specified node.
	 * <p>
	 * By default, all <code>NodeVisitor.visitXXX</code> methods delegate to this method.
	 * </p>
	 * 
	 * @param node
	 *            the node being visited
	 * @return whether to visit the node's siblings
	 */
	public boolean visitNode(Node node)
	{
		return true;
	}
	
	/**
	 * Ends the visit to the specified node.
	 * <p>
	 * By default, all <code>NodeVisitor.endXXX</code> methods delegate to this method.
	 * </p>
	 * 
	 * @param node
	 *            the node being visited
	 * @return whether to visit the node's siblings
	 */
	public boolean endNode(Node node)
	{
		return true;
	}
}
