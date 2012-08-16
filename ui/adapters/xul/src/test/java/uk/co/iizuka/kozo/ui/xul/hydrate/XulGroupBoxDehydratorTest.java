/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulGroupBoxDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulGroupBoxDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulGroupBoxDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see XulGroupBoxDehydrator
 */
public class XulGroupBoxDehydratorTest extends AbstractXmlRenderKitTest<GroupBox>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<GroupBox> createRenderKit()
	{
		return StubUiComponentRenderKit.get(GroupBox.class, new XulGroupBoxDehydrator<GroupBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateGroupBox() throws HydrationException
	{
		GroupBox groupBox = new GroupBox();
		
		assertDehydrate("<groupbox></groupbox>", groupBox);
	}
	
	@Test
	public void dehydrateGroupBoxWithTitle() throws HydrationException
	{
		GroupBox groupBox = new GroupBox("text");
		
		assertDehydrate("<groupbox><caption label=\"text\"/></groupbox>", groupBox);
	}
}
