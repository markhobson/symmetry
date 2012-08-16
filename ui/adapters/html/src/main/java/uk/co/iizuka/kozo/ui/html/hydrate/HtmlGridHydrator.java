/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlGridHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asGridVisitor;

import uk.co.iizuka.kozo.ui.Grid;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedGridHydrator;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Grid} component using an HTML {@code <table/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlGridHydrator.java 99169 2012-03-09 17:09:04Z mark@IIZUKA.CO.UK $
 * @see Grid
 * @param <T>
 *            the grid type this visitor can visit
 */
public class HtmlGridHydrator<T extends Grid> extends PhasedGridHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlGridHydrator()
	{
		super(asGridVisitor(new AbstractHtmlContainerHydrator<T>()));
		
		setDelegate(DEHYDRATE, new HtmlGridDehydrator<T>());
	}
}
