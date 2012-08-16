/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/model/TreePathAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing.model;

import uk.co.iizuka.kozo.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TreePathAdapter.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
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
