/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextAreaHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

import org.hobsoft.symmetry.ui.TextArea;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code TextArea} component using an HTML {@code <textarea/>} tag.
 * 
 * @author Mark Hobson
 * @see TextArea
 * @param <T>
 *            the text area type this visitor can visit
 */
public class HtmlTextAreaHydrator<T extends TextArea> extends HtmlTextBoxHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTextAreaHydrator()
	{
		setDelegate(DEHYDRATE, new HtmlTextAreaDehydrator<T>());
	}
}
