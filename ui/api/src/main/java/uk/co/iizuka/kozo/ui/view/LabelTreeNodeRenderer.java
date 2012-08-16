/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/LabelTreeNodeRenderer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.view;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: LabelTreeNodeRenderer.java 100497 2012-04-16 15:42:05Z mark@IIZUKA.CO.UK $
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
