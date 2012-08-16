/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlDeckHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.selectedComponent;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitors;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Deck} component using an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlDeckHydrator.java 99169 2012-03-09 17:09:04Z mark@IIZUKA.CO.UK $
 * @see Deck
 * @param <T>
 *            the deck type this visitor can visit
 */
public class HtmlDeckHydrator<T extends Deck> extends AbstractHtmlContainerHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlDeckHydrator()
	{
		setDelegate(DEHYDRATE, selectedComponent(new HtmlDeckDehydrator<T>()));
		
		// filter unselected children from parameter rehydration
		setDelegate(REHYDRATE_PARAMETERS,
			ComponentVisitors.<T, HydrationContext, HydrationException>selectedComponent());
	}
}
