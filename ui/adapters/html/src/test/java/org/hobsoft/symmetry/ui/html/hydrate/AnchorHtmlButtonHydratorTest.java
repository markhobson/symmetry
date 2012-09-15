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

import java.beans.EventSetDescriptor;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.state.EventSetState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.support.bean.EventSets;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.hobsoft.symmetry.ui.test.event.DummyActionListener;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code AnchorHtmlButtonHydrator}.
 * 
 * @author Mark Hobson
 * @see AnchorHtmlButtonHydrator
 */
@RunWith(JMock.class)
public class AnchorHtmlButtonHydratorTest extends AbstractXmlRenderKitTest<Button>
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
		return StubPhasedUiComponentRenderKit.get(Button.class, new AnchorHtmlButtonHydrator<Button>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateButtonWithText() throws HydrationException
	{
		Button button = new Button("label");
		
		assertDehydrate("<span class=\"button\"><a>label</a></span>", button);
	}
	
	@Test
	public void dehydrateButtonWithImage() throws HydrationException
	{
		Button button = new Button(new Image("http://a/b"));
		
		assertDehydrate("<span class=\"button\"><a><img src=\"http://a/b\"/></a></span>", button);
	}
	
	@Test
	public void dehydrateButtonWithToolTip() throws HydrationException
	{
		Button button = new Button();
		button.setToolTip("toolTip");
		
		assertDehydrate("<span class=\"button\"><a title=\"toolTip\"></a></span>", button);
	}
	
	@Test
	public void dehydrateButtonWithMnemonic() throws HydrationException
	{
		Button button = new Button("label");
		button.setMnemonic('a');
		
		assertDehydrate("<span class=\"button\">"
			+ "<a accesskey=\"a\">l<span class=\"mnemonic\">a</span>bel</a>"
			+ "</span>", button);
	}
	
	@Test
	public void dehydrateButtonWithDisabled() throws HydrationException
	{
		Button button = new Button();
		button.setEnabled(false);
		
		assertDehydrate("<span class=\"button button-disabled\"><a></a></span>", button);
	}
	
	@Test
	public void dehydrateButtonWithActionListener() throws HydrationException
	{
		Button button = new Button();
		button.addActionListener(new DummyActionListener());
		
		assertDehydrate("<span class=\"button\"><a href=\"state\"></a></span>", button);
	}
	
	@Test
	public void rehydrateWithActionEvent() throws HydrationException
	{
		final Button button = new Button();

		final ActionListener listener = mockery.mock(ActionListener.class);
		button.addActionListener(listener);
		
		mockery.checking(new Expectations() { {
			oneOf(listener).actionPerformed(new ActionEvent(button));
		} });
		
		getRehydrationContext().get(State.class)
			.addEvent(new EventSetState(getActionDescriptor(), new ActionEvent(button)));
		
		rehydrate(button);
	}
	
	// private methods --------------------------------------------------------
	
	private static EventSetDescriptor getActionDescriptor()
	{
		return EventSets.getDescriptor(Button.class, Button.ACTION_EVENT);
	}
}
