/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/model/ListModelAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing.model;

import javax.swing.event.ListDataListener;

import uk.co.iizuka.kozo.ui.model.ListModel;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ListModelAdapter.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class ListModelAdapter implements javax.swing.ListModel
{
	// fields -----------------------------------------------------------------
	
	private final ListModel<?> model;
	
	// constructors -----------------------------------------------------------
	
	public ListModelAdapter(ListModel<?> model)
	{
		this.model = model;
	}
	
	// ListModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize()
	{
		return model.getItemCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getElementAt(int index)
	{
		return model.getItem(index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListDataListener(ListDataListener listener)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListDataListener(ListDataListener listener)
	{
		// TODO: implement
	}
}
