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

import java.io.Serializable;
import java.util.EventListener;

import org.hobsoft.symmetry.support.bean.EventListeners;
import org.hobsoft.symmetry.ui.event.TableCellListener;
import org.hobsoft.symmetry.ui.event.TableEvent;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.hobsoft.symmetry.ui.model.TableModel;
import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.event.DummyTableCellListener;
import org.hobsoft.symmetry.ui.test.view.NullComponentTableCellRenderer;
import org.hobsoft.symmetry.ui.view.TableCellRenderer;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code Table}.
 * 
 * @author Mark Hobson
 * @see Table
 */
public class TableTest extends AbstractComponentTest<Table>
{
	// TODO: some of these tests should probably move to TableColumnTest
	
	// fields -----------------------------------------------------------------
	
	private DefaultTableModel model;
	
	private Table table;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new DefaultTableModel();
		table = new Table(model);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newTable()
	{
		assertEquals("model", new DefaultTableModel(), table.getModel());
		assertEquals("visibleRowCount", 20, table.getVisibleRowCount());
		assertEquals("firstVisibleRowIndex", 0, table.getFirstVisibleRowIndex());
	}
	
	@Test
	public void newTableWithNullModel()
	{
		table = new Table(null);
		
		assertEquals("model", new DefaultTableModel(), table.getModel());
		assertEquals("visibleRowCount", 20, table.getVisibleRowCount());
		assertEquals("firstVisibleRowIndex", 0, table.getFirstVisibleRowIndex());
	}
	
	@Test
	public void addCellListener()
	{
		final TableCellListener listener = getMockery().mock(TableCellListener.class);
		final TableEvent event = new TableEvent(table, 0);
		
		getMockery().checking(new Expectations() { {
			oneOf(listener).tableCellSelected(event);
		} });
		
		table.addCellListener(listener);
		table.fireCellSelected(event);
	}
	
	@Test
	public void addCellListenerForColumn()
	{
		model.setColumnCount(1);
		
		final TableCellListener listener = getMockery().mock(TableCellListener.class);
		final TableEvent event = new TableEvent(table, 0);
		
		getMockery().checking(new Expectations() { {
			oneOf(listener).tableCellSelected(event);
		} });
		
		table.getColumn(0).addCellListener(listener);
		table.fireCellSelected(event);
	}
	
	@Test
	public void addCellListenerForColumnWhenDifferent()
	{
		model.setColumnCount(1);
		
		final TableCellListener listener = getMockery().mock(TableCellListener.class);
		
		table.getColumn(0).addCellListener(listener);
		table.fireCellSelected(new TableEvent(table, 1));
	}
	
	@Test
	public void getCellListenersWithNoListeners()
	{
		assertArrayEquals(new TableCellListener[0], table.getCellListeners());
	}
	
	@Test
	public void getCellListenersWithListener()
	{
		TableCellListener listener = new DummyTableCellListener();
		table.addCellListener(listener);
		
		assertArrayEquals(new TableCellListener[] {listener}, table.getCellListeners());
	}
	
	@Test
	public void getCellListenersWithColumnListener()
	{
		model.setColumnCount(1);
		
		TableCellListener listener = new DummyTableCellListener();
		table.getColumn(0).addCellListener(listener);
		
		assertEventListeners(new EventListener[] {listener}, table.getCellListeners());
	}
	
	@Test
	public void getCellListenersForColumnWithNoListeners()
	{
		model.setColumnCount(1);
		
		assertArrayEquals(new TableCellListener[] {}, table.getColumn(0).getCellListeners());
	}
	
	@Test
	public void getCellListenersForColumnWithListener()
	{
		model.setColumnCount(1);
		
		TableCellListener listener = new DummyTableCellListener();
		table.addCellListener(listener);
		
		assertArrayEquals(new TableCellListener[] {listener}, table.getColumn(0).getCellListeners());
	}
	
	@Test
	public void getCellListenersForColumnWithColumnListener()
	{
		model.setColumnCount(1);
		
		TableCellListener listener = new DummyTableCellListener();
		table.getColumn(0).addCellListener(listener);
		
		assertArrayEquals(new TableCellListener[] {listener}, table.getColumn(0).getCellListeners());
	}
	
	@Test
	public void getCellListenersForColumnWithDifferentColumnListener()
	{
		model.setColumnCount(2);
		
		TableCellListener listener = new DummyTableCellListener();
		table.getColumn(0).addCellListener(listener);
		
		assertArrayEquals(new TableCellListener[] {}, table.getColumn(1).getCellListeners());
	}
	
	@Test
	public void onSelect()
	{
		@SuppressWarnings("unchecked")
		final Closure<TableEvent> closure = getMockery().mock(Closure.class);
		final TableEvent event = new TableEvent(table, 0, 0);
		
		getMockery().checking(new Expectations() { {
			oneOf(closure).apply(event);
		} });
		
		table.onSelect(closure);
		table.fireCellSelected(event);
	}
	
	@Test
	public void onSelectForColumn()
	{
		model.setColumnCount(1);
		
		@SuppressWarnings("unchecked")
		final Closure<TableEvent> closure = getMockery().mock(Closure.class);
		final TableEvent event = new TableEvent(table, 0, 0);
		
		getMockery().checking(new Expectations() { {
			oneOf(closure).apply(event);
		} });
		
		table.getColumn(0).onSelect(closure);
		table.fireCellSelected(event);
	}
	
	@Test
	public void onSelectForColumnWhenDifferent()
	{
		model.setColumnCount(1);
		
		@SuppressWarnings("unchecked")
		Closure<TableEvent> closure = getMockery().mock(Closure.class);
		
		table.getColumn(0).onSelect(closure);
		table.fireCellSelected(new TableEvent(table, 0, 1));
	}
	
	@Test
	public void doSelect()
	{
		final TableCellListener listener = getMockery().mock(TableCellListener.class);
		
		getMockery().checking(new Expectations() { {
			oneOf(listener).tableCellSelected(new TableEvent(table, 1, 2));
		} });
		
		table.addCellListener(listener);
		table.doSelect(1, 2);
	}
	
	@Test
	public void setModel()
	{
		TableModel model = new DefaultTableModel();
		
		table.setModel(model);
		
		assertSame(model, table.getModel());
	}
	
	@Test
	public void setModelWithNull()
	{
		table.setModel(null);
		
		assertEquals(new DefaultTableModel(), table.getModel());
	}
	
	@Test
	public void setFirstVisibleRowIndex()
	{
		table.setFirstVisibleRowIndex(1);
		
		assertEquals(1, table.getFirstVisibleRowIndex());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setFirstVisibleRowIndexWithNegative()
	{
		table.setFirstVisibleRowIndex(-1);
	}
	
	@Test
	public void setVisibleRowCount()
	{
		table.setVisibleRowCount(1);
		
		assertEquals(1, table.getVisibleRowCount());
	}
	
	@Test
	public void setVisibleRowCountWithZero()
	{
		table.setVisibleRowCount(0);
		
		assertEquals(0, table.getVisibleRowCount());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setVisibleRowCountWithNegative()
	{
		table.setVisibleRowCount(-1);
	}
	
	@Test
	public void setHeaderRenderer()
	{
		TableCellRenderer renderer = new NullComponentTableCellRenderer();
		table.setHeaderRenderer(renderer);
		
		assertEquals(renderer, table.getHeaderRenderer());
	}
	
	@Test(expected = NullPointerException.class)
	public void setHeaderRendererWithNull()
	{
		table.setHeaderRenderer(null);
	}
	
	@Test
	public void setCellRenderer()
	{
		model.setSize(1, 1);
		
		TableCellRenderer renderer = new NullComponentTableCellRenderer();
		table.setCellRenderer(renderer);
		
		assertEquals(renderer, table.getCellRenderer(0, 0));
	}
	
	@Test
	public void setCellRendererWithClass()
	{
		model.setSize(1, 1);
		model.setColumnClass(0, String.class);
		
		TableCellRenderer typeRenderer = new NullComponentTableCellRenderer();
		table.setCellRenderer(String.class, typeRenderer);
		
		assertEquals(typeRenderer, table.getCellRenderer(0, 0));
	}
	
	@Test
	public void setCellRendererWithSuperclass()
	{
		model.setSize(1, 1);
		model.setColumnClass(0, Integer.class);
		
		TableCellRenderer typeRenderer = new NullComponentTableCellRenderer();
		table.setCellRenderer(Number.class, typeRenderer);
		
		assertEquals(typeRenderer, table.getCellRenderer(0, 0));
	}
	
	@Test
	public void setCellRendererWithInterface()
	{
		model.setSize(1, 1);
		model.setColumnClass(0, Integer.class);
		
		TableCellRenderer typeRenderer = new NullComponentTableCellRenderer();
		table.setCellRenderer(Comparable.class, typeRenderer);
		
		assertEquals(typeRenderer, table.getCellRenderer(0, 0));
	}
	
	@Test
	public void setCellRendererWithSuperclassInterface()
	{
		model.setSize(1, 1);
		model.setColumnClass(0, Integer.class);
		
		TableCellRenderer typeRenderer = new NullComponentTableCellRenderer();
		table.setCellRenderer(Serializable.class, typeRenderer);
		
		assertEquals(typeRenderer, table.getCellRenderer(0, 0));
	}
	
	@Test
	public void setCellRendererWithColumn()
	{
		model.setSize(1, 1);
		
		TableCellRenderer columnRenderer = new NullComponentTableCellRenderer();
		table.getColumn(0).setCellRenderer(columnRenderer);
		
		assertEquals(columnRenderer, table.getCellRenderer(0, 0));
	}
	
	@Test
	public void setCellRendererWithClassAndColumn()
	{
		model.setSize(1, 1);
		model.setColumnClass(0, String.class);
		
		TableCellRenderer typeRenderer = new NullComponentTableCellRenderer();
		table.setCellRenderer(String.class, typeRenderer);
		
		TableCellRenderer columnRenderer = new NullComponentTableCellRenderer();
		table.getColumn(0).setCellRenderer(columnRenderer);
		
		assertEquals(columnRenderer, table.getCellRenderer(0, 0));
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Table createComponent()
	{
		return new Table();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeToken<Table> getComponentType()
	{
		return TypeToken.of(Table.class);
	}

	// private methods --------------------------------------------------------
	
	private static void assertEventListeners(EventListener[] expected, EventListener[] actual)
	{
		EventListener[] unproxiedActual = EventListeners.unproxy(actual);
		
		assertArrayEquals(expected, unproxiedActual);
	}
}
