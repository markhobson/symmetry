/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/model/ComboBoxModelAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing.model;

import javax.swing.ComboBoxModel;

import org.hobsoft.symmetry.ui.model.ListModel;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ComboBoxModelAdapter extends ListModelAdapter implements ComboBoxModel
{
	// fields -----------------------------------------------------------------
	
	private Object selectedItem;
	
	// public methods ---------------------------------------------------------
	
	public ComboBoxModelAdapter(ListModel<?> model)
	{
		super(model);
	}
	
	// ComboBoxModel methods --------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getSelectedItem()
	{
		return selectedItem;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSelectedItem(Object item)
	{
		selectedItem = item;
	}
}
