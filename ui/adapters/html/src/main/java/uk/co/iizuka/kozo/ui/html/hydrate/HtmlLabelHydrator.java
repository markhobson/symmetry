/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlLabelHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedBeanHydrator;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Label} component using an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlLabelHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Label
 * @param <T>
 *            the label type this visitor can visit
 */
public class HtmlLabelHydrator<T extends Label> extends PhasedBeanHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlLabelHydrator()
	{
		setDelegate(DEHYDRATE, new HtmlLabelDehydrator<T>());
	}
}
