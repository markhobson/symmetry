/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/AnchorHtmlButtonHydratorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: AnchorHtmlButtonHydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
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
