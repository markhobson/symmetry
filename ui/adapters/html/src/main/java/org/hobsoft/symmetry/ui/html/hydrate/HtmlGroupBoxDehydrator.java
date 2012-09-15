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

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;
import org.hobsoft.symmetry.ui.traversal.DelegatingContainerVisitor;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * Hydrator that dehydrates a {@code GroupBox} component to an HTML {@code <fieldset/>} tag.
 * 
 * @author Mark Hobson
 * @see GroupBox
 * @param <T>
 *            the group box type this visitor can visit
 */
public class HtmlGroupBoxDehydrator<T extends GroupBox>
	extends DelegatingContainerVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlGroupBoxDehydrator(ContainerVisitor<Box, HydrationContext, HydrationException> boxDehydrator)
	{
		super(boxDehydrator);
	}

	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T groupBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("fieldset");
			writeClass(out, getGroupBoxCssClass(groupBox));
			out.writeStartElement("legend");
			out.writeStartElement("label");
			out.writeCharacters(groupBox.getTitle());
			// label
			out.writeEndElement();
			// legend
			out.writeEndElement();
			out.writeStartElement("div");
			// TODO: move to decorator
			out.writeAttribute("class", "grouppanel");
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		super.visit(groupBox, context);
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T groupBox, HydrationContext context) throws HydrationException
	{
		super.endVisit(groupBox, context);
		
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// div
			out.writeEndElement();
			// fieldset
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getGroupBoxCssClass(T groupBox)
	{
		return new CssClassBuilder("groupbox");
	}
}