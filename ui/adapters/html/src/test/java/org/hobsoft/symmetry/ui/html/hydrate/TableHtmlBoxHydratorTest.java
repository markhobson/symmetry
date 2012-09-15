/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/TableHtmlBoxHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Orientation;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.layout.Length.flex;
import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

/**
 * Tests {@code TableHtmlBoxHydrator}.
 * 
 * @author Mark Hobson
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
