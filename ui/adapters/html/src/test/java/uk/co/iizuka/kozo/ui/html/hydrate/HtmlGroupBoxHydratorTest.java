/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlGroupBoxHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlContainerHydrator;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlGroupBoxHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlGroupBoxHydratorTest.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
 * @see HtmlGroupBoxHydrator
 */
public class HtmlGroupBoxHydratorTest extends AbstractXmlRenderKitTest<GroupBox>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<GroupBox> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		ContainerVisitor<Box, HydrationContext, HydrationException> boxHydrator = stubXmlContainerHydrator("box");
		hydrator.setDelegate(Box.class, boxHydrator);
		
		hydrator.setDelegate(GroupBox.class, new HtmlGroupBoxHydrator<GroupBox>(boxHydrator));
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(GroupBox.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrate() throws HydrationException
	{
		GroupBox groupBox = new GroupBox();
		
		String expected = "<fieldset class=\"groupbox\">"
			// TODO: expect no <label/>
			+ "<legend><label></label></legend>"
			+ "<div class=\"grouppanel\">[box][/box]</div>"
			+ "</fieldset>";
		
		assertDehydrate(expected, groupBox);
	}
	
	@Test
	public void dehydrateWithTitle() throws HydrationException
	{
		GroupBox groupBox = new GroupBox("a");
		
		String expected = "<fieldset class=\"groupbox\">"
			+ "<legend><label>a</label></legend>"
			+ "<div class=\"grouppanel\">[box][/box]</div>"
			+ "</fieldset>";
		
		assertDehydrate(expected, groupBox);
	}
	
	@Test
	public void dehydrateWithChild() throws HydrationException
	{
		GroupBox groupBox = new GroupBox(null, new DummyComponent());
		
		String expected = "<fieldset class=\"groupbox\">"
			// TODO: expect no <label/>
			+ "<legend><label></label></legend>"
			+ "<div class=\"grouppanel\">[box][dummy][/dummy][/box]</div>"
			+ "</fieldset>";
		
		assertDehydrate(expected, groupBox);
	}
	
	@Test
	public void dehydrateWithChildren() throws HydrationException
	{
		GroupBox groupBox = new GroupBox(null, new DummyComponent(), new DummyComponent(), new DummyComponent());
		
		String expected = "<fieldset class=\"groupbox\">"
			// TODO: expect no <label/>
			+ "<legend><label></label></legend>"
			+ "<div class=\"grouppanel\">[box][dummy][/dummy][dummy][/dummy][dummy][/dummy][/box]</div>"
			+ "</fieldset>";
		
		assertDehydrate(expected, groupBox);
	}
}
