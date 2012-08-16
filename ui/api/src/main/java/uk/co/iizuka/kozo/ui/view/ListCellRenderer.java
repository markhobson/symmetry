/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/ListCellRenderer.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.view;

import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ListCellRenderer.java 87354 2011-04-27 21:59:27Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public interface ListCellRenderer<T>
{
	// Note: ComboBox used here since ListCellRenderer is used for both ListBox and ComboBox. Perhaps rename interface?
	
	Component getListCellComponent(ComboBox<T> comboBox, int index);
}
