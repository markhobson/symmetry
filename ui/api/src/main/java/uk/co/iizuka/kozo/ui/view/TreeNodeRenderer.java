/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/TreeNodeRenderer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.view;

import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TreeNodeRenderer.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface TreeNodeRenderer
{
	// TODO: note somewhere in javadoc dangers of having singleton renderers
	
	Component getTreeNodeComponent(Tree tree, TreePath path);
}
