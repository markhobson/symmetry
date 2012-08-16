/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlComboBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asListBoxVisitor;

import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedBeanHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedListBoxHydrator;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code ComboBox} component using an HTML {@code <select/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlComboBoxHydrator.java 99704 2012-03-20 16:49:00Z mark@IIZUKA.CO.UK $
 * @see ComboBox
 * @param <T>
 *            the combo box type this visitor can visit
 */
public class HtmlComboBoxHydrator<T extends ComboBox<?>> extends PhasedListBoxHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlComboBoxHydrator()
	{
		// TODO: remove unnecessary actual type argument when javac can cope
		super(asListBoxVisitor(ComponentHydrators.<T>skipChildrenOnRehydrate(new PhasedBeanHydrator<T>())));
		
		setDelegate(DEHYDRATE_INITIALIZE, asListBoxVisitor(HtmlComponentHydrators.<T>form(true, false)));
		
		setDelegate(DEHYDRATE, new HtmlComboBoxDehydrator<T>());
		
		setDelegate(REHYDRATE_PARAMETERS, asListBoxVisitor(ComponentHydrators.<T>beanProperty(
			ComboBox.SELECTED_INDEX_PROPERTY, true)));
	}
}
