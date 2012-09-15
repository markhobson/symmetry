/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/LabelListCellRenderer.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Label;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: LabelListCellRenderer.java 100497 2012-04-16 15:42:05Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public class LabelListCellRenderer<T> extends Label implements ListCellRenderer<T>
{
	// constructors -----------------------------------------------------------
	
	public LabelListCellRenderer()
	{
		setTransient(true);
	}
	
	// ListCellRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Label getListCellComponent(ComboBox<T> comboBox, int index)
	{
		checkNotNull(comboBox, "comboBox cannot be null");
		
		T item = comboBox.getModel().getItem(index);
		
		setText(firstNonNull(item, "").toString());
		
		return this;
	}
}
