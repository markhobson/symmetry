/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/TreeModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import org.hobsoft.symmetry.ui.event.TreeModelListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TreeModel.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface TreeModel
{
	void addTreeModelListener(TreeModelListener listener);
	
	void removeTreeModelListener(TreeModelListener listener);
	
	Object getRoot();
	
	Object getChild(Object parent, int index);
	
	int getChildCount(Object parent);
	
	int getChildIndex(Object parent, Object child);
	
	boolean isLeaf(Object node);
}
