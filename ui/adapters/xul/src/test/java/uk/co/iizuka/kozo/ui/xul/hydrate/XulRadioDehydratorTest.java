/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulRadioDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulRadioDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulRadioDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
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
