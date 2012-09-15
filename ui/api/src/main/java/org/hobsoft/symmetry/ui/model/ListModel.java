/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/ListModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ListModel.java 87354 2011-04-27 21:59:27Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public interface ListModel<T>
{
	// TODO: extend Iterable<Object>?  would complicate direct implementors
	
	// TODO: do we need item listeners?
	
	T getItem(int index);
	
	int getItemCount();
}
