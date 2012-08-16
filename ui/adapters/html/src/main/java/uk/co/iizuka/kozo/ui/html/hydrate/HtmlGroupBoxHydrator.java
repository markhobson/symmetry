/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlGroupBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code GroupBox} component using an HTML {@code <fieldset/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlGroupBoxHydrator.java 99169 2012-03-09 17:09:04Z mark@IIZUKA.CO.UK $
 * @see GroupBox
 * @param <T>
 *            the group box type this visitor can visit
 */
public class HtmlGroupBoxHydrator<T extends GroupBox> extends AbstractHtmlContainerHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlGroupBoxHydrator(ContainerVisitor<Box, HydrationContext, HydrationException> boxDehydrator)
	{
		setDelegate(DEHYDRATE, new HtmlGroupBoxDehydrator<T>(boxDehydrator));
	}
}
