/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlPasswordBoxHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.ui.PasswordBox;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code PasswordBox} component using an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlPasswordBoxHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
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
