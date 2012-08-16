/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulWindowDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulWindowDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulWindowDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see XulWindowDehydrator
 */
public class XulWindowDehydratorTest extends AbstractXmlRenderKitTest<Window>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Window> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Window.class, new XulWindowDehydrator<Window>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateWindow() throws HydrationException
	{
		Window window = new Window();
		
		String expected = "<?xml version=\"1.0\" ?>"
			+ "<window xmlns=\"http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul\"></window>";
		
		assertDehydrate(expected, window);
	}
	
	@Test
	public void dehydrateWindowWithTitle() throws HydrationException
	{
		Window window = new Window("a");
		
		String expected = "<?xml version=\"1.0\" ?>"
			+ "<window xmlns=\"http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul\" title=\"a\"></window>";
		
		assertDehydrate(expected, window);
	}
}
