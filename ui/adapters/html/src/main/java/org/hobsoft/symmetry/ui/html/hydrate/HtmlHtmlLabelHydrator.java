/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlHtmlLabelHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.ui.HtmlLabel;

/**
 * Phased hydrator that dehydrates and rehydrates an {@code HtmlLabel} component using an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
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
