/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.event.TableCellListener;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.hobsoft.symmetry.ui.test.event.DummyTableCellListener;
import org.hobsoft.symmetry.ui.test.view.NullComponentTableCellRenderer;
import org.hobsoft.symmetry.ui.view.TableCellRenderer;
import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.layout.Length.pixels;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code TableColumn}.
 * 
 * @author Mark Hobson
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