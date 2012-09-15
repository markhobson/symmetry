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
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;
import org.hobsoft.symmetry.ui.traversal.DelegatingContainerVisitor;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * Hydrator that dehydrates a {@code Window} component to an HTML {@code <html/>} tag with a {@code <form/>}.
 * 
 * @author Mark Hobson
 * @see Window
 * @param <T>
 *            the window type this visitor can visit
 */
public class FormHtmlWindowDehydrator<T extends Window> extends HtmlWindowDehydrator<T>
{
	// types ------------------------------------------------------------------
	
	/**
	 * Dehydration context parameters for this hydrator.
	 */
	public static final class Parameters
	{
		// fields -----------------------------------------------------------------
		
		private boolean post;
		
		private boolean multipartForm;
		
		// public methods ---------------------------------------------------------
		
		public boolean isPost()
		{
			return post;
		}
		
		public void setPost(boolean post)
		{
			this.post = post;
		}
		
		public boolean isMultipartForm()
		{
			return multipartForm;
		}
		
		public void setMultipartForm(boolean multipartForm)
		{
			this.multipartForm = multipartForm;
		}
	}
	
	private static class FormBoxDecorator extends DelegatingContainerVisitor<Box, HydrationContext, HydrationException>
	{
		// constructors -----------------------------------------------------------
		
		public FormBoxDecorator(ContainerVisitor<Box, HydrationContext, HydrationException> delegate)
		{
			super(delegate);
		}
		
		// HierarchicalComponentVisitor methods -----------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Visit visit(Box box, HydrationContext context) throws HydrationException
		{
			Parameters parameters = context.get(Parameters.class, new Parameters());
			
			if (parameters.isPost())
			{
				XMLStreamWriter out = context.get(XMLStreamWriter.class);
				
				try
				{
					out.writeStartElement("form");
					out.writeAttribute("method", "post");
					out.writeAttribute("action", "");
					
					if (parameters.isMultipartForm())
					{
						out.writeAttribute("enctype", "multipart/form-data");
					}
				}
				catch (XMLStreamException exception)
				{
					throw new HydrationException(exception);
				}
			}
			
			super.visit(box, context);
			
			return VISIT_CHILDREN;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public EndVisit endVisit(Box box, HydrationContext context) throws HydrationException
		{
			super.endVisit(box, context);
			
			Parameters parameters = context.get(Parameters.class, new Parameters());
			
			if (parameters.isPost())
			{
				XMLStreamWriter out = context.get(XMLStreamWriter.class);
				
				try
				{
					// form
					out.writeEndElement();
				}
				catch (XMLStreamException exception)
				{
					throw new HydrationException(exception);
				}
			}
			
			return VISIT_SIBLINGS;
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public FormHtmlWindowDehydrator(ContainerVisitor<Box, HydrationContext, HydrationException> boxDehydrator)
	{
		super(new FormBoxDecorator(boxDehydrator));
	}
}
