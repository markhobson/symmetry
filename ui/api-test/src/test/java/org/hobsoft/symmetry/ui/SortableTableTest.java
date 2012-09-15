/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/SortableTableTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.junit.Assert.assertTrue;

import org.hobsoft.symmetry.ui.model.SortableTableModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code SortableTable}.
 * 
 * @author Mark Hobson
 * @see SortableTable
 */
public class SortableTableTest
{
	// fields -----------------------------------------------------------------
	
	private SortableTable table;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		table = new SortableTable();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newSortableTable()
	{
		assertTrue("Expected SortableTableModel", table.getModel() instanceof SortableTableModel);
	}
}
