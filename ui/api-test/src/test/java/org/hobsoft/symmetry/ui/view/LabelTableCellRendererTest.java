/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/LabelTableCellRendererTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code LabelTableCellRenderer}.
 * 
 * @author Mark Hobson
 * @see LabelTableCellRenderer
 */
public class LabelTableCellRendererTest
{
	// fields -----------------------------------------------------------------
	
	private LabelTableCellRenderer renderer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		renderer = new LabelTableCellRenderer();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getTableCellComponentWithFirstCell()
	{
		Table table = createTable(new Object[] {"x"});
		assertLabelText("x", renderer.getTableCellComponent(table, 0, 0));
	}
	
	@Test
	public void getTableCellComponentWithSecondRow()
	{
		Table table = createTable(new Object[] {"x"}, new Object[] {"y"});
		assertLabelText("y", renderer.getTableCellComponent(table, 1, 0));
	}
	
	@Test
	public void getTableCellComponentWithSecondColumn()
	{
		Table table = createTable(new Object[] {"x", "y"});
		assertLabelText("y", renderer.getTableCellComponent(table, 0, 1));
	}
	
	@Test
	public void getTableCellComponentWithNullCell()
	{
		Table table = createTable(new Object[] {null});
		assertLabelText("", renderer.getTableCellComponent(table, 0, 0));
	}
	
	@Test(expected = NullPointerException.class)
	public void getTableCellComponentWithNullTable()
	{
		renderer.getTableCellComponent(null, 0, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getTableCellComponentWithNegativeRowIndex()
	{
		renderer.getTableCellComponent(new Table(), -1, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getTableCellComponentWithTooLargeRowIndex()
	{
		Table table = createTable(new Object[] {"x"});
		renderer.getTableCellComponent(table, 1, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getTableCellComponentWithNegativeColumnIndex()
	{
		renderer.getTableCellComponent(new Table(), 0, -1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getTableCellComponentWithTooLargeColumnIndex()
	{
		Table table = createTable(new Object[] {"x"});
		renderer.getTableCellComponent(table, 0, 1);
	}
	
	// private methods --------------------------------------------------------
	
	private static Table createTable(Object[]... rows)
	{
		return new Table(new DefaultTableModel(rows));
	}
	
	private static void assertLabelText(String expected, Label actualLabel)
	{
		assertNotNull("label", actualLabel);
		assertEquals("text", expected, actualLabel.getText());
	}
}
