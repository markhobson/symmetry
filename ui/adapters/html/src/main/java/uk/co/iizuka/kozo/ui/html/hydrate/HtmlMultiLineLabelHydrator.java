/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlMultiLineLabelHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.ui.MultiLineLabel;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code MultiLineLabel} component using an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlMultiLineLabelHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see MultiLineLabel
 * @param <T>
 *            the multi-line label type this visitor can visit
 */
public class HtmlMultiLineLabelHydrator<T extends MultiLineLabel> extends HtmlLabelHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlMultiLineLabelHydrator()
	{
		setDelegate(HydrationPhase.DEHYDRATE, new HtmlMultiLineLabelDehydrator<T>());
	}
}
