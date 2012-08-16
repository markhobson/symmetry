/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/ButtonHtmlButtonHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.ui.Button;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Button} component using an HTML {@code <button/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: ButtonHtmlButtonHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public class ButtonHtmlButtonHydrator<T extends Button> extends AbstractControlHtmlButtonHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public ButtonHtmlButtonHydrator()
	{
		setDelegate(DEHYDRATE, new ButtonHtmlButtonDehydrator<T>());
	}
}
