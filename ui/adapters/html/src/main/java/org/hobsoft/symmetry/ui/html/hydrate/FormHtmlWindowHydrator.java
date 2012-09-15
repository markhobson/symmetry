/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/FormHtmlWindowHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Window} component using an HTML {@code <html/>} tag with a
 * {@code <form/>}.
 * 
 * @author Mark Hobson
 * @see Window
 * @param <T>
 *            the window type this visitor can visit
 */
public class FormHtmlWindowHydrator<T extends Window> extends HtmlWindowHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public FormHtmlWindowHydrator(ContainerVisitor<Box, HydrationContext, HydrationException> boxDehydrator)
	{
		super(boxDehydrator);
		
		setDelegate(DEHYDRATE, new FormHtmlWindowDehydrator<T>(boxDehydrator));
	}
}
