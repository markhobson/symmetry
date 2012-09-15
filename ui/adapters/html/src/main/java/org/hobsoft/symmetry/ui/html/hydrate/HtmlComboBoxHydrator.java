/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlComboBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedListBoxHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asListBoxVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code ComboBox} component using an HTML {@code <select/>} tag.
 * 
 * @author Mark Hobson
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
