/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/TableColumnTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static uk.co.iizuka.kozo.ui.layout.Length.pixels;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.event.TableCellListener;
import uk.co.iizuka.kozo.ui.model.DefaultTableModel;
import uk.co.iizuka.kozo.ui.test.event.DummyTableCellListener;
import uk.co.iizuka.kozo.ui.test.view.NullComponentTableCellRenderer;
import uk.co.iizuka.kozo.ui.view.TableCellRenderer;

/**
 * Tests {@code TableColumn}.
 * 
 * @author Mark Hobson
 * @version $Id: TableColumnTest.java 99804 2012-03-22 17:25:54Z mark@IIZUKA.CO.UK $
 * @see TableColumn
 */
public class TableColumnTest
{
	// fields -----------------------------------------------------------------
	
	private DefaultTableModel model;
	
	private Table table;
	
	private TableColumn column;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new DefaultTableModel();
		table = new Table(model);
		column = new TableColumn(table, 0);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newTableColumn()
	{
		assertNull("width", column.getWidth());
		assertNull("headerRenderer", column.getHeaderRenderer());
		assertNull("cellRenderer", column.getCellRenderer());
		assertArrayEquals("cellListeners", new TableCellListener[0], column.getCellListeners());
	}
	
	@Test(expected = NullPointerException.class)
	public void newTableColumnWithNullTable()
	{
		new TableColumn(null, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newTableColumnWithNegativeColumn()
	{
		new TableColumn(table, -1);
	}
	
	@Test
	public void getColumnIndex()
	{
		assertEquals(0, column.getIndex());
	}
	
	@Test
	public void setWidth()
	{
		column.setWidth(pixels(1));
		
		assertEquals("width", pixels(1), column.getWidth());
	}
	
	@Test
	public void setWidthWithNull()
	{
		column.setWidth(null);
		
		assertNull("width", column.getWidth());
	}
	
	@Test
	public void addCellListener()
	{
		TableCellListener listener = new DummyTableCellListener();
		column.addCellListener(listener);
		
		assertArrayEquals(new TableCellListener[] {listener}, column.getCellListeners());
	}
	
	@Test
	public void setHeaderRenderer()
	{
		TableCellRenderer renderer = new NullComponentTableCellRenderer();
		column.setHeaderRenderer(renderer);
		
		assertEquals(renderer, column.getHeaderRenderer());
	}
	
	@Test
	public void setHeaderRendererWithNull()
	{
		column.setHeaderRenderer(null);
		
		assertNull(column.getHeaderRenderer());
	}
	
	@Test
	public void setCellRenderer()
	{
		TableCellRenderer renderer = new NullComponentTableCellRenderer();
		column.setCellRenderer(renderer);
		
		assertEquals(renderer, column.getCellRenderer());
	}
	
	@Test
	public void setCellRendererWithNull()
	{
		column.setCellRenderer(null);
		
		assertNull(column.getCellRenderer());
	}
}
