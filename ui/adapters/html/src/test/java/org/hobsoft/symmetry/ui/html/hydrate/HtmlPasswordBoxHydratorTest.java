/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlPasswordBoxHydratorTest.java $
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
import org.hobsoft.symmetry.ui.PasswordBox;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code HtmlPasswordBoxHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlPasswordBoxHydrator
 */
public class HtmlPasswordBoxHydratorTest extends AbstractXmlRenderKitTest<PasswordBox>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<PasswordBox> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(PasswordBox.class, new HtmlPasswordBoxHydrator<PasswordBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(new PasswordBox());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydratePasswordBox() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		
		assertDehydrate("<input class=\"passwordbox\" type=\"password\" size=\"20\"/>", passwordBox);
	}
	
	@Test
	public void dehydratePasswordBoxWithText() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox("text");
		getStateCodec().setEncodedPropertyValue(passwordBox, TextBox.TEXT_PROPERTY, "text", "x");
		
		// don't expect @value for security reasons
		assertDehydrate("<input class=\"passwordbox\" type=\"password\" size=\"20\"/>", passwordBox);
	}
	
	@Test
	public void dehydratePasswordBoxWithId() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		getIdEncoder().setEncodedObject(passwordBox, "1");
		
		assertDehydrate("<input class=\"passwordbox\" id=\"1\" type=\"password\" size=\"20\"/>", passwordBox);
	}
	
	@Test
	public void dehydratePasswordBoxWithName() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		getStateCodec().setEncodedBean(passwordBox, "1");
		
		assertDehydrate("<input class=\"passwordbox\" name=\"1\" type=\"password\" size=\"20\"/>", passwordBox);
	}
	
	@Test
	public void dehydratePasswordBoxWithMaxLength() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		passwordBox.setMaxLength(10);
		
		assertDehydrate("<input class=\"passwordbox\" type=\"password\" size=\"20\" maxlength=\"10\"/>", passwordBox);
	}
	
	@Test
	public void dehydratePasswordBoxWithDisabled() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		passwordBox.setEnabled(false);
		
		assertDehydrate("<input class=\"passwordbox\" type=\"password\" size=\"20\" disabled=\"disabled\"/>",
			passwordBox);
	}
	
	@Test
	public void dehydrateTextBoxWithReadOnly() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		passwordBox.setReadOnly(true);
		
		assertDehydrate("<input class=\"passwordbox\" type=\"password\" size=\"20\" readonly=\"readonly\"/>",
			passwordBox);
	}
	
	@Test
	public void rehydrateWithTextProperty() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		getRehydrationContext().get(State.class).addProperty(new PropertyState(passwordBox, getTextDescriptor(), "a"));
		
		rehydrate(passwordBox);
		
		assertEquals("a", passwordBox.getText());
	}
	
	@Test
	public void rehydrateWithTextParameter() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		getStateCodec().setEncodedBean(passwordBox, "1");
		getStateCodec().setEncodedPropertyValue(passwordBox, TextBox.TEXT_PROPERTY, "a", "a");
		setEncodedState(createEncodedState("1", "a"));
		
		rehydrate(passwordBox);
		
		assertEquals("a", passwordBox.getText());
	}
	
	@Test(expected = HydrationException.class)
	public void rehydrateWithMultivaluedTextParameter() throws HydrationException
	{
		PasswordBox passwordBox = new PasswordBox();
		getStateCodec().setEncodedBean(passwordBox, "1");
		setEncodedState(createEncodedState("1", "a", "a"));
		
		rehydrate(passwordBox);
	}
	
	// private methods --------------------------------------------------------
	
	private static PropertyDescriptor getTextDescriptor()
	{
		return Properties.getDescriptor(PasswordBox.class, TextBox.TEXT_PROPERTY);
	}
}
