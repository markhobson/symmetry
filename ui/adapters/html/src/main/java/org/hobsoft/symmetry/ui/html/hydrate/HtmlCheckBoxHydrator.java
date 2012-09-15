/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlCheckBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators.beanProperty;
import static org.hobsoft.symmetry.ui.html.hydrate.HtmlComponentHydrators.form;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code CheckBox} component using an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlCheckBoxHydrator.java 98878 2012-02-29 16:54:12Z mark@IIZUKA.CO.UK $
 * @see CheckBox
 * @param <T>
 *            the check box type this visitor can visit
 */
public class HtmlCheckBoxHydrator<T extends CheckBox> extends PhasedBeanHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlCheckBoxHydrator()
	{
		setDelegate(DEHYDRATE_INITIALIZE, form(true, false));
		
		setDelegate(DEHYDRATE, new HtmlCheckBoxDehydrator<T>());
		
		setDelegate(REHYDRATE_PARAMETERS, beanProperty(Selectable.SELECTED_PROPERTY, false));
	}
}
