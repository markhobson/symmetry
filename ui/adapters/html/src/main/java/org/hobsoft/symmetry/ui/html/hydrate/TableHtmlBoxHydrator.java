/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/TableHtmlBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

import org.hobsoft.symmetry.ui.Box;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Box} component using an HTML {@code <table/>} tag.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the box type this visitor can visit
 */
public class TableHtmlBoxHydrator<T extends Box> extends AbstractHtmlContainerHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public TableHtmlBoxHydrator()
	{
		setDelegate(DEHYDRATE, new TableHtmlBoxDehydrator<T>());
	}
}
