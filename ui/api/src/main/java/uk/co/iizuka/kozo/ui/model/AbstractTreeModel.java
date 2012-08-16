/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/AbstractTreeModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import javax.swing.event.EventListenerList;

import uk.co.iizuka.kozo.ui.event.TreeModelEvent;
import uk.co.iizuka.kozo.ui.event.TreeModelListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractTreeModel.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractTreeModel implements TreeModel
{
	// fields -----------------------------------------------------------------
	
	private final EventListenerList listeners;
	
	// constructors -----------------------------------------------------------
	
	public AbstractTreeModel()
	{
		listeners = new EventListenerList();
	}
	
	// TreeModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTreeModelListener(TreeModelListener listener)
	{
		listeners.add(TreeModelListener.class, listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener listener)
	{
		listeners.remove(TreeModelListener.class, listener);
	}
	
	// protected methods ------------------------------------------------------
	
	protected void fireNodeDeletedEvent(TreePath path)
	{
		Object[] listenerArray = listeners.getListenerList();
		TreeModelEvent event = null;
		
		for (int i = 0; i < listenerArray.length; i += 2)
		{
			if (listenerArray[i] == TreeModelListener.class)
			{
				if (event == null)
				{
					event = new TreeModelEvent(this, path);
				}
				
				((TreeModelListener) listenerArray[i + 1]).nodeDeleted(event);
			}
		}
	}
}
