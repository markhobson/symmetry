/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulLabelDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulLabelDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulLabelDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
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
