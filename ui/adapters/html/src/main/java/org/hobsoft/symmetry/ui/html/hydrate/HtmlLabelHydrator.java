/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlLabelHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Label} component using an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
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
