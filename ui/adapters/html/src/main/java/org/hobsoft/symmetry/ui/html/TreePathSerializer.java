/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/TreePathSerializer.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.hobsoft.symmetry.util.io.Serializer;
import org.hobsoft.symmetry.util.io.SerializerContext;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TreePathSerializer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class TreePathSerializer implements Serializer<TreePath>
{
	// Serializer methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreePath readObject(ObjectInput in) throws IOException
	{
		Tree tree = (Tree) ((SerializerContext) in).getSerializerContext();
		int row = in.readInt();
		
		return tree.getPathForRow(row);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeObject(ObjectOutput out, TreePath path) throws IOException
	{
		Tree tree = (Tree) ((SerializerContext) out).getSerializerContext();
		int row = tree.getRowForPath(path);
		
		out.writeInt(row);
	}
}
