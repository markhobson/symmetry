/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/InputHtmlButtonHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

import org.hobsoft.symmetry.ui.Button;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Button} component using an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public class InputHtmlButtonHydrator<T extends Button> extends AbstractControlHtmlButtonHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public InputHtmlButtonHydrator()
	{
		setDelegate(DEHYDRATE, new InputHtmlButtonDehydrator<T>());
	}
}
