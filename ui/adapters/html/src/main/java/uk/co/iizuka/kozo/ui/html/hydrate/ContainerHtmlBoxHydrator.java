/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/ContainerHtmlBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.ui.Box;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Box} component using an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: ContainerHtmlBoxHydrator.java 99169 2012-03-09 17:09:04Z mark@IIZUKA.CO.UK $
 * @see Box
 * @param <T>
 *            the box type this visitor can visit
 */
public class ContainerHtmlBoxHydrator<T extends Box> extends AbstractHtmlContainerHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public ContainerHtmlBoxHydrator()
	{
		setDelegate(DEHYDRATE, new ContainerHtmlBoxDehydrator<T>());
	}
}
