/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlWindowHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlContainerHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.html.HtmlDocument;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlWindowHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlWindowHydratorTest.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
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
