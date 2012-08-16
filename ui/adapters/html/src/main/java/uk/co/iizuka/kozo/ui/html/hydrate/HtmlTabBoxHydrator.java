/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTabBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asContainerVisitor;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.selectedComponent;

import uk.co.iizuka.kozo.ui.TabBox;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code TabBox} component using an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTabBoxHydrator.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
 * @see TabBox
 * @param <T>
 *            the tab box type this visitor can visit
 */
public class HtmlTabBoxHydrator<T extends TabBox> extends HtmlDeckHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTabBoxHydrator()
	{
		setDelegate(DEHYDRATE, selectedComponent(asContainerVisitor(new HtmlTabBoxDehydrator<T>())));
	}
}
