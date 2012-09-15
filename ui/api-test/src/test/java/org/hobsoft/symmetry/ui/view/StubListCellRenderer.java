/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/StubListCellRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
public class StubListCellRenderer<T> implements ListCellRenderer<T>
{
	// fields -----------------------------------------------------------------
	
	private final Component component;
	
	// constructors -----------------------------------------------------------
	
	public StubListCellRenderer(Component component)
	{
		this.component = component;
	}
	
	// ListCellRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getListCellComponent(ComboBox<T> comboBox, int index)
	{
		return component;
	}
}
