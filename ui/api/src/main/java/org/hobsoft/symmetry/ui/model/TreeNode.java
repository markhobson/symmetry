/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/TreeNode.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TreeNode.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface TreeNode
{
	TreeNode getChildAt(int index);
	
	int getChildCount();
	
	int getIndex(TreeNode child);
}
