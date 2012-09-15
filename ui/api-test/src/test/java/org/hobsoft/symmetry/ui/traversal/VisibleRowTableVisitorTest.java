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
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code VisibleRowTableVisitor}.
 * 
 * @author Mark Hobson
 * @see VisibleRowTableVisitor
 */
public class VisibleRowTableVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private VisibleRowTableVisitor<Table, Void, RuntimeException> visitor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		visitor = new VisibleRowTableVisitor<Table, Void, RuntimeException>(
			new NullTableVisitor<Table, Void, RuntimeException>());
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void visitRowWhenVisible()
	{
		Table table = createTableWithRows(1);
		
		assertEquals(VISIT_CHILDREN, visitor.visitRow(table, null, 0));
	}
	
	@Test
	public void visitRowWhenVisibleAndUnpaged()
	{
		Table table = createTableWithRows(1);
		table.setVisibleRowCount(0);
		
		assertEquals(VISIT_CHILDREN, visitor.visitRow(table, null, 0));
	}
	
	@Test
	public void visitRowWhenBeforeVisible()
	{
		Table table = createTableWithRows(2);
		table.setFirstVisibleRowIndex(1);
		
		assertEquals(SKIP_CHILDREN, visitor.visitRow(table, null, 0));
	}
	
	@Test
	public void visitRowWhenAfterVisible()
	{
		Table table = createTableWithRows(2);
		table.setVisibleRowCount(1);
		
		assertEquals(SKIP_CHILDREN, visitor.visitRow(table, null, 1));
	}
	
	// private methods --------------------------------------------------------
	
	private static Table createTableWithRows(int rows)
	{
		DefaultTableModel model = new DefaultTableModel();
		
		for (int i = 0; i < rows; i++)
		{
			model.addRow("a");
		}
		
		return new Table(model);
	}
}
