/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/view/DummyComponentListCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.test.view;

import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.view.ListCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyComponentListCellRenderer.java 95834 2011-12-01 09:40:04Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public class DummyComponentListCellRenderer<T> extends DummyComponent implements ListCellRenderer<T>
{
	// ListCellRenderer methods -------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getListCellComponent(ComboBox<T> comboBox, int index)
	{
		return this;
	}
}
