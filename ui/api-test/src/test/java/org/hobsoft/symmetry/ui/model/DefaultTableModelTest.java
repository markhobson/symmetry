/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/DefaultTableModelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hobsoft.symmetry.ui.event.TableModelEvent;
import org.hobsoft.symmetry.ui.event.TableModelListener;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Collections.singletonList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code DefaultTableModel}.
 * 
 * @author Mark Hobson
 * @see DefaultTableModel
 */
@RunWith(JMock.class)
public class DefaultTableModelTest
{
	// fields -----------------------------------------------------------------
	
	private Mockery mockery = new JUnit4Mockery();
	
	private DefaultTableModel model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new DefaultTableModel();
	}

	// tests ------------------------------------------------------------------

	@Test
	public void newDefaultTableModel()
	{
		assertRows(model);
	}
	
	@Test
	public void newDefaultTableModelArray()
	{
		Object[][] rows = createRows();
		model = new DefaultTableModel(rows);
		
		assertRows(model, rows);
	}
	
	@Test
	public void newDefaultTableModelCollection()
	{
		List<List<Object>> rows = createRowsAsList();
		model = new DefaultTableModel(rows);
		
		assertRows(model, rows);
	}
	
	@Test
	public void setSizeGrows()
	{
		model.addRow("a");
		model.setSize(2, 2);
		
		assertRows(model, new Object[][] {{"a", null}, {null, null}});
	}
	
	@Test
	public void setSizeShrinks()
	{
		model.addRow("a", "b");
		model.addRow("c", "d");
		model.setSize(1, 1);
		
		assertRows(model, new Object[][] {{"a"}});
	}
	
	@Test
	public void setSizeFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setSize(1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSizeWithNegativeRows()
	{
		model.setSize(-1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSizeWithNegativeColumns()
	{
		model.setSize(0, -1);
	}
	
	@Test
	public void setRowCountGrowsRows()
	{
		model.addRow("a", "b");
		model.setRowCount(2);
		
		assertRows(model, new Object[][] {{"a", "b"}, {null, null}});
	}
	
	@Test
	public void setRowCountShrinksRows()
	{
		model.addRow("a", "b");
		model.addRow("c", "d");
		model.setRowCount(1);
		
		assertRows(model, new Object[][] {{"a", "b"}});
	}
	
	@Test
	public void setRowCountFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setRowCount(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setRowCountWithNegative()
	{
		model.setRowCount(-1);
	}
	
	@Test
	public void setColumnCountGrowsColumns()
	{
		model.addRow("a");
		model.addRow("b");
		model.setColumnCount(2);
		
		assertRows(model, new Object[][] {{"a", null}, {"b", null}});
	}
	
	@Test
	public void setColumnCountShrinksColumns()
	{
		model.addRow("a", "b");
		model.addRow("c", "d");
		model.setColumnCount(1);
		
		assertRows(model, new Object[][] {{"a"}, {"c"}});
	}
	
	@Test
	public void setColumnCountFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setColumnCount(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnCountWithNegative()
	{
		model.setColumnCount(-1);
	}
	
	@Test
	public void getColumnNameWithFirstIndex()
	{
		model.setColumnNames("a", "b", "c");
		
		assertEquals("a", model.getColumnName(0));
	}
	
	@Test
	public void getColumnNameWithLastIndex()
	{
		model.setColumnNames("a", "b", "c");
		
		assertEquals("c", model.getColumnName(2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getColumnNameWithNegativeIndex()
	{
		model.getColumnName(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getColumnNameWithTooLargeIndex()
	{
		model.addRow(createRow());
		model.getColumnName(4);
	}
	
	@Test
	public void getColumnNames()
	{
		model.setColumnNames("a", "b", "c");
		
		assertArrayEquals(new String[] {"a", "b", "c"}, model.getColumnNames());
	}
	
	@Test
	public void setColumnNameWithFirstIndex()
	{
		model.addRow(createRow());
		model.setColumnName(0, "a");
		
		assertColumnNames(model, "a", null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnNameWithNegativeIndex()
	{
		model.setColumnName(-1, "a");
	}
	
	@Test
	public void setColumnNameGrowsColumns()
	{
		model.setColumnName(2, "a");
		
		assertColumnNames(model, null, null, "a");
	}
	
	@Test
	public void setColumnNameFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setColumnName(0, "a");
	}
	
	@Test
	public void setColumnNames()
	{
		model.setColumnNames("a", "b", "c");
		
		assertColumnNames(model, "a", "b", "c");
	}
	
	@Test
	public void setColumnNamesFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setColumnNames("a", "b", "c");
	}
	
	@Test
	public void getColumnClassWithFirstIndex()
	{
		Class<?>[] columnClasses = createColumnClasses();
		model.setColumnClasses(columnClasses);
		
		assertEquals(columnClasses[0], model.getColumnClass(0));
	}
	
	@Test
	public void getColumnClassWithLastIndex()
	{
		Class<?>[] columnClasses = createColumnClasses();
		model.setColumnClasses(columnClasses);
		
		assertEquals(columnClasses[2], model.getColumnClass(2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getColumnClassWithNegativeIndex()
	{
		model.getColumnClass(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getColumnClassWithTooLargeIndex()
	{
		model.addRow(createRow());
		model.getColumnClass(4);
	}
	
	@Test
	public void getColumnClasses()
	{
		Class<?>[] columnClasses = createColumnClasses();
		model.setColumnClasses(columnClasses);
		
		assertArrayEquals(columnClasses, model.getColumnClasses());
	}
	
	@Test
	public void setColumnClassWithFirstIndex()
	{
		model.addRow(createRow());
		model.setColumnClass(0, String.class);
		
		assertColumnClasses(model, String.class, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setColumnClassWithNegativeIndex()
	{
		model.setColumnClass(-1, String.class);
	}
	
	@Test
	public void setColumnClassGrowsColumns()
	{
		model.setColumnClass(2, String.class);
		
		assertColumnClasses(model, null, null, String.class);
	}
	
	@Test
	public void setColumnClassFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setColumnClass(0, String.class);
	}
	
	@Test
	public void setColumnClasses()
	{
		Class<?>[] columnClasses = createColumnClasses();
		model.setColumnClasses(columnClasses);
		
		assertColumnClasses(model, columnClasses);
	}
	
	@Test
	public void setColumnClassesFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setColumnClasses(createColumnClasses());
	}
	
	@Test
	public void getValueAtFirstRow()
	{
		Object[][] rows = createRows();
		model.addRows(rows);
		
		assertEquals(rows[0][0], model.getValueAt(0, 0));
	}
	
	@Test
	public void getValueAtLastRow()
	{
		Object[][] rows = createRows();
		model.addRows(rows);
		
		assertEquals(rows[2][0], model.getValueAt(2, 0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getValueAtNegativeRow()
	{
		model.addRows(createRows());
		model.getValueAt(-1, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getValueAtTooLargeRow()
	{
		model.addRows(createRows());
		model.getValueAt(3, 0);
	}
	
	@Test
	public void getValueAtGrownCell()
	{
		model.addRow(createCell());
		model.addRow(createCell(), createCell());
		
		assertNull(model.getValueAt(0, 1));
	}
	
	@Test
	public void getValueAtFirstColumn()
	{
		Object[] row = createRow();
		model.addRow(row);
		
		assertEquals(row[0], model.getValueAt(0, 0));
	}
	
	@Test
	public void getValueAtLastColumn()
	{
		Object[] row = createRow();
		model.addRow(row);
		
		assertEquals(row[2], model.getValueAt(0, 2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getValueAtNegativeColumn()
	{
		model.addRows(createRows());
		model.getValueAt(0, -1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getValueAtTooLargeColumn()
	{
		model.addRows(createRows());
		model.getValueAt(0, 3);
	}
	
	@Test
	public void setValueAtFirstRow()
	{
		model.addRow("a");
		model.addRow("b");
		model.addRow("c");
		model.setValueAt(0, 0, "x");
		
		assertEquals("x", model.getValueAt(0, 0));
	}
	
	@Test
	public void setValueAtLastRow()
	{
		model.addRow("a");
		model.addRow("b");
		model.addRow("c");
		model.setValueAt(2, 0, "x");
		
		assertEquals("x", model.getValueAt(2, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setValueAtNegativeRow()
	{
		model.setValueAt(-1, 0, "x");
	}
	
	@Test
	public void setValueAtGrowsRows()
	{
		model.addRow("a", "b", "c");
		model.setValueAt(1, 0, "x");
		
		assertRows(model, new Object[][] {{"a", "b", "c"}, {"x", null, null}});
	}
	
	@Test
	public void setValueAtFirstColumn()
	{
		model.addRow("a", "b", "c");
		model.setValueAt(0, 0, "x");
		
		assertEquals("x", model.getValueAt(0, 0));
	}
	
	@Test
	public void setValueAtLastColumn()
	{
		model.addRow("a", "b", "c");
		model.setValueAt(0, 2, "x");
		
		assertEquals("x", model.getValueAt(0, 2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setValueAtNegativeColumn()
	{
		model.setValueAt(0, -1, "x");
	}
	
	@Test
	public void setValueAtGrowsColumns()
	{
		model.addRow("a");
		model.addRow("b");
		model.addRow("c");
		model.setValueAt(0, 1, "x");
		
		assertRows(model, new Object[][] {{"a", "x"}, {"b", null}, {"c", null}});
	}
	
	@Test
	public void setValueFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setValueAt(0, 0, "x");
	}
	
	@Test
	public void clear()
	{
		model.addRows(createRows());
		
		model.clear();
		
		assertRows(model);
	}
	
	@Test
	public void clearFiresTableChangedEvent()
	{
		model.addRows(createRows());
		
		model.addTableModelListener(mockTableModelListener(model));
		model.clear();
	}
	
	@Test
	public void addRowArray()
	{
		Object[] row = createRow();
		model.addRow(row);
		
		assertRows(model, row);
	}
	
	@Test
	public void addRowArrayFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.addRow(createRow());
	}
	
	@Test
	public void addRowCollection()
	{
		List<Object> row = createRowAsList();
		model.addRow(row);
		
		assertRows(model, singletonList(row));
	}
	
	@Test
	public void addRowCollectionFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.addRow(createRowAsList());
	}
	
	@Test
	public void addRowsArray()
	{
		Object[][] rows = createRows();
		model.addRows(rows);
		
		assertRows(model, rows);
	}
	
	@Test
	public void addRowsArrayFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.addRows(createRows());
	}
	
	@Test
	public void addRowsCollection()
	{
		List<List<Object>> rows = createRowsAsList();
		model.addRows(rows);
		
		assertRows(model, rows);
	}
	
	@Test
	public void addRowsCollectionFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.addRows(createRowsAsList());
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		DefaultTableModel model1 = createModel();
		DefaultTableModel model2 = createModel();
		
		assertEquals(model1.hashCode(), model2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		DefaultTableModel model1 = createModel();
		DefaultTableModel model2 = createModel();
		
		assertTrue(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentColumnName()
	{
		DefaultTableModel model1 = createModel();
		DefaultTableModel model2 = createModel();
		model2.setColumnName(0, "d");
		
		assertFalse(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentColumnClass()
	{
		DefaultTableModel model1 = createModel();
		DefaultTableModel model2 = createModel();
		model2.setColumnClass(0, Double.class);
		
		assertFalse(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentRowCount()
	{
		DefaultTableModel model1 = createModel();
		DefaultTableModel model2 = createModel();
		model2.setRowCount(2);
		
		assertFalse(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentColumnCount()
	{
		DefaultTableModel model1 = createModel();
		DefaultTableModel model2 = createModel();
		model2.setColumnCount(2);
		
		assertFalse(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentValue()
	{
		DefaultTableModel model1 = createModel();
		DefaultTableModel model2 = createModel();
		model2.setValueAt(0, 0, "x");
		
		assertFalse(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		DefaultTableModel model = createModel();
		Object object = new Object();
		
		assertFalse(model.equals(object));
	}
	
	@Test
	public void equalsWithNull()
	{
		DefaultTableModel model = createModel();
		
		// workaround Checkstyle bug 2809655
		// CHECKSTYLE:OFF
		assertFalse(model.equals(null));
		// CHECKSTYLE:ON
	}
	
	// private methods --------------------------------------------------------
	
	private static DefaultTableModel createModel()
	{
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnNames("a", "b", "c");
		model.setColumnClasses(String.class, Integer.class, Long.class);
		model.addRow("p", 1, 4L);
		model.addRow("q", 2, 5L);
		model.addRow("r", 3, 6L);
		return model;
	}
	
	private static Class<?>[] createColumnClasses()
	{
		return new Class[] {String.class, Integer.class, Long.class};
	}
	
	private static Object createCell()
	{
		return new Object();
	}
	
	private static Object[] createRow()
	{
		return new Object[] {createCell(), createCell(), createCell()};
	}
	
	private static List<Object> createRowAsList()
	{
		return Arrays.asList(createRow());
	}
	
	private static Object[][] createRows()
	{
		return new Object[][] {createRow(), createRow(), createRow()};
	}
	
	private static List<List<Object>> createRowsAsList()
	{
		return toLists(createRows());
	}
	
	private static void assertColumnNames(TableModel actual, String... expectedColumnNames)
	{
		assertEquals("Column names", Arrays.asList(expectedColumnNames), toColumnNameList(actual));
	}
	
	private static void assertColumnClasses(TableModel actual, Class<?>... expectedColumnClasses)
	{
		assertEquals("Column classes", Arrays.asList(expectedColumnClasses), toColumnClassList(actual));
	}
	
	private static List<String> toColumnNameList(TableModel model)
	{
		List<String> columnNames = new ArrayList<String>();
		
		for (int column = 0; column < model.getColumnCount(); column++)
		{
			columnNames.add(model.getColumnName(column));
		}
		
		return columnNames;
	}
	
	private static List<Class<?>> toColumnClassList(TableModel model)
	{
		List<Class<?>> columnClasses = new ArrayList<Class<?>>();
		
		for (int column = 0; column < model.getColumnCount(); column++)
		{
			columnClasses.add(model.getColumnClass(column));
		}
		
		return columnClasses;
	}
	
	private static void assertRows(TableModel actual, Object[]... expectedRows)
	{
		assertRows(actual, toLists(expectedRows));
	}
	
	private static void assertRows(TableModel actual, List<List<Object>> expectedRows)
	{
		assertEquals("Cells", expectedRows, toRowList(actual));
	}
	
	private static List<List<Object>> toRowList(TableModel model)
	{
		List<List<Object>> rows = new ArrayList<List<Object>>();
		
		for (int row = 0; row < model.getRowCount(); row++)
		{
			List<Object> cells = new ArrayList<Object>();
			
			for (int column = 0; column < model.getColumnCount(); column++)
			{
				cells.add(model.getValueAt(row, column));
			}
			
			rows.add(cells);
		}
		
		return rows;
	}
	
	private static List<List<Object>> toLists(Object[][] arrays)
	{
		List<List<Object>> lists = new ArrayList<List<Object>>();
		
		for (Object[] array : arrays)
		{
			lists.add(Arrays.asList(array));
		}
		
		return lists;
	}
	
	private TableModelListener mockTableModelListener(TableModel model)
	{
		final TableModelListener listener = mockery.mock(TableModelListener.class);
		
		mockery.checking(new Expectations() { {
			// TODO: examine event when equals or matcher implemented
			oneOf(listener).tableChanged(with(any(TableModelEvent.class)));
		} });
		
		return listener;
	}
}
