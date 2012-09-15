/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulLabelDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulLabelDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulLabelDehydrator
 */
public class XulLabelDehydratorTest extends AbstractXmlRenderKitTest<Label>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Label> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Label.class, new XulLabelDehydrator<Label>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateLabel() throws HydrationException
	{
		Label label = new Label();
		
		assertDehydrate("<label/>", label);
	}
	
	@Test
	public void dehydrateLabelWithText() throws HydrationException
	{
		Label label = new Label("text");
		
		assertDehydrate("<label value=\"text\"/>", label);
	}
	
	@Test
	public void dehydrateLabelWithToolTip() throws HydrationException
	{
		Label label = new Label();
		label.setToolTip("a");
		
		assertDehydrate("<label tooltiptext=\"a\"/>", label);
	}
}
