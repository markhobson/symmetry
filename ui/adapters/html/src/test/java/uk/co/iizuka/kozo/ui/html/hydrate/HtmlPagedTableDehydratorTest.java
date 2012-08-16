/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlPagedTableDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlTableHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.model.DefaultTableModel;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlPagedTableDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlPagedTableDehydratorTest.java 99706 2012-03-20 16:50:54Z mark@IIZUKA.CO.UK $
 * @see HtmlPagedTableDehydrator
 */
public class HtmlPagedTableDehydratorTest extends AbstractXmlRenderKitTest<Table>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Table> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Table.class, new HtmlPagedTableDehydrator<Table>(
			stubXmlTableHydrator("table", false)));
	}
	
	// tests ------------------------------------------------------------------
	
	// TODO: dehydrateInitializeSkipsNonVisibleRows
	
	@Test
	public void dehydrateTable() throws HydrationException
	{
		Table table = new Table();
		
		String expected = "[table]"
			+ "[table.header][/table.header]"
			+ "[table.body][/table.body]"
			+ "[/table]";
		
		assertDehydrate(expected, table);
	}

	@Test
	public void dehydrateTableWithRow() throws HydrationException
	{
		Table table = createTableWithRow("a");
		
		String expected = "[table]"
			+ "[table.header][/table.header]"
			+ "[table.body][table.row][/table.row][/table.body]"
			+ "[/table]";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithRows() throws HydrationException
	{
		Table table = createTableWithRows(new Object[] {"a"}, new Object[] {"b"}, new Object[] {"c"});
		
		String expected = "[table]"
			+ "[table.header][/table.header]"
			+ "[table.body][table.row][/table.row][table.row][/table.row][table.row][/table.row][/table.body]"
			+ "[/table]";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithRowsOnSinglePage() throws HydrationException
	{
		Table table = createTableWithRows(new Object[] {"a"}, new Object[] {"b"}, new Object[] {"c"});
		table.setVisibleRowCount(3);
		
		String expected = "[table]"
			+ "[table.header][/table.header]"
			+ "[table.body][table.row][/table.row][table.row][/table.row][table.row][/table.row][/table.body]"
			+ "[/table]";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithPagedRowsOnFirstPage() throws HydrationException
	{
		Table table = createTableWithRows(new Object[] {"a"}, new Object[] {"b"}, new Object[] {"c"});
		table.setVisibleRowCount(2);
		
		String expected = "[table]"
			+ "[table.header][/table.header]"
			+ "<tfoot><tr><td colspan=\"1\"><ul>"
			+ "<li class=\"disabled\">&laquo; First</li>"
			+ "<li class=\"disabled\">&lsaquo; Previous</li>"
			+ "<li class=\"separated\">1-2 of 3</li>"
			+ "<li><a title=\"Go to next page\" href=\"state\">Next &rsaquo;</a></li>"
			+ "<li><a title=\"Go to last page\" href=\"state\">Last &raquo;</a></li>"
			+ "</ul></td></tr></tfoot>"
			+ "[table.body][table.row][/table.row][table.row][/table.row][/table.body]"
			+ "[/table]";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithPagedRowsOnMiddlePage() throws HydrationException
	{
		Table table = createTableWithRows(new Object[] {"a"}, new Object[] {"b"}, new Object[] {"c"},
			new Object[] {"d"});
		table.setVisibleRowCount(2);
		table.setFirstVisibleRowIndex(1);
		
		String expected = "[table]"
			+ "[table.header][/table.header]"
			+ "<tfoot><tr><td colspan=\"1\"><ul>"
			+ "<li><a title=\"Go to first page\" href=\"state\">&laquo; First</a></li>"
			+ "<li><a title=\"Go to previous page\" href=\"state\">&lsaquo; Previous</a></li>"
			+ "<li class=\"separated\">2-3 of 4</li>"
			+ "<li><a title=\"Go to next page\" href=\"state\">Next &rsaquo;</a></li>"
			+ "<li><a title=\"Go to last page\" href=\"state\">Last &raquo;</a></li>"
			+ "</ul></td></tr></tfoot>"
			+ "[table.body][table.row][/table.row][table.row][/table.row][/table.body]"
			+ "[/table]";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithPagedRowsOnLastPage() throws HydrationException
	{
		Table table = createTableWithRows(new Object[] {"a"}, new Object[] {"b"}, new Object[] {"c"});
		table.setVisibleRowCount(2);
		table.setFirstVisibleRowIndex(2);
		
		String expected = "[table]"
			+ "[table.header][/table.header]"
			+ "<tfoot><tr><td colspan=\"1\"><ul>"
			+ "<li><a title=\"Go to first page\" href=\"state\">&laquo; First</a></li>"
			+ "<li><a title=\"Go to previous page\" href=\"state\">&lsaquo; Previous</a></li>"
			+ "<li class=\"separated\">3-3 of 3</li>"
			+ "<li class=\"disabled\">Next &rsaquo;</li>"
			+ "<li class=\"disabled\">Last &raquo;</li>"
			+ "</ul></td></tr></tfoot>"
			+ "[table.body][table.row][/table.row][/table.body]"
			+ "[/table]";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithUnpagedRows() throws HydrationException
	{
		Table table = createTableWithRows(new Object[] {"a"}, new Object[] {"b"}, new Object[] {"c"});
		table.setVisibleRowCount(0);
		
		String expected = "[table]"
			+ "[table.header][/table.header]"
			+ "[table.body][table.row][/table.row][table.row][/table.row][table.row][/table.row][/table.body]"
			+ "[/table]";
		
		assertDehydrate(expected, table);
	}
	
	// private methods --------------------------------------------------------
	
	private static Table createTableWithRow(Object... row)
	{
		return createTableWithRows(row);
	}
	
	private static Table createTableWithRows(Object[]... rows)
	{
		return new Table(new DefaultTableModel(rows));
	}
}
