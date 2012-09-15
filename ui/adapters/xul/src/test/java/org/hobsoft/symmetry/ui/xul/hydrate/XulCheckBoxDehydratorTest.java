/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulCheckBoxDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulCheckBoxDehydrator}.
 * 
 * @author Mark Hobson
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
