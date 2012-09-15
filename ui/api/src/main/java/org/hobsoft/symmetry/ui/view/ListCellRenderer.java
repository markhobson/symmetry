/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/ListCellRenderer.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the item type
 */
public interface ListCellRenderer<T>
{
	// Note: ComboBox used here since ListCellRenderer is used for both ListBox and ComboBox. Perhaps rename interface?
	
	Component getListCellComponent(ComboBox<T> comboBox, int index);
}
