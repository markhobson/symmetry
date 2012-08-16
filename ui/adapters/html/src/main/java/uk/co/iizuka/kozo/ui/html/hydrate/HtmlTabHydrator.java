/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTabHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.ui.Tab;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Tab} component using an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTabHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Tab
 * @param <T>
 *            the tab type this visitor can visit
 */
public class HtmlTabHydrator<T extends Tab> extends AbstractHtmlButtonHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTabHydrator()
	{
		setDelegate(DEHYDRATE, new HtmlTabDehydrator<T>());
	}
}
