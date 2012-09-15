/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlWindowHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

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
