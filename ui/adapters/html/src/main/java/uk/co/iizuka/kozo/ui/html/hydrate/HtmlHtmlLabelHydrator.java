/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlHtmlLabelHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.ui.HtmlLabel;

/**
 * Phased hydrator that dehydrates and rehydrates an {@code HtmlLabel} component using an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlHtmlLabelHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see HtmlLabel
 * @param <T>
 *            the HTML label type this visitor can visit
 */
public class HtmlHtmlLabelHydrator<T extends HtmlLabel> extends HtmlLabelHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlHtmlLabelHydrator()
	{
		setDelegate(HydrationPhase.DEHYDRATE, new HtmlHtmlLabelDehydrator<T>());
	}
}
