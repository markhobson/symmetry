/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/traversal/VisibleRowTableVisitorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
