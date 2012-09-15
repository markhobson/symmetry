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

import org.hobsoft.symmetry.ui.layout.GridLayoutData;
import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static java.util.Arrays.asList;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Grid}.
 * 
 * @author Mark Hobson
 * @see Grid
 */
public class GridTest extends AbstractComponentTest<Grid>
{
	// fields -----------------------------------------------------------------
	
	private Grid grid;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		grid = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setColumnCountGrowsColumns()
	{
		grid.setColumnCount(3);
		
		assertEquals(new GridColumn(), grid.getColumn(2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setColumnCountCropsColumns()
	{
		grid.setColumnCount(1);
		
		grid.getColumn(1);
	}
	
	@Test
	public void getRowCountWhenEmpty()
	{
		assertEquals(0, grid.getRowCount());
	}
	
	@Test
	public void getRowCountWhenRowIsNotFull()
	{
		grid.add(createChild());
		
		assertEquals(1, grid.getRowCount());
	}
	
	@Test
	public void getRowCountWhenRowIsFull()
	{
		grid.add(createChild(), createChild());
		
		assertEquals(1, grid.getRowCount());
	}
	
	@Test
	public void getRowCountWhenRows()
	{
		grid.add(createChild(), createChild(), createChild(), createChild());
		
		assertEquals(2, grid.getRowCount());
	}
	
	@Test
	public void getRowIndexWithFirstRowChildIndex()
	{
		grid.add(createChild());
		
		assertEquals(0, grid.getRowIndex(0));
	}
	
	@Test
	public void getRowIndexWithFirstRowSecondChildIndex()
	{
		grid.add(createChild());
		grid.add(createChild());
		
		assertEquals(0, grid.getRowIndex(1));
	}
	
	@Test
	public void getRowIndexWithSecondRowChildIndex()
	{
		grid.setColumnCount(1);
		grid.add(createChild());
		grid.add(createChild());
		
		assertEquals(1, grid.getRowIndex(1));
	}
	
	// TODO: getRowIndexWithSecondRowChildIndexAndPrecedingRowSpan when rowSpan implemented
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowIndexWithNegativeChildIndex()
	{
		grid.getRowIndex(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowIndexWithTooLargeChildIndex()
	{
		grid.getRowIndex(0);
	}
	
	@Test
	public void getRowIndexWithFirstRowChild()
	{
		Component child = createChild();
		grid.add(child);
		
		assertEquals(0, grid.getRowIndex(child));
	}
	
	@Test(expected = NullPointerException.class)
	public void getRowIndexWithNullChild()
	{
		grid.getRowIndex(null);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowStartIndexWhenEmpty()
	{
		grid.getRowStartIndex(0);
	}
	
	@Test
	public void getRowStartIndexWithFirstWhenRowIsFull()
	{
		grid.add(createChild(), createChild());
		
		assertEquals(0, grid.getRowStartIndex(0));
	}
	
	@Test
	public void getRowStartIndexWithFirstWhenRowIsNotFull()
	{
		grid.add(createChild());
		
		assertEquals(0, grid.getRowStartIndex(0));
	}
	
	@Test
	public void getRowStartIndexWithSecondWhenRowFull()
	{
		grid.add(createChild(), createChild());
		grid.add(createChild(), createChild());
		
		assertEquals(2, grid.getRowStartIndex(1));
	}
	
	@Test
	public void getRowStartIndexWhenPrecedingColumnSpan()
	{
		grid.add(createChild());
		grid.add(createChild(), createChild());
		grid.getLayoutData(0).setColumnSpan(2);
		
		assertEquals(1, grid.getRowStartIndex(1));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowStartIndexWithNegative()
	{
		grid.getRowStartIndex(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowStartIndexWithTooLarge()
	{
		grid.getRowStartIndex(1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowEndIndexWhenEmpty()
	{
		grid.getRowEndIndex(0);
	}
	
	@Test
	public void getRowEndIndexWithFirstWhenRowIsFull()
	{
		grid.add(createChild(), createChild());
		
		assertEquals(1, grid.getRowEndIndex(0));
	}
	
	@Test
	public void getRowEndIndexWithFirstWhenRowIsNotFull()
	{
		grid.add(createChild());
		
		assertEquals(0, grid.getRowEndIndex(0));
	}
	
	@Test
	public void getRowEndIndexWithSecondWhenRowFull()
	{
		grid.add(createChild(), createChild());
		grid.add(createChild(), createChild());
		
		assertEquals(3, grid.getRowEndIndex(1));
	}
	
	@Test
	public void getRowEndIndexWhenPrecedingColumnSpan()
	{
		grid.add(createChild());
		grid.add(createChild(), createChild());
		grid.getLayoutData(0).setColumnSpan(2);
		
		assertEquals(2, grid.getRowEndIndex(1));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowEndIndexWithNegative()
	{
		grid.getRowEndIndex(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowEndIndexWithTooLarge()
	{
		grid.add(createChild(), createChild());
		
		grid.getRowEndIndex(1);
	}
	
	@Test
	public void getColumnIndexWithFirstColumnChildIndex()
	{
		grid.add(createChild());
		
		assertEquals(0, grid.getColumnIndex(0));
	}
	
	@Test
	public void getColumnIndexWithSecondColumnChildIndex()
	{
		grid.add(createChild());
		grid.add(createChild());
		
		assertEquals(1, grid.getColumnIndex(1));
	}
	
	@Test
	public void getColumnIndexWithSecondColumnChildIndexAndPrecedingColumnSpan()
	{
		grid.setColumnCount(3);
		
		Component child1 = createChild();
		grid.add(child1);
		grid.getLayoutData(child1).setColumnSpan(2);
		
		grid.add(createChild());
		
		assertEquals(2, grid.getColumnIndex(1));
	}
	
	@Test
	public void getColumnIndexWithSecondRowChildIndex()
	{
		grid.setColumnCount(1);
		grid.add(createChild());
		grid.add(createChild());
		
		assertEquals(0, grid.getColumnIndex(1));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getColumnIndexWithNegativeChildIndex()
	{
		grid.getColumnIndex(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getColumnIndexWithTooLargeChildIndex()
	{
		grid.getColumnIndex(0);
	}
	
	@Test
	public void getColumnIndexWithFirstColumnChild()
	{
		Component child = createChild();
		grid.add(child);
		
		assertEquals(0, grid.getColumnIndex(child));
	}
	
	@Test(expected = NullPointerException.class)
	public void getColumnIndexWithNullChild()
	{
		grid.getColumnIndex(null);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowComponentsWhenEmpty()
	{
		grid.getRowComponents(0);
	}
	
	@Test
	public void getRowComponentsWithFirstWhenRowIsFull()
	{
		Component child1 = createChild();
		Component child2 = createChild();
		grid.add(child1, child2);
		
		assertEquals(asList(child1, child2), grid.getRowComponents(0));
	}
	
	@Test
	public void getRowComponentsWithFirstWhenRowIsNotFull()
	{
		Component child = createChild();
		grid.add(child);
		
		assertEquals(asList(child), grid.getRowComponents(0));
	}
	
	@Test
	public void getRowComponentsWithSecondWhenRowIsFull()
	{
		grid.add(createChild(), createChild());
		Component child3 = createChild();
		Component child4 = createChild();
		grid.add(child3, child4);
		
		assertEquals(asList(child3, child4), grid.getRowComponents(1));
	}
	
	@Test
	public void getRowComponentsWithWhenPrecedingColumnSpan()
	{
		grid.add(createChild());
		grid.getLayoutData(0).setColumnSpan(2);
		Component child2 = createChild();
		Component child3 = createChild();
		grid.add(child2, child3);
		
		assertEquals(asList(child2, child3), grid.getRowComponents(1));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void getRowComponentsReturnsImmutableList()
	{
		grid.add(createChild(), createChild());
		
		grid.getRowComponents(0).set(0, createChild());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowComponentsWithWithNegative()
	{
		grid.getRowComponents(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getRowComponentsWithWithTooLarge()
	{
		grid.add(createChild(), createChild());
		
		grid.getRowComponents(1);
	}
	
	@Test
	public void getColumn()
	{
		assertEquals(new GridColumn(), grid.getColumn(0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getColumnWithNegative()
	{
		grid.getColumn(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getColumnWithTooLarge()
	{
		grid.getColumn(2);
	}
	
	@Test
	public void getLayoutDataAfterAdd()
	{
		Component child = createChild();
		grid.add(child);
		
		assertEquals(new GridLayoutData(), grid.getLayoutData(child));
	}
	
	@Test(expected = NullPointerException.class)
	public void getLayoutDataWithNull()
	{
		grid.getLayoutData(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getWidthWithUnknownComponent()
	{
		grid.getLayoutData(createChild());
	}
	
	@Test(expected = IllegalStateException.class)
	public void setColumnSpanWithTooLargeColumnSpan()
	{
		Component child = createChild();
		grid.add(child);
		
		grid.getLayoutData(child).setColumnSpan(3);
	}
	
	@Test(expected = IllegalStateException.class)
	public void setColumnSpanWithColumnSpanThatInvalidatesFollowingChildsColumnSpan()
	{
		grid.setColumnCount(3);
		Component child1 = createChild();
		Component child2 = createChild();
		grid.add(child1);
		grid.add(child2);
		grid.getLayoutData(child2).setColumnSpan(2);
		
		grid.getLayoutData(child1).setColumnSpan(2);
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Grid createComponent()
	{
		return new Grid();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Grid> getComponentType()
	{
		return Generic.get(Grid.class);
	}
	
	// private methods --------------------------------------------------------
	
	private static Component createChild()
	{
		return new DummyComponent();
	}
}
