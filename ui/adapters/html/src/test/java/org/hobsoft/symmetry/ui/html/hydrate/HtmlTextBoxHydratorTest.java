/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextBoxHydratorTest.java $
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
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code HtmlTextBoxHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTextBoxHydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see HtmlTextBoxHydrator
 */
public class HtmlTextBoxHydratorTest extends AbstractXmlRenderKitTest<TextBox>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<TextBox> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(TextBox.class, new HtmlTextBoxHydrator<TextBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(new TextBox());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydrateTextBox() throws HydrationException
	{
		TextBox textBox = new TextBox();
		
		assertDehydrate("<input class=\"textbox\" type=\"text\" size=\"20\"/>", textBox);
	}
	
	@Test
	public void dehydrateTextBoxWithText() throws HydrationException
	{
		TextBox textBox = new TextBox("text");
		getStateCodec().setEncodedPropertyValue(textBox, TextBox.TEXT_PROPERTY, "text", "x");
		
		assertDehydrate("<input class=\"textbox\" type=\"text\" value=\"x\" size=\"20\"/>", textBox);
	}
	
	@Test
	public void dehydrateTextBoxWithId() throws HydrationException
	{
		TextBox textBox = new TextBox();
		getIdEncoder().setEncodedObject(textBox, "1");
		
		assertDehydrate("<input class=\"textbox\" id=\"1\" type=\"text\" size=\"20\"/>", textBox);
	}
	
	@Test
	public void dehydrateTextBoxWithName() throws HydrationException
	{
		TextBox textBox = new TextBox();
		getStateCodec().setEncodedBean(textBox, "1");
		
		assertDehydrate("<input class=\"textbox\" name=\"1\" type=\"text\" size=\"20\"/>", textBox);
	}
	
	@Test
	public void dehydrateTextBoxWithMaxLength() throws HydrationException
	{
		TextBox textBox = new TextBox();
		textBox.setMaxLength(10);
		
		assertDehydrate("<input class=\"textbox\" type=\"text\" size=\"20\" maxlength=\"10\"/>", textBox);
	}
	
	@Test
	public void dehydrateTextBoxWithDisabled() throws HydrationException
	{
		TextBox textBox = new TextBox();
		textBox.setEnabled(false);
		
		assertDehydrate("<input class=\"textbox\" type=\"text\" size=\"20\" disabled=\"disabled\"/>",
			textBox);
	}
	
	@Test
	public void dehydrateTextBoxWithReadOnly() throws HydrationException
	{
		TextBox textBox = new TextBox();
		textBox.setReadOnly(true);
		
		assertDehydrate("<input class=\"textbox\" type=\"text\" size=\"20\" readonly=\"readonly\"/>", textBox);
	}
	
	@Test
	public void rehydrateWithTextProperty() throws HydrationException
	{
		TextBox textBox = new TextBox();
		getRehydrationContext().get(State.class).addProperty(new PropertyState(textBox, getTextDescriptor(), "a"));
		
		rehydrate(textBox);
		
		assertEquals("a", textBox.getText());
	}
	
	@Test
	public void rehydrateWithTextParameter() throws HydrationException
	{
		TextBox textBox = new TextBox();
		getStateCodec().setEncodedBean(textBox, "1");
		getStateCodec().setEncodedPropertyValue(textBox, TextBox.TEXT_PROPERTY, "a", "a");
		setEncodedState(createEncodedState("1", "a"));
		
		rehydrate(textBox);
		
		assertEquals("a", textBox.getText());
	}
	
	@Test(expected = HydrationException.class)
	public void rehydrateWithMultivaluedTextParameter() throws HydrationException
	{
		TextBox textBox = new TextBox();
		getStateCodec().setEncodedBean(textBox, "1");
		setEncodedState(createEncodedState("1", "a", "a"));
		
		rehydrate(textBox);
	}
	
	// private methods --------------------------------------------------------
	
	private static PropertyDescriptor getTextDescriptor()
	{
		return Properties.getDescriptor(TextBox.class, TextBox.TEXT_PROPERTY);
	}
}
