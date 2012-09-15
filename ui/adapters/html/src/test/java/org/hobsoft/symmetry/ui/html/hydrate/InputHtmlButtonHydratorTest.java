/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/InputHtmlButtonHydratorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.hobsoft.symmetry.ui.test.event.MockActionListeners.createMockActionListener;
import static org.junit.Assert.assertTrue;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.ui.test.event.DummyActionListener;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code InputHtmlButtonHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: InputHtmlButtonHydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see InputHtmlButtonHydrator
 */
@RunWith(JMock.class)
public class InputHtmlButtonHydratorTest extends AbstractXmlRenderKitTest<Button>
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Button> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(Button.class, new InputHtmlButtonHydrator<Button>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(new Button());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydrateButtonWithText() throws HydrationException
	{
		Button button = new Button("label");
		
		assertDehydrate("<input class=\"button\" type=\"button\" value=\"label\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithImage() throws HydrationException
	{
		Button button = new Button(new Image("http://a/b"));
		
		// expect image to be ignored
		assertDehydrate("<input class=\"button\" type=\"button\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithId() throws HydrationException
	{
		Button button = new Button("label");
		getIdEncoder().setEncodedObject(button, "1");
		
		assertDehydrate("<input id=\"1\" class=\"button\" type=\"button\" value=\"label\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithName() throws HydrationException
	{
		Button button = new Button("label");
		getStateCodec().setEncodedBean(button, "1");
		
		assertDehydrate("<input class=\"button\" name=\"1\" type=\"button\" value=\"label\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithToolTip() throws HydrationException
	{
		Button button = new Button();
		button.setToolTip("toolTip");
		
		assertDehydrate("<input class=\"button\" type=\"button\" title=\"toolTip\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithMnemonic() throws HydrationException
	{
		Button button = new Button("label");
		button.setMnemonic('a');
		
		assertDehydrate("<input class=\"button\" type=\"button\" accesskey=\"a\" value=\"label\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithDisabled() throws HydrationException
	{
		Button button = new Button();
		button.setEnabled(false);
		
		assertDehydrate("<input class=\"button button-disabled\" type=\"button\" disabled=\"disabled\"/>", button);
	}
	
	@Test
	public void dehydrateButtonWithActionListener() throws HydrationException
	{
		Button button = new Button();
		button.addActionListener(new DummyActionListener());
		
		assertDehydrate("<input class=\"button\" type=\"button\" onclick=\"state\"/>", button);
	}
	
	@Test
	public void rehydrateWithActionParameter() throws HydrationException
	{
		Button button = new Button();
		button.addActionListener(createMockActionListener(mockery, true));
		getStateCodec().setEncodedBean(button, "1");
		setEncodedState(createEncodedState("1"));
		
		rehydrate(button);
	}
	
	@Test
	public void rehydrateWithImageActionParameters() throws HydrationException
	{
		Button button = new Button();
		button.addActionListener(createMockActionListener(mockery, true));
		getStateCodec().setEncodedBean(button, "1");
		setEncodedState(createEncodedState("1.x", "0", "1.y", "0"));
		
		rehydrate(button);
	}
	
	@Test
	public void rehydrateWithNoParameters() throws HydrationException
	{
		Button button = new Button();
		button.addActionListener(createMockActionListener(mockery, false));
		getStateCodec().setEncodedBean(button, "1");
		
		rehydrate(button);
	}
}
