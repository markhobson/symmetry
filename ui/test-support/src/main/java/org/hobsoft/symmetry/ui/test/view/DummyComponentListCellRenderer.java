/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/view/DummyComponentListCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.view;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.view.ListCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
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
