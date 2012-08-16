/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/TreeTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import com.googlecode.jtype.Generic;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.model.DefaultTreeModel;
import uk.co.iizuka.kozo.ui.model.TreeModel;
import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;

/**
 * Tests {@code Tree}.
 * 
 * @author Mark Hobson
 * @version $Id: TreeTest.java 95615 2011-11-29 10:04:50Z mark@IIZUKA.CO.UK $
 * @see Tree
 */
public class TreeTest extends AbstractComponentTest<Tree>
{
	// fields -----------------------------------------------------------------
	
	private Tree tree;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		tree = createComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newTree()
	{
		assertTreeModelEmpty(tree.getModel());
		assertEquals("selectionMode", SelectionMode.SINGLE, tree.getSelectionMode());
		assertFalse("autoExpand", tree.isAutoExpand());
		assertFalse("autoCollapse", tree.isAutoCollapse());
	}
	
	@Test
	public void newTreeWithNullModel()
	{
		tree = new Tree((TreeModel) null);
		
		assertTreeModelEmpty(tree.getModel());
		assertEquals("selectionMode", SelectionMode.SINGLE, tree.getSelectionMode());
		assertFalse("autoExpand", tree.isAutoExpand());
		assertFalse("autoCollapse", tree.isAutoCollapse());
	}
	
	@Test
	public void setModel()
	{
		TreeModel model = new DefaultTreeModel();
		
		tree.setModel(model);
		
		assertSame(model, tree.getModel());
	}
	
	@Test
	public void setModelWithNull()
	{
		tree.setModel(null);
	
		assertTreeModelEmpty(tree.getModel());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Tree createComponent()
	{
		return new Tree();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Tree> getComponentType()
	{
		return Generic.get(Tree.class);
	}
	
	// private methods --------------------------------------------------------
	
	// TODO: replace with assertEquals when DefaultTreeModel.equals implemented
	private static void assertTreeModelEmpty(TreeModel actual)
	{
		assertNotNull("Model was null", actual);
		assertNull("Root expected null", actual.getRoot());
	}
}
