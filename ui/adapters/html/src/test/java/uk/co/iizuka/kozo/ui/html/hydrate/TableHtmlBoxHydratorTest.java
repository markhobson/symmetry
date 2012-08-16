/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/TableHtmlBoxHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.ui.layout.Length.flex;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Orientation;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code TableHtmlBoxHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: TableHtmlBoxHydratorTest.java 99869 2012-03-23 17:26:34Z mark@IIZUKA.CO.UK $
 * @see TableHtmlBoxHydrator
 */
public class TableHtmlBoxHydratorTest extends AbstractXmlRenderKitTest<Box>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Box> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Box.class, new TableHtmlBoxHydrator<Box>());
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(Box.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateWhenEmpty() throws HydrationException
	{
		Box box = new Box();
		
		assertDehydrate("", box);
	}
	
	@Test
	public void dehydrateVerticalWithChild() throws HydrationException
	{
		Box box = new Box(component());

		String expected = "<table class=\"vbox\">"
			+ "<tr><td class=\"vbox-child\">[dummy][/dummy]</td></tr>"
			+ "</table>";
		
		assertDehydrate(expected, box);
	}
	
	@Test
	public void dehydrateVerticalWithChildren() throws HydrationException
	{
		Box box = new Box(component(), component(), component());
		
		String expected = "<table class=\"vbox\">"
			+ "<tr><td class=\"vbox-child\">[dummy][/dummy]</td></tr>"
			+ "<tr><td class=\"vbox-child\">[dummy][/dummy]</td></tr>"
			+ "<tr><td class=\"vbox-child\">[dummy][/dummy]</td></tr>"
			+ "</table>";
		
		assertDehydrate(expected, box);
	}
	
	@Test
	public void dehydrateHorizontalWithChild() throws HydrationException
	{
		Box box = new Box(Orientation.HORIZONTAL, component());
		
		String expected = "<table class=\"hbox\"><tr>"
			+ "<td class=\"hbox-child\">[dummy][/dummy]</td>"
			+ "</tr></table>";
		
		assertDehydrate(expected, box);
	}
	
	@Test
	public void dehydrateHorizontalWithChildren() throws HydrationException
	{
		Box box = new Box(Orientation.HORIZONTAL, component(), component(), component());
		
		String expected = "<table class=\"hbox\"><tr>"
			+ "<td class=\"hbox-child\">[dummy][/dummy]</td>"
			+ "<td class=\"hbox-child\">[dummy][/dummy]</td>"
			+ "<td class=\"hbox-child\">[dummy][/dummy]</td>"
			+ "</tr></table>";
		
		assertDehydrate(expected, box);
	}
	
	@Test
	public void dehydrateHorizontalWithFlexedChild() throws HydrationException
	{
		Box box = new Box(Orientation.HORIZONTAL, component());
		box.getLayoutData(0).setLength(flex(1));
		
		String expected = "<table class=\"hbox flexed\"><tr>"
			+ "<td class=\"hbox-child\" style=\"width: 100%\">[dummy][/dummy]</td>"
			+ "</tr></table>";
		
		assertDehydrate(expected, box);
	}
	
	@Test
	public void dehydrateHorizontalWithFlexedChildren() throws HydrationException
	{
		Box box = new Box(Orientation.HORIZONTAL, component(), component());
		box.getLayoutData(0).setLength(flex(1));
		box.getLayoutData(1).setLength(flex(1));
		
		String expected = "<table class=\"hbox flexed\"><tr>"
			+ "<td class=\"hbox-child\" style=\"width: 50%\">[dummy][/dummy]</td>"
			+ "<td class=\"hbox-child\" style=\"width: 50%\">[dummy][/dummy]</td>"
			+ "</tr></table>";
		
		assertDehydrate(expected, box);
	}
	
	@Test
	public void dehydrateHorizontalWithFlexedChildrenRounding() throws HydrationException
	{
		Box box = new Box(Orientation.HORIZONTAL, component(), component(), component());
		box.getLayoutData(0).setLength(flex(1));
		box.getLayoutData(1).setLength(flex(1));
		box.getLayoutData(2).setLength(flex(1));
		
		String expected = "<table class=\"hbox flexed\"><tr>"
			+ "<td class=\"hbox-child\" style=\"width: 33%\">[dummy][/dummy]</td>"
			+ "<td class=\"hbox-child\" style=\"width: 33%\">[dummy][/dummy]</td>"
			+ "<td class=\"hbox-child\" style=\"width: 34%\">[dummy][/dummy]</td>"
			+ "</tr></table>";
		
		assertDehydrate(expected, box);
	}
	
	@Test
	public void dehydrateHorizontalWithFlexedChildrenRatios() throws HydrationException
	{
		Box box = new Box(Orientation.HORIZONTAL, component(), component(), component());
		box.getLayoutData(0).setLength(flex(1));
		box.getLayoutData(1).setLength(flex(3));
		box.getLayoutData(2).setLength(flex(6));
		
		String expected = "<table class=\"hbox flexed\"><tr>"
			+ "<td class=\"hbox-child\" style=\"width: 10%\">[dummy][/dummy]</td>"
			+ "<td class=\"hbox-child\" style=\"width: 30%\">[dummy][/dummy]</td>"
			+ "<td class=\"hbox-child\" style=\"width: 60%\">[dummy][/dummy]</td>"
			+ "</tr></table>";
		
		assertDehydrate(expected, box);
	}
	
	// private methods --------------------------------------------------------
	
	private static Component component()
	{
		return new DummyComponent();
	}
}
