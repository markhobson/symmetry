/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulCheckBoxDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.CheckBox;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulCheckBoxDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulCheckBoxDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see XulCheckBoxDehydrator
 */
public class XulCheckBoxDehydratorTest extends AbstractXmlRenderKitTest<CheckBox>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<CheckBox> createRenderKit()
	{
		return StubUiComponentRenderKit.get(CheckBox.class, new XulCheckBoxDehydrator<CheckBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateCheckBox() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		
		assertDehydrate("<checkbox/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithText() throws HydrationException
	{
		CheckBox checkBox = new CheckBox("text");
		
		assertDehydrate("<checkbox label=\"text\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWhenSelected() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		checkBox.setSelected(true);
		
		assertDehydrate("<checkbox checked=\"true\"/>", checkBox);
	}
}
