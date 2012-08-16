/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlComponentHydrators.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.common.hydrate.DelegatingHierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.HierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.html.HtmlDocument;
import uk.co.iizuka.kozo.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlComponentHydrators.java 98868 2012-02-29 11:10:57Z mark@IIZUKA.CO.UK $
 */
public final class HtmlComponentHydrators
{
	// constructors -----------------------------------------------------------
	
	private HtmlComponentHydrators()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends Component> HierarchicalComponentHydrator<T> htmlDocument(
		HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> delegate,
		final HtmlDocument document)
	{
		// TODO: do we really want to be lenient towards null delegates?
		return new DelegatingHierarchicalComponentHydrator<T>(nullHierarchicalVisitor(delegate))
		{
			@Override
			public Visit visit(T component, HydrationContext context) throws HydrationException
			{
				super.visit(component, context);
				
				context.getOrSet(HtmlDocument.class, new HtmlDocument()).add(document);
				
				return VISIT_CHILDREN;
			}
		};
	}
	
	public static <T extends Component> HierarchicalComponentHydrator<T> form(final boolean post,
		final boolean multipartForm)
	{
		return new NullHierarchicalComponentHydrator<T>()
		{
			@Override
			public Visit visit(T component, HydrationContext context) throws HydrationException
			{
				Parameters parameters = context.getOrSet(Parameters.class, new Parameters());
				
				if (post)
				{
					parameters.setPost(true);
				}
				
				if (multipartForm)
				{
					parameters.setMultipartForm(true);
				}
				
				return SKIP_CHILDREN;
			}
		};
	}
}
