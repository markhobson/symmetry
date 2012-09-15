/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlWindowHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.HtmlDocument;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlContainerHydrator;

/**
 * Tests {@code HtmlWindowHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlWindowHydrator
 */
public class HtmlWindowHydratorTest extends AbstractXmlRenderKitTest<Window>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Window> createRenderKit()
	{
		ContainerVisitor<Box, HydrationContext, HydrationException> boxHydrator = stubXmlContainerHydrator("box");
		
		return StubPhasedUiComponentRenderKit.get(Window.class, new HtmlWindowHydrator<Window>(boxHydrator));
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrate() throws HydrationException
	{
		Window window = new Window();
		
		// TODO: expect encoding; no head
		String expected = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" "
				+ "\"http://www.w3.org/TR/html4/strict.dtd\">"
			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
			+ "<head></head>"
			+ "<body class=\"window\">[box][/box]</body>"
			+ "</html>";
		
		assertDehydrate(expected, window);
	}
	
	@Test
	public void dehydrateWithMetadata() throws HydrationException
	{
		Window window = new Window();
		
		HtmlDocument document = new HtmlDocument();
		document.addMetadata("x", "y");
		getDehydrationContext().set(HtmlDocument.class, document);
		
		String expected = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" "
				+ "\"http://www.w3.org/TR/html4/strict.dtd\">"
			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
			+ "<head><meta name=\"x\" content=\"y\"/></head>"
			+ "<body class=\"window\">[box][/box]</body>"
			+ "</html>";
		
		assertDehydrate(expected, window);
	}
	
	@Test
	public void dehydrateWithHttpMetadata() throws HydrationException
	{
		Window window = new Window();
		
		HtmlDocument document = new HtmlDocument();
		document.addHttpMetadata("x", "y");
		getDehydrationContext().set(HtmlDocument.class, document);
		
		String expected = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" "
				+ "\"http://www.w3.org/TR/html4/strict.dtd\">"
			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
			+ "<head><meta http-equiv=\"x\" content=\"y\"/></head>"
			+ "<body class=\"window\">[box][/box]</body>"
			+ "</html>";
		
		assertDehydrate(expected, window);
	}
}
