/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTabHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

import org.hobsoft.symmetry.ui.Tab;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Tab} component using an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
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
