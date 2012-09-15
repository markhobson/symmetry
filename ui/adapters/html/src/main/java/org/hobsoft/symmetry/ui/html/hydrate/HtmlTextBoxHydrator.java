/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators.beanProperty;
import static org.hobsoft.symmetry.ui.html.hydrate.HtmlComponentHydrators.form;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code TextBox} component using an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTextBoxHydrator.java 98868 2012-02-29 11:10:57Z mark@IIZUKA.CO.UK $
 * @see TextBox
 * @param <T>
 *            the text box type this visitor can visit
 */
public class HtmlTextBoxHydrator<T extends TextBox> extends PhasedBeanHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTextBoxHydrator()
	{
		setDelegate(DEHYDRATE_INITIALIZE, form(true, false));
		
		setDelegate(DEHYDRATE, new HtmlTextBoxDehydrator<T>());
		
		setDelegate(REHYDRATE_PARAMETERS, beanProperty(TextBox.TEXT_PROPERTY, true));
	}
}
