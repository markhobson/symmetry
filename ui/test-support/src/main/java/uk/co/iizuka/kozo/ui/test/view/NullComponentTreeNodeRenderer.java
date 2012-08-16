/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/view/NullComponentTreeNodeRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.test.view;

import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.model.TreePath;
import uk.co.iizuka.kozo.ui.view.TreeNodeRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: NullComponentTreeNodeRenderer.java 99606 2012-03-16 12:46:26Z mark@IIZUKA.CO.UK $
 */
public class NullComponentTreeNodeRenderer implements TreeNodeRenderer
{
	// TODO: rename to NullTreeNodeRenderer
	
	// TreeNodeRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getTreeNodeComponent(Tree tree, TreePath path)
	{
		return null;
	}
}
