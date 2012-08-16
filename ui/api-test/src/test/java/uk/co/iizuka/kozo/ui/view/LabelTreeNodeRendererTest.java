/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/LabelTreeNodeRendererTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.model.TreePath;

/**
 * Tests {@code LabelTreeNodeRenderer}.
 * 
 * @author Mark Hobson
 * @version $Id: LabelTreeNodeRendererTest.java 100497 2012-04-16 15:42:05Z mark@IIZUKA.CO.UK $
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
