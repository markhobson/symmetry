/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulGroupBoxDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

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
