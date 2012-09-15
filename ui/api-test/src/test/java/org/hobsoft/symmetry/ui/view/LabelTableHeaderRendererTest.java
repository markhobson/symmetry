/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/LabelTableHeaderRendererTest.java $
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
 * Tests {@code LabelTableHeaderRenderer}.
 * 
 * @author Mark Hobson
 * @version $Id: LabelTableHeaderRendererTest.java 100497 2012-04-16 15:42:05Z mark@IIZUKA.CO.UK $
 * @see LabelTableHeaderRenderer
 */
public class LabelTableHeaderRendererTest
{
	// fields -----------------------------------------------------------------
	
	private LabelTableHeaderRenderer renderer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		renderer = new LabelTableHeaderRenderer();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getTableCellComponentWithFirstColumn()
	{
		Table table = createTable("x");
		assertLabelText("x", renderer.getTableCellComponent(table, 0, 0));
	}
	
	@Test
	public void getTableCellComponentWithSecondColumn()
	{
		Table table = createTable("x", "y");
		assertLabelText("y", renderer.getTableCellComponent(table, 0, 1));
	}
	
	@Test
	public void getTableCellComponentWithNullColumnName()
	{
		Table table = createTable((String) null);
		assertLabelText("", renderer.getTableCellComponent(table, 0, 0));
	}
	
	@Test(expected = NullPointerException.class)
	public void getTableCellComponentWithNullTable()
	{
		renderer.getTableCellComponent(null, 0, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getTableCellComponentWithNegativeColumnIndex()
	{
		renderer.getTableCellComponent(new Table(), 0, -1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getTableCellComponentWithTooLargeColumnIndex()
	{
		Table table = createTable("x");
		renderer.getTableCellComponent(table, 0, 1);
	}
	
	// private methods --------------------------------------------------------
	
	private static Table createTable(String... columnNames)
	{
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnNames(columnNames);
		
		return new Table(model);
	}
	
	private static void assertLabelText(String expected, Label actualLabel)
	{
		assertNotNull("label", actualLabel);
		assertEquals("text", expected, actualLabel.getText());
	}
}
