/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulButtonDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulButtonDehydrator}.
 * 
 * @author Mark Hobson
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
