/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/LabelListCellRendererTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Label;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code LabelListCellRenderer}.
 * 
 * @author Mark Hobson
 * @version $Id: LabelListCellRendererTest.java 100497 2012-04-16 15:42:05Z mark@IIZUKA.CO.UK $
 * @see LabelListCellRenderer
 */
public class LabelListCellRendererTest
{
	// fields -----------------------------------------------------------------
	
	private LabelListCellRenderer<Object> renderer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		renderer = new LabelListCellRenderer<Object>();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getListCellComponentWithFirstItem()
	{
		assertLabelText("x", renderer.getListCellComponent(new ComboBox<Object>("x"), 0));
	}
	
	@Test
	public void getListCellComponentWithSecondItem()
	{
		assertLabelText("y", renderer.getListCellComponent(new ComboBox<Object>("x", "y"), 1));
	}
	
	@Test
	public void getListCellComponentWithNullItem()
	{
		assertLabelText("", renderer.getListCellComponent(new ComboBox<Object>((Object) null), 0));
	}
	
	@Test(expected = NullPointerException.class)
	public void getListCellComponentWithNullComboBox()
	{
		renderer.getListCellComponent(null, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getListCellComponentWithNegativeItemIndex()
	{
		renderer.getListCellComponent(new ComboBox<Object>(), -1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getListCellComponentWithTooLargeItemIndex()
	{
		renderer.getListCellComponent(new ComboBox<Object>("x"), 1);
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertLabelText(String expected, Label actualLabel)
	{
		assertNotNull("label", actualLabel);
		assertEquals("text", expected, actualLabel.getText());
	}
}
