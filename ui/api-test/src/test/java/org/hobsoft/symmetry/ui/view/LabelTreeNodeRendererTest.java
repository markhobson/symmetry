/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/LabelTreeNodeRendererTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@code LabelTreeNodeRenderer}.
 * 
 * @author Mark Hobson
 * @see LabelTreeNodeRenderer
 */
public class LabelTreeNodeRendererTest
{
	// fields -----------------------------------------------------------------
	
	private LabelTreeNodeRenderer renderer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		renderer = new LabelTreeNodeRenderer();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getTreeNodeComponentWithNode()
	{
		assertLabelText("x", renderer.getTreeNodeComponent(new Tree(), new TreePath("x")));
	}
	
	@Test
	public void getTreeNodeComponentWithNullNode()
	{
		assertLabelText("", renderer.getTreeNodeComponent(new Tree(), new TreePath(null)));
	}
	
	@Test(expected = NullPointerException.class)
	public void getTreeNodeComponentWithNullTree()
	{
		renderer.getTreeNodeComponent(null, new TreePath("x"));
	}
	
	@Test(expected = NullPointerException.class)
	public void getTreeNodeComponentWithNullPath()
	{
		renderer.getTreeNodeComponent(new Tree(), null);
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertLabelText(String expected, Label actualLabel)
	{
		assertNotNull("label", actualLabel);
		assertEquals("text", expected, actualLabel.getText());
	}
}
