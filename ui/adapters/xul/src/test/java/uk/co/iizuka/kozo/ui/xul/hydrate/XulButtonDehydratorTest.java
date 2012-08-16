/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulButtonDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulButtonDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulButtonDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see XulButtonDehydrator
 */
public class XulButtonDehydratorTest extends AbstractXmlRenderKitTest<Button>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Button> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Button.class, new XulButtonDehydrator<Button>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateButton() throws HydrationException
	{
		Button button = new Button();
		
		assertDehydrate("<button/>", button);
	}
	
	@Test
	public void dehydrateButtonWithText() throws HydrationException
	{
		Button button = new Button("text");
		
		assertDehydrate("<button label=\"text\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithToolTip() throws HydrationException
	{
		Button button = new Button();
		button.setToolTip("a");
		
		assertDehydrate("<button tooltiptext=\"a\"/>", button);
	}
}
