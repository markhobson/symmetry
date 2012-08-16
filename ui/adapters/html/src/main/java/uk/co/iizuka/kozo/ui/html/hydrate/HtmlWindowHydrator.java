/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlWindowHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Window} component using an HTML {@code <html/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlWindowHydrator.java 99169 2012-03-09 17:09:04Z mark@IIZUKA.CO.UK $
 * @see Window
 * @param <T>
 *            the window type this visitor can visit
 */
public class HtmlWindowHydrator<T extends Window> extends AbstractHtmlContainerHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlWindowHydrator(ContainerVisitor<Box, HydrationContext, HydrationException> boxDehydrator)
	{
		setDelegate(DEHYDRATE, new HtmlWindowDehydrator<Window>(boxDehydrator));
	}
}
