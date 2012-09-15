/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/view/DummyComponentTreeNodeRenderer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.view;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.view.TreeNodeRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyComponentTreeNodeRenderer.java 95278 2011-11-17 15:54:48Z mark@IIZUKA.CO.UK $
 */
public class DummyComponentTreeNodeRenderer extends DummyComponent implements TreeNodeRenderer
{
	// TreeNodeRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DummyComponent getTreeNodeComponent(Tree tree, TreePath path)
	{
		return this;
	}
}
