/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlComponentHydrators.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.common.hydrate.DelegatingHierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.html.HtmlDocument;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

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
