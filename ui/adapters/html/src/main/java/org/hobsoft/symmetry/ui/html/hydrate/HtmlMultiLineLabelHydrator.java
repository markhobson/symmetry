/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlMultiLineLabelHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.ui.MultiLineLabel;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code MultiLineLabel} component using an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
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
