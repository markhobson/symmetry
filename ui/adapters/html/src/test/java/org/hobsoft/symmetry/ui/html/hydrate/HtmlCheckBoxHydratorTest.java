/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlCheckBoxHydratorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code HtmlCheckBoxHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlCheckBoxHydrator
 */
public class HtmlCheckBoxHydratorTest extends AbstractXmlRenderKitTest<CheckBox>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<CheckBox> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(CheckBox.class, new HtmlCheckBoxHydrator<CheckBox>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(new CheckBox());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydrateCheckBox() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		
		assertDehydrate("<input class=\"checkbox\" type=\"checkbox\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithText() throws HydrationException
	{
		CheckBox checkBox = new CheckBox("text");
		
		assertDehydrate("<input class=\"checkbox\" type=\"checkbox\"/>"
			+ "<label class=\"checkbox\">text</label>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithId() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		getIdEncoder().setEncodedObject(checkBox, "1");
		
		assertDehydrate("<input class=\"checkbox\" id=\"1\" type=\"checkbox\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithName() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		getStateCodec().setEncodedBean(checkBox, "1");
		
		assertDehydrate("<input class=\"checkbox\" name=\"1\" type=\"checkbox\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithValue() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		getStateCodec().setEncodedPropertyValue(checkBox, Selectable.SELECTED_PROPERTY, false, "x");
		
		assertDehydrate("<input class=\"checkbox\" value=\"x\" type=\"checkbox\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithDefaultValue() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		getStateCodec().setEncodedPropertyValue(checkBox, Selectable.SELECTED_PROPERTY, false, "on");
		
		assertDehydrate("<input class=\"checkbox\" type=\"checkbox\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithIdAndText() throws HydrationException
	{
		CheckBox checkBox = new CheckBox("text");
		getIdEncoder().setEncodedObject(checkBox, "1");
		
		assertDehydrate("<input class=\"checkbox\" id=\"1\" type=\"checkbox\"/>"
			+ "<label class=\"checkbox\" for=\"1\">text</label>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithMnemonic() throws HydrationException
	{
		CheckBox checkBox = new CheckBox("text");
		checkBox.setMnemonic('x');
		
		assertDehydrate("<input class=\"checkbox\" type=\"checkbox\" accesskey=\"x\"/>"
			+ "<label class=\"checkbox\">te<span class=\"mnemonic\">x</span>t</label>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithToolTip() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		checkBox.setToolTip("toolTip");
		
		assertDehydrate("<input class=\"checkbox\" type=\"checkbox\" title=\"toolTip\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxWithToolTipAndText() throws HydrationException
	{
		CheckBox checkBox = new CheckBox("text");
		checkBox.setToolTip("toolTip");
		
		assertDehydrate("<input class=\"checkbox\" type=\"checkbox\" title=\"toolTip\"/>"
			+ "<label class=\"checkbox\" title=\"toolTip\">text</label>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxSelected() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		checkBox.setSelected(true);
		
		assertDehydrate("<input class=\"checkbox\" type=\"checkbox\" checked=\"checked\"/>", checkBox);
	}
	
	@Test
	public void dehydrateCheckBoxDisabled() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		checkBox.setEnabled(false);
		
		assertDehydrate("<input class=\"checkbox checkbox-disabled\" type=\"checkbox\" disabled=\"disabled\"/>",
			checkBox);
	}
	
	@Test
	public void rehydrateWithSelectedParameter() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		getStateCodec().setEncodedBean(checkBox, "1");
		getStateCodec().setEncodedPropertyValue(checkBox, Selectable.SELECTED_PROPERTY, true, "true");
		setEncodedState(createEncodedState("1", "true"));
		
		rehydrate(checkBox);
		
		assertTrue("selected", checkBox.isSelected());
	}
	
	@Test
	public void rehydrateWithUnselectedParameter() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		checkBox.setSelected(true);
		getStateCodec().setEncodedBean(checkBox, "1");
		getStateCodec().setEncodedPropertyValue(checkBox, Selectable.SELECTED_PROPERTY, false, "false");
		setEncodedState(createEncodedState("1", "false"));
		
		rehydrate(checkBox);
		
		assertFalse("selected", checkBox.isSelected());
	}
	
	@Test
	public void rehydrateWithNoSelectedParameter() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		checkBox.setSelected(true);
		getStateCodec().setEncodedBean(checkBox, "1");
		getStateCodec().setEncodedPropertyValue(checkBox, Selectable.SELECTED_PROPERTY, false, null);
		
		rehydrate(checkBox);
		
		assertFalse("selected", checkBox.isSelected());
	}
	
	@Test(expected = HydrationException.class)
	public void rehydrateWithMultivaluedSelectedParameter() throws HydrationException
	{
		CheckBox checkBox = new CheckBox();
		getStateCodec().setEncodedBean(checkBox, "1");
		setEncodedState(createEncodedState("1", "true", "true"));
		
		rehydrate(checkBox);
	}
}
