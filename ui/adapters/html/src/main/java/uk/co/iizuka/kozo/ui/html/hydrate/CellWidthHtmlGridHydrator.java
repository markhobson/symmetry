/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/CellWidthHtmlGridHydrator.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.ui.Grid;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Grid} component using an HTML {@code <table/>} tag without
 * using {@code min-width} on {@code <col/>}.
 * <p>
 * This works around issues in Chrome 18, Safari 5 and Opera 11.
 * 
 * @author Mark Hobson
 * @version $Id: CellWidthHtmlGridHydrator.java 100113 2012-03-30 17:00:28Z mark@IIZUKA.CO.UK $
 * @see <a href="http://code.google.com/p/chromium/issues/detail?id=120886">Chrome issue 120886</a>
 * @param <T>
 *            the grid type this visitor can visit
 */
public class CellWidthHtmlGridHydrator<T extends Grid> extends HtmlGridHydrator<T>
{
	// TODO: remove once Chrome issue 120886 fixed and in stable
	
	// constructors -----------------------------------------------------------
	
	public CellWidthHtmlGridHydrator()
	{
		setDelegate(DEHYDRATE, new CellWidthHtmlGridDehydrator<T>());
	}
}
