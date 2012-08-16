/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/SortableTableTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.model.SortableTableModel;

/**
 * Tests {@code SortableTable}.
 * 
 * @author Mark Hobson
 * @version $Id: SortableTableTest.java 87836 2011-05-10 17:40:10Z mark@IIZUKA.CO.UK $
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
