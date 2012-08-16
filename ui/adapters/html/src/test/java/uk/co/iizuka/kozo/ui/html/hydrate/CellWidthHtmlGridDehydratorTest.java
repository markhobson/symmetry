/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/CellWidthHtmlGridDehydratorTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.layout.Length.flex;
import static uk.co.iizuka.kozo.ui.layout.Length.pixels;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Grid;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.layout.HorizontalAlignment;
import uk.co.iizuka.kozo.ui.layout.VerticalAlignment;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code CellWidthHtmlGridDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: CellWidthHtmlGridDehydratorTest.java 100133 2012-04-02 10:09:45Z mark@IIZUKA.CO.UK $
 * @see CellWidthHtmlGridDehydrator
 */
public class CellWidthHtmlGridDehydratorTest extends AbstractXmlRenderKitTest<Grid>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Grid> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Grid.class, new CellWidthHtmlGridDehydrator<Grid>());
		
		hydrator.setDelegate(DummyComponent.class, stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy"));
		
		return StubUiComponentRenderKit.get(Grid.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateWhenEmpty() throws HydrationException
	{
		Grid grid = new Grid();
		
		assertDehydrate("<table class=\"grid\"></table>", grid);
	}
	
	@Test
	public void dehydrateWithCell() throws HydrationException
	{
		Grid grid = new Grid();
		grid.add(createComponent());
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndColumnWidthPixels() throws HydrationException
	{
		Grid grid = new Grid();
		grid.add(createComponent());
		grid.getColumn(0).setWidth(pixels(1));
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td style=\"width: 1px\">[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndColumnWidthFlex() throws HydrationException
	{
		Grid grid = new Grid();
		grid.add(createComponent());
		grid.getColumn(0).setWidth(flex(1));
		
		String expected = "<table class=\"grid flexed\">"
			+ "<tbody>"
			+ "<tr><td style=\"width: 100%\">[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRowAndColumnsWidthFlex() throws HydrationException
	{
		Grid grid = new Grid();
		grid.add(createComponent(), createComponent());
		grid.getColumn(0).setWidth(flex(1));
		grid.getColumn(1).setWidth(flex(1));
		
		String expected = "<table class=\"grid flexed\">"
			+ "<tbody>"
			+ "<tr>"
			+ "<td style=\"width: 50%\">[dummy][/dummy]</td>"
			+ "<td style=\"width: 50%\">[dummy][/dummy]</td>"
			+ "</tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRowAndColumnsWidthFlexRounding() throws HydrationException
	{
		Grid grid = new Grid(3);
		grid.add(createComponent(), createComponent(), createComponent());
		grid.getColumn(0).setWidth(flex(1));
		grid.getColumn(1).setWidth(flex(1));
		grid.getColumn(2).setWidth(flex(1));
		
		String expected = "<table class=\"grid flexed\">"
			+ "<tbody>"
			+ "<tr>"
			+ "<td style=\"width: 33%\">[dummy][/dummy]</td>"
			+ "<td style=\"width: 33%\">[dummy][/dummy]</td>"
			+ "<td style=\"width: 34%\">[dummy][/dummy]</td>"
			+ "</tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRowAndColumnsWidthFlexRatios() throws HydrationException
	{
		Grid grid = new Grid(3);
		grid.add(createComponent(), createComponent(), createComponent());
		grid.getColumn(0).setWidth(flex(1));
		grid.getColumn(1).setWidth(flex(3));
		grid.getColumn(2).setWidth(flex(6));
		
		String expected = "<table class=\"grid flexed\">"
			+ "<tbody>"
			+ "<tr>"
			+ "<td style=\"width: 10%\">[dummy][/dummy]</td>"
			+ "<td style=\"width: 30%\">[dummy][/dummy]</td>"
			+ "<td style=\"width: 60%\">[dummy][/dummy]</td>"
			+ "</tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRowAndColumnWidthPixelsAndFlex() throws HydrationException
	{
		Grid grid = new Grid();
		grid.add(createComponent(), createComponent());
		grid.getColumn(0).setWidth(pixels(1));
		grid.getColumn(1).setWidth(flex(1));
		
		String expected = "<table class=\"grid flexed\">"
			+ "<tbody>"
			+ "<tr>"
			+ "<td style=\"min-width: 1px\">[dummy][/dummy]</td>"
			+ "<td style=\"width: 100%\">[dummy][/dummy]</td>"
			+ "</tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRowsAndColumnWidthPixels() throws HydrationException
	{
		Grid grid = new Grid(1);
		grid.add(createComponent());
		grid.add(createComponent());
		grid.getColumn(0).setWidth(pixels(1));
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td style=\"width: 1px\">[dummy][/dummy]</td></tr>"
			+ "<tr><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRowsAndColumnWidthPixelsWithColumnSpan() throws HydrationException
	{
		Grid grid = new Grid();
		grid.add(createComponent());
		grid.getLayoutData(0).setColumnSpan(2);
		grid.add(createComponent(), createComponent());
		grid.getColumn(0).setWidth(pixels(1));
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td colspan=\"2\">[dummy][/dummy]</td></tr>"
			+ "<tr><td style=\"width: 1px\">[dummy][/dummy]</td><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndLeftHorizontalAlignment() throws HydrationException
	{
		Grid grid = new Grid();
		Component child = createComponent();
		grid.add(child);
		grid.getLayoutData(child).setHorizontalAlignment(HorizontalAlignment.LEFT);
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndMiddleHorizontalAlignment() throws HydrationException
	{
		Grid grid = new Grid();
		Component child = createComponent();
		grid.add(child);
		grid.getLayoutData(child).setHorizontalAlignment(HorizontalAlignment.MIDDLE);
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td style=\"text-align: center\">[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndRightHorizontalAlignment() throws HydrationException
	{
		Grid grid = new Grid();
		Component child = createComponent();
		grid.add(child);
		grid.getLayoutData(child).setHorizontalAlignment(HorizontalAlignment.RIGHT);
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td style=\"text-align: right\">[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndTopVerticalAlignment() throws HydrationException
	{
		Grid grid = new Grid();
		Component child = createComponent();
		grid.add(child);
		grid.getLayoutData(child).setVerticalAlignment(VerticalAlignment.TOP);
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td style=\"vertical-align: top\">[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndMiddleVerticalAlignment() throws HydrationException
	{
		Grid grid = new Grid();
		Component child = createComponent();
		grid.add(child);
		grid.getLayoutData(child).setVerticalAlignment(VerticalAlignment.MIDDLE);
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndBottomVerticalAlignment() throws HydrationException
	{
		Grid grid = new Grid();
		Component child = createComponent();
		grid.add(child);
		grid.getLayoutData(child).setVerticalAlignment(VerticalAlignment.BOTTOM);
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td style=\"vertical-align: bottom\">[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithCellAndColumnSpan() throws HydrationException
	{
		Grid grid = new Grid();
		Component child = createComponent();
		grid.add(child);
		grid.getLayoutData(child).setColumnSpan(2);
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td colspan=\"2\">[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRow() throws HydrationException
	{
		Grid grid = new Grid();
		grid.add(createComponent(), createComponent());
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td>[dummy][/dummy]</td><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRows() throws HydrationException
	{
		Grid grid = new Grid();
		grid.add(createComponent(), createComponent());
		grid.add(createComponent(), createComponent());
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td>[dummy][/dummy]</td><td>[dummy][/dummy]</td></tr>"
			+ "<tr><td>[dummy][/dummy]</td><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	@Test
	public void dehydrateWithRowsAndColumnSpan() throws HydrationException
	{
		Grid grid = new Grid();
		Component child = createComponent();
		grid.add(child);
		grid.getLayoutData(child).setColumnSpan(2);
		grid.add(createComponent(), createComponent());
		
		String expected = "<table class=\"grid\">"
			+ "<tbody>"
			+ "<tr><td colspan=\"2\">[dummy][/dummy]</td></tr>"
			+ "<tr><td>[dummy][/dummy]</td><td>[dummy][/dummy]</td></tr>"
			+ "</tbody>"
			+ "</table>";
		
		assertDehydrate(expected, grid);
	}
	
	// private methods --------------------------------------------------------
	
	private static Component createComponent()
	{
		return new DummyComponent();
	}
}
