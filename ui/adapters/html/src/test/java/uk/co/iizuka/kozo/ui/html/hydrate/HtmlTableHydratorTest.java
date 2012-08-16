/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTableHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static org.junit.Assert.assertEquals;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_EVENTS;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static uk.co.iizuka.kozo.ui.layout.Length.flex;
import static uk.co.iizuka.kozo.ui.layout.Length.pixels;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.model.DefaultTableModel;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.test.event.DummyTableCellListener;
import uk.co.iizuka.kozo.ui.test.view.DummyComponentTableCellRenderer;
import uk.co.iizuka.kozo.ui.test.view.NullComponentTableCellRenderer;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlTableHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTableHydratorTest.java 100101 2012-03-30 15:08:55Z mark@IIZUKA.CO.UK $
 * @see HtmlTableHydrator
 */
public class HtmlTableHydratorTest extends AbstractXmlRenderKitTest<Table>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Table> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Table.class, createHydrator());
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(Table.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTable() throws HydrationException
	{
		Table table = new Table();
		
		// TODO: shouldn't expect thead
		String expected = "<table class=\"table\">"
			+ "<thead><tr></tr></thead>"
			+ "<tbody></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithCell() throws HydrationException
	{
		Table table = createTableWithRow("a");
		
		// TODO: do we need table-nolink class?
		String expected = "<table class=\"table\">"
			+ "<thead><tr><th class=\"table-nolink\">[dummy][/dummy]</th></tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\"><td>[dummy][/dummy]</td></tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithRow() throws HydrationException
	{
		Table table = createTableWithRow("a", "b", "c");
		
		String expected = "<table class=\"table\">"
			+ "<thead><tr>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "</tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\">"
			+ "<td>[dummy][/dummy]</td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "</tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithRows() throws HydrationException
	{
		Table table = createTableWithRows(new Object[] {"a"}, new Object[] {"b"}, new Object[] {"c"});
		
		String expected = "<table class=\"table\">"
			+ "<thead><tr>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "</tr></thead>"
			+ "<tbody>"
			+ "<tr class=\"table-row-odd\"><td>[dummy][/dummy]</td></tr>"
			+ "<tr class=\"table-row-even\"><td>[dummy][/dummy]</td></tr>"
			+ "<tr class=\"table-row-odd\"><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithRowsDoesNotPage() throws HydrationException
	{
		Table table = createTableWithRows(new Object[] {"a"}, new Object[] {"b"}, new Object[] {"c"});
		table.setVisibleRowCount(2);
		
		String expected = "<table class=\"table\">"
			+ "<thead><tr>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "</tr></thead>"
			+ "<tbody>"
			+ "<tr class=\"table-row-odd\"><td>[dummy][/dummy]</td></tr>"
			+ "<tr class=\"table-row-even\"><td>[dummy][/dummy]</td></tr>"
			+ "<tr class=\"table-row-odd\"><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithColumnWidthPixels() throws HydrationException
	{
		Table table = createTableWithRow("a");
		table.getColumn(0).setWidth(pixels(1));
		
		String expected = "<table class=\"table\">"
			+ "<colgroup>"
			+ "<col style=\"width: 1px\"/>"
			+ "</colgroup>"
			+ "<thead><tr><th class=\"table-nolink\">[dummy][/dummy]</th></tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\"><td>[dummy][/dummy]</td></tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithColumnWidthFlex() throws HydrationException
	{
		Table table = createTableWithRow("a");
		table.getColumn(0).setWidth(flex(1));
		
		String expected = "<table class=\"table flexed\">"
			+ "<colgroup>"
			+ "<col style=\"width: 100%\"/>"
			+ "</colgroup>"
			+ "<thead><tr><th class=\"table-nolink\">[dummy][/dummy]</th></tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\"><td>[dummy][/dummy]</td></tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithColumnsWidthFlex() throws HydrationException
	{
		Table table = createTableWithRow("a", "b");
		table.getColumn(0).setWidth(flex(1));
		table.getColumn(1).setWidth(flex(1));
		
		String expected = "<table class=\"table flexed\">"
			+ "<colgroup>"
			+ "<col style=\"width: 50%\"/>"
			+ "<col style=\"width: 50%\"/>"
			+ "</colgroup>"
			+ "<thead><tr>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "</tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\">"
			+ "<td>[dummy][/dummy]</td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "</tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithColumnsWidthFlexRounding() throws HydrationException
	{
		Table table = createTableWithRow("a", "b", "c");
		table.getColumn(0).setWidth(flex(1));
		table.getColumn(1).setWidth(flex(1));
		table.getColumn(2).setWidth(flex(1));
		
		String expected = "<table class=\"table flexed\">"
			+ "<colgroup>"
			+ "<col style=\"width: 33%\"/>"
			+ "<col style=\"width: 33%\"/>"
			+ "<col style=\"width: 34%\"/>"
			+ "</colgroup>"
			+ "<thead><tr>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "</tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\">"
			+ "<td>[dummy][/dummy]</td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "</tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithColumnsWidthFlexRatios() throws HydrationException
	{
		Table table = createTableWithRow("a", "b", "c");
		table.getColumn(0).setWidth(flex(1));
		table.getColumn(1).setWidth(flex(3));
		table.getColumn(2).setWidth(flex(6));
		
		String expected = "<table class=\"table flexed\">"
			+ "<colgroup>"
			+ "<col style=\"width: 10%\"/>"
			+ "<col style=\"width: 30%\"/>"
			+ "<col style=\"width: 60%\"/>"
			+ "</colgroup>"
			+ "<thead><tr>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "</tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\">"
			+ "<td>[dummy][/dummy]</td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "</tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithColumnWidthPixelsAndFlex() throws HydrationException
	{
		Table table = createTableWithRow("a", "b");
		table.getColumn(0).setWidth(pixels(1));
		table.getColumn(1).setWidth(flex(1));
		
		String expected = "<table class=\"table flexed\">"
			+ "<colgroup>"
			+ "<col style=\"min-width: 1px\"/>"
			+ "<col style=\"width: 100%\"/>"
			+ "</colgroup>"
			+ "<thead><tr>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "</tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\">"
			+ "<td>[dummy][/dummy]</td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "</tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithNullComponentHeaderRenderer() throws HydrationException
	{
		Table table = createTableWithRow("a");
		table.setHeaderRenderer(new NullComponentTableCellRenderer());
		
		String expected = "<table class=\"table\">"
			+ "<thead><tr><th class=\"table-nolink\"></th></tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\"><td>[dummy][/dummy]</td></tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithNullComponentCellRenderer() throws HydrationException
	{
		Table table = createTableWithRow("a");
		table.setCellRenderer(new NullComponentTableCellRenderer());
		
		String expected = "<table class=\"table\">"
			+ "<thead><tr><th class=\"table-nolink\">[dummy][/dummy]</th></tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\"><td></td></tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithTableCellListener() throws HydrationException
	{
		Table table = createTableWithRow("a");
		table.addCellListener(new DummyTableCellListener());
		
		String expected = "<table class=\"table\">"
			+ "<thead><tr><th class=\"table-nolink\">[dummy][/dummy]</th></tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\"><td><a href=\"state\">[dummy][/dummy]</a></td></tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void dehydrateTableWithColumnCellListener() throws HydrationException
	{
		Table table = createTableWithRow("a", "b");
		table.getColumn(0).addCellListener(new DummyTableCellListener());
		
		String expected = "<table class=\"table\">"
			+ "<thead><tr>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "<th class=\"table-nolink\">[dummy][/dummy]</th>"
			+ "</tr></thead>"
			+ "<tbody><tr class=\"table-row-odd\">"
			+ "<td><a href=\"state\">[dummy][/dummy]</a></td>"
			+ "<td>[dummy][/dummy]</td>"
			+ "</tr></tbody>"
			+ "</table>";
		
		assertDehydrate(expected, table);
	}
	
	@Test
	public void rehydratePropertiesSkipsChildren() throws HydrationException
	{
		Table table = createTableWithRow("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(table, getRehydrationContext(REHYDRATE_PROPERTIES)));
	}
	
	@Test
	public void rehydrateEventsSkipsChildren() throws HydrationException
	{
		Table table = createTableWithRow("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(table, getRehydrationContext(REHYDRATE_EVENTS)));
	}
	
	@Test
	public void rehydrateParametersSkipsChildren() throws HydrationException
	{
		Table table = createTableWithRow("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(table, getRehydrationContext(REHYDRATE_PARAMETERS)));
	}
	
	// private methods --------------------------------------------------------
	
	private static HtmlTableHydrator<Table> createHydrator()
	{
		return new HtmlTableHydrator<Table>();
	}

	// TODO: centralise
	private RehydrationContext getRehydrationContext(HydrationPhase phase)
	{
		RehydrationContext context = getRehydrationContext();
		context.set(HydrationPhase.class, phase);
		return context;
	}
	
	private static Table createTableWithRow(Object... row)
	{
		return createTableWithRows(row);
	}
	
	private static Table createTableWithRows(Object[]... rows)
	{
		Table table = new Table(new DefaultTableModel(rows));
		
		table.setHeaderRenderer(new DummyComponentTableCellRenderer());
		table.setCellRenderer(new DummyComponentTableCellRenderer());
		
		return table;
	}
}
