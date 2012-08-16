/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/FormHtmlWindowHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Window} component using an HTML {@code <html/>} tag with a
 * {@code <form/>}.
 * 
 * @author Mark Hobson
 * @version $Id: FormHtmlWindowHydrator.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
 * @see Window
 * @param <T>
 *            the window type this visitor can visit
 */
public class FormHtmlWindowHydrator<T extends Window> extends HtmlWindowHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public FormHtmlWindowHydrator(ContainerVisitor<Box, HydrationContext, HydrationException> boxDehydrator)
	{
		super(boxDehydrator);
		
		setDelegate(DEHYDRATE, new FormHtmlWindowDehydrator<T>(boxDehydrator));
	}
}
