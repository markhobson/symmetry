/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/view/NullComponentListCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.view;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.view.ListCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: NullComponentListCellRenderer.java 99606 2012-03-16 12:46:26Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public class NullComponentListCellRenderer<T> implements ListCellRenderer<T>
{
	// TODO: rename to NullListCellRenderer
	
	// ListCellRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getListCellComponent(ComboBox<T> comboBox, int index)
	{
		return null;
	}
}
