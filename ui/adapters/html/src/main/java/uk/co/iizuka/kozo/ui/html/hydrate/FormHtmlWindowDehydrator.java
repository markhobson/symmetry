/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/FormHtmlWindowDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;
import uk.co.iizuka.kozo.ui.traversal.DelegatingContainerVisitor;

/**
 * Hydrator that dehydrates a {@code Window} component to an HTML {@code <html/>} tag with a {@code <form/>}.
 * 
 * @author Mark Hobson
 * @version $Id: FormHtmlWindowDehydrator.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
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
