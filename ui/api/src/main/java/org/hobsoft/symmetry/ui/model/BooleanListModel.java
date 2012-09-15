/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/BooleanListModel.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: BooleanListModel.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
class BooleanListModel implements ListModel<Boolean>
{
	// ListModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getItem(int index)
	{
		if (index < 0 || index > 1)
		{
			throw new IndexOutOfBoundsException("index must be 0 or 1: " + index);
		}
		
		return (index == 0) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getItemCount()
	{
		return 2;
	}
}
