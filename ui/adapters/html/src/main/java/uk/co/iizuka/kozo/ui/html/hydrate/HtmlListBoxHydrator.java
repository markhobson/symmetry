/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlListBoxHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asListBoxVisitor;

import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;

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
