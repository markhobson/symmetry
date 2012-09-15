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
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
