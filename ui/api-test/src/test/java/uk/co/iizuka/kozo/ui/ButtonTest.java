/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ButtonTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertEquals;

import com.googlecode.jtype.Generic;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;

/**
 * Tests {@code Button}.
 * 
 * @author Mark Hobson
 * @version $Id: ButtonTest.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @see Button
 */
public class ButtonTest extends AbstractComponentTest<Button>
{
	// fields -----------------------------------------------------------------
	
	private Button button;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		button = getComponent();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newButtonWithImage()
	{
		Image image = createImage();
		button = new Button(image);
		
		assertEquals("text", "", button.getText());
		assertEquals("image", image, button.getImage());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Button createComponent()
	{
		return new Button();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Button> getComponentType()
	{
		return Generic.get(Button.class);
	}
	
	// private methods --------------------------------------------------------
	
	private static Image createImage()
	{
		return new Image(new byte[] {0, 1, 2});
	}
}
