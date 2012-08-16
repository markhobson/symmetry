/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulTextBoxDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulTextBoxDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulTextBoxDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see XulTextBoxDehydrator
 */
public class XulTextBoxDehydratorTest extends AbstractXmlRenderKitTest<TextBox>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<TextBox> createRenderKit()
	{
		return StubUiComponentRenderKit.get(TextBox.class, new XulTextBoxDehydrator<TextBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTextBox() throws HydrationException
	{
		TextBox textBox = new TextBox();
		
		assertDehydrate("<textbox/>", textBox);
	}
	
	@Test
	public void dehydrateTextBoxWithText() throws HydrationException
	{
		TextBox textBox = new TextBox("text");
		
		assertDehydrate("<textbox value=\"text\"/>", textBox);
	}
}
