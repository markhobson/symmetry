/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulRadioDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulRadioDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulRadioDehydrator
 */
public class XulRadioDehydratorTest extends AbstractXmlRenderKitTest<Radio>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Radio> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Radio.class, new XulRadioDehydrator<Radio>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateRadio() throws HydrationException
	{
		Radio radio = new Radio();
		
		assertDehydrate("<radio/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithText() throws HydrationException
	{
		Radio radio = new Radio("text");
		
		assertDehydrate("<radio label=\"text\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWhenSelected() throws HydrationException
	{
		Radio radio = new Radio();
		radio.setSelected(true);
		
		assertDehydrate("<radio checked=\"true\"/>", radio);
	}
}
