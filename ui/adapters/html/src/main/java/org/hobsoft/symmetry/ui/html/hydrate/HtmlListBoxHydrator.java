/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlListBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asListBoxVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code ListBox} component using an HTML {@code <select/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlListBoxHydrator.java 98878 2012-02-29 16:54:12Z mark@IIZUKA.CO.UK $
 * @see ListBox
 * @param <T>
 *            the list box type this visitor can visit
 */
public class HtmlListBoxHydrator<T extends ListBox<?>> extends HtmlComboBoxHydrator<T>
{
	// constructors -----------------------------------------------------------

	public HtmlListBoxHydrator()
	{
		setDelegate(DEHYDRATE, new HtmlListBoxDehydrator<T>());
		
		setDelegate(REHYDRATE_PARAMETERS,
			asListBoxVisitor(ComponentHydrators.<T>beanProperty(ListBox.SELECTED_INDEXES_PROPERTY, false)));
	}
}
