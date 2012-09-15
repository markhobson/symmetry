/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/LabelTreeNodeRenderer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class LabelTreeNodeRenderer extends Label implements TreeNodeRenderer
{
	// constructors -----------------------------------------------------------
	
	public LabelTreeNodeRenderer()
	{
		setTransient(true);
	}
	
	// TreeNodeRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Label getTreeNodeComponent(Tree tree, TreePath path)
	{
		checkNotNull(tree, "tree cannot be null");
		checkNotNull(path, "path cannot be null");
		
		Object node = path.getNode();
		
		setText(firstNonNull(node, "").toString());
		
		return this;
	}
}
