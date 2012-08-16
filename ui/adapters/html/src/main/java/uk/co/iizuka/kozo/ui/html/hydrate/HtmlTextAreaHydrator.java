/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextAreaHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.ui.TextArea;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code TextArea} component using an HTML {@code <textarea/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTextAreaHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see TextArea
 * @param <T>
 *            the text area type this visitor can visit
 */
public class HtmlTextAreaHydrator<T extends TextArea> extends HtmlTextBoxHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTextAreaHydrator()
	{
		setDelegate(DEHYDRATE, new HtmlTextAreaDehydrator<T>());
	}
}
