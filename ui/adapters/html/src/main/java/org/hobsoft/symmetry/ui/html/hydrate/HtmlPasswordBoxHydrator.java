/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlPasswordBoxHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.PasswordBox;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code PasswordBox} component using an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @see PasswordBox
 * @param <T>
 *            the password box type this visitor can visit
 */
public class HtmlPasswordBoxHydrator<T extends PasswordBox> extends HtmlTextBoxHydrator<T>
{
	// constructors -----------------------------------------------------------

	public HtmlPasswordBoxHydrator()
	{
		setDelegate(DEHYDRATE, new HtmlPasswordBoxDehydrator<T>());
	}
}
