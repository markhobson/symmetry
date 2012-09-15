/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlGroupBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code GroupBox} component using an HTML {@code <fieldset/>} tag.
 * 
 * @author Mark Hobson
 * @see GroupBox
 * @param <T>
 *            the group box type this visitor can visit
 */
public class HtmlGroupBoxHydrator<T extends GroupBox> extends AbstractHtmlContainerHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlGroupBoxHydrator(ContainerVisitor<Box, HydrationContext, HydrationException> boxDehydrator)
	{
		setDelegate(DEHYDRATE, new HtmlGroupBoxDehydrator<T>(boxDehydrator));
	}
}
