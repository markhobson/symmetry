/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/ButtonHtmlButtonHydratorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.state.EncodedState;
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

import static org.hobsoft.symmetry.ui.test.event.MockActionListeners.createMockActionListener;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code ButtonHtmlButtonHydrator}.
 * 
 * @author Mark Hobson
 * @see ButtonHtmlButtonHydrator
 */
@RunWith(JMock.class)
public class ButtonHtmlButtonHydratorTest extends AbstractXmlRenderKitTest<Button>
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
		return StubPhasedUiComponentRenderKit.get(Button.class, new ButtonHtmlButtonHydrator<Button>());
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
		
		assertDehydrate("<button class=\"button\" type=\"button\">label</button>", button);
	}
	
	@Test
	public void dehydrateButtonWithImage() throws HydrationException
	{
		Button button = new Button(new Image("http://a/b"));
		
		assertDehydrate("<button class=\"button\" type=\"button\"><img src=\"http://a/b\"/></button>", button);
	}
	
	@Test
	public void dehydrateButtonWithId() throws HydrationException
	{
		Button button = new Button("label");
		getIdEncoder().setEncodedObject(button, "1");
		
		assertDehydrate("<button id=\"1\" class=\"button\" type=\"button\">label</button>", button);
	}
	
	@Test
	public void dehydrateButtonWithName() throws HydrationException
	{
		Button button = new Button("label");
		getStateCodec().setEncodedBean(button, "1");
		
		assertDehydrate("<button class=\"button\" name=\"1\" type=\"button\">label</button>", button);
	}
	
	@Test
	public void dehydrateButtonWithToolTip() throws HydrationException
	{
		Button button = new Button();
		button.setToolTip("toolTip");
		
		assertDehydrate("<button class=\"button\" type=\"button\" title=\"toolTip\"></button>", button);
	}
	
	@Test
	public void dehydrateButtonWithMnemonic() throws HydrationException
	{
		Button button = new Button("label");
		button.setMnemonic('a');
		
		assertDehydrate("<button class=\"button\" type=\"button\" accesskey=\"a\">"
			+ "l<span class=\"mnemonic\">a</span>bel"
			+ "</button>", button);
	}
	
	@Test
	public void dehydrateButtonWithDisabled() throws HydrationException
	{
		Button button = new Button();
		button.setEnabled(false);
		
		assertDehydrate("<button class=\"button button-disabled\" type=\"button\" disabled=\"disabled\"></button>",
			button);
	}
	
	@Test
	public void dehydrateButtonWithActionListener() throws HydrationException
	{
		Button button = new Button();
		button.addActionListener(new DummyActionListener());
		
		assertDehydrate("<button class=\"button\" type=\"button\" onclick=\"state\"></button>", button);
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
	
	// private methods --------------------------------------------------------
	
	private static EncodedState createEncodedState(String parameterName1, Object parameterValue1, String parameterName2,
		Object parameterValue2)
	{
		Map<String, List<Object>> parameters = new HashMap<String, List<Object>>();
		parameters.put(parameterName1, Collections.singletonList(parameterValue1));
		parameters.put(parameterName2, Collections.singletonList(parameterValue2));
		
		return new EncodedState(parameters);
	}
}
