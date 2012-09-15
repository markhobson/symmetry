/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

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

import static org.hobsoft.symmetry.ui.test.event.MockActionListeners.createMockActionListener;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code InputHtmlButtonHydrator}.
 * 
 * @author Mark Hobson
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
