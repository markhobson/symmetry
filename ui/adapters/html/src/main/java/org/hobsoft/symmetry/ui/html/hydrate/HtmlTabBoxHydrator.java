/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTabBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.TabBox;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asContainerVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.selectedComponent;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code TabBox} component using an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTabBoxHydrator.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
 * @see TabBox
 * @param <T>
 *            the tab box type this visitor can visit
 */
public class HtmlTabBoxHydrator<T extends TabBox> extends HtmlDeckHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTabBoxHydrator()
	{
		setDelegate(DEHYDRATE, selectedComponent(asContainerVisitor(new HtmlTabBoxDehydrator<T>())));
	}
}
