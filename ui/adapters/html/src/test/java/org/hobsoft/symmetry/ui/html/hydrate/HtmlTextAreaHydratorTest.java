/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextAreaHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.state.PropertyState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.support.bean.Properties;
import org.hobsoft.symmetry.ui.TextArea;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code HtmlTextAreaHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTextAreaHydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see HtmlTextAreaHydrator
 */
public class HtmlTextAreaHydratorTest extends AbstractXmlRenderKitTest<TextArea>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<TextArea> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(TextArea.class, new HtmlTextAreaHydrator<TextArea>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(new TextArea());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydrateTextArea() throws HydrationException
	{
		TextArea textArea = new TextArea();
		
		assertDehydrate("<textarea class=\"textarea\" cols=\"20\" rows=\"3\"></textarea>", textArea);
	}
	
	@Test
	public void dehydrateTextAreaWithText() throws HydrationException
	{
		TextArea textArea = new TextArea("text");
		getStateCodec().setEncodedPropertyValue(textArea, TextBox.TEXT_PROPERTY, "text", "x");
		
		assertDehydrate("<textarea class=\"textarea\" cols=\"20\" rows=\"3\">x</textarea>", textArea);
	}
	
	@Test
	public void dehydrateTextAreaWithMaxLength() throws HydrationException
	{
		TextArea textArea = new TextArea();
		textArea.setMaxLength(10);
		
		// maxLength should be ignored
		assertDehydrate("<textarea class=\"textarea\" cols=\"20\" rows=\"3\"></textarea>", textArea);
	}

	@Test
	public void dehydrateTextAreaWithReadOnly() throws HydrationException
	{
		TextArea textArea = new TextArea();
		textArea.setReadOnly(true);
		
		assertDehydrate("<textarea class=\"textarea\" cols=\"20\" rows=\"3\" readonly=\"readonly\"></textarea>",
			textArea);
	}

	@Test
	public void rehydrateWithTextProperty() throws HydrationException
	{
		TextArea textArea = new TextArea();
		getRehydrationContext().get(State.class).addProperty(new PropertyState(textArea, getTextDescriptor(), "a"));
		
		rehydrate(textArea);
		
		assertEquals("a", textArea.getText());
	}
	
	@Test
	public void rehydrateWithTextParameter() throws HydrationException
	{
		TextArea textArea = new TextArea();
		getStateCodec().setEncodedBean(textArea, "1");
		getStateCodec().setEncodedPropertyValue(textArea, TextBox.TEXT_PROPERTY, "a", "a");
		setEncodedState(createEncodedState("1", "a"));
		
		rehydrate(textArea);
		
		assertEquals("a", textArea.getText());
	}
	
	@Test(expected = HydrationException.class)
	public void rehydrateWithMultivaluedTextParameter() throws HydrationException
	{
		TextArea textArea = new TextArea();
		getStateCodec().setEncodedBean(textArea, "1");
		setEncodedState(createEncodedState("1", "a", "a"));
		
		rehydrate(textArea);
	}
	
	// private methods --------------------------------------------------------
	
	private static PropertyDescriptor getTextDescriptor()
	{
		return Properties.getDescriptor(TextArea.class, TextBox.TEXT_PROPERTY);
	}
}
