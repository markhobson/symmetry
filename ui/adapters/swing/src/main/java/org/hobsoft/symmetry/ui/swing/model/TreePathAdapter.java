/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/model/TreePathAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing.model;

import org.hobsoft.symmetry.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TreePathAdapter extends TreePath
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// constructors -----------------------------------------------------------
	
	public TreePathAdapter(javax.swing.tree.TreePath treePath)
	{
		super(treePath.getParentPath() == null ? null : new TreePathAdapter(treePath.getParentPath()),
			treePath.getLastPathComponent());
	}
}
