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
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeStyle;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * Hydrator that dehydrates a {@code Box} component to an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @see Box
 * @param <T>
 *            the box type this visitor can visit
 */
public class ContainerHtmlBoxDehydrator<T extends Box> extends AbstractHtmlBoxDehydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T box, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		if (!isEmpty(box))
		{
			try
			{
				out.writeStartElement("div");
				writeClass(out, getBoxCssClass(box));
			}
			catch (XMLStreamException exception)
			{
				throw new HydrationException(exception);
			}
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T box, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		if (!isEmpty(box))
		{
			try
			{
				// div
				out.writeEndElement();
			}
			catch (XMLStreamException exception)
			{
				throw new HydrationException(exception);
			}
		}
				
		return VISIT_SIBLINGS;
	}
	
	// ContainerVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitChild(T box, HydrationContext context, int childIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement(getBoxChildElement(box));
			
			writeClass(out, getBoxChildCssClass(box, childIndex));
			writeStyle(out, getBoxChildCssStyle(box, childIndex));
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitChild(T box, HydrationContext context, int childIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// span/div
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// private methods --------------------------------------------------------
	
	private static String getBoxChildElement(Box box)
	{
		return isHorizontal(box) ? "span" : "div";
	}
}
