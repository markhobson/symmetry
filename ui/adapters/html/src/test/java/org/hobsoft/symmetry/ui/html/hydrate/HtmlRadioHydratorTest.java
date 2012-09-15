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

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.state.PropertyState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.support.bean.Properties;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code HtmlRadioHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlRadioHydrator
 */
public class HtmlRadioHydratorTest extends AbstractXmlRenderKitTest<Radio>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Radio> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(Radio.class, new HtmlRadioHydrator<Radio>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(new Radio());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydrateRadio() throws HydrationException
	{
		Radio radio = new Radio();
		
		assertDehydrate("<input class=\"radio\" type=\"radio\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithText() throws HydrationException
	{
		Radio radio = new Radio("text");
		
		assertDehydrate("<input class=\"radio\" type=\"radio\"/><label class=\"radio\">text</label>", radio);
	}
	
	@Test
	public void dehydrateRadioWithId() throws HydrationException
	{
		Radio radio = new Radio();
		getIdEncoder().setEncodedObject(radio, "1");
		
		assertDehydrate("<input class=\"radio\" id=\"1\" type=\"radio\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithName() throws HydrationException
	{
		Radio radio = new Radio();
		getStateCodec().setEncodedBean(radio, "1");
		
		assertDehydrate("<input class=\"radio\" name=\"1\" type=\"radio\" value=\"1\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithIdAndText() throws HydrationException
	{
		Radio radio = new Radio("text");
		getIdEncoder().setEncodedObject(radio, "1");
		
		assertDehydrate("<input class=\"radio\" id=\"1\" type=\"radio\"/>"
			+ "<label class=\"radio\" for=\"1\">text</label>", radio);
	}
	
	@Test
	public void dehydrateRadioWithMnemonic() throws HydrationException
	{
		Radio radio = new Radio("text");
		radio.setMnemonic('x');
		
		assertDehydrate("<input class=\"radio\" type=\"radio\" accesskey=\"x\"/>"
			+ "<label class=\"radio\">te<span class=\"mnemonic\">x</span>t</label>", radio);
	}
	
	@Test
	public void dehydrateRadioWithToolTip() throws HydrationException
	{
		Radio radio = new Radio();
		radio.setToolTip("toolTip");
		
		assertDehydrate("<input class=\"radio\" type=\"radio\" title=\"toolTip\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithToolTipAndText() throws HydrationException
	{
		Radio radio = new Radio("text");
		radio.setToolTip("toolTip");
		
		assertDehydrate("<input class=\"radio\" type=\"radio\" title=\"toolTip\"/>"
			+ "<label class=\"radio\" title=\"toolTip\">text</label>", radio);
	}
	
	@Test
	public void dehydrateRadioSelected() throws HydrationException
	{
		Radio radio = new Radio();
		radio.setSelected(true);
		
		assertDehydrate("<input class=\"radio\" type=\"radio\" checked=\"checked\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithGroup() throws HydrationException
	{
		ToggleButtonGroup group = new ToggleButtonGroup();
		
		Radio radio = new Radio();
		group.add(radio);
		
		assertDehydrate("<input class=\"radio\" type=\"radio\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithGroupWithName() throws HydrationException
	{
		ToggleButtonGroup group = new ToggleButtonGroup();
		getStateCodec().setEncodedBean(group, "1");
		
		Radio radio = new Radio();
		group.add(radio);
		
		assertDehydrate("<input class=\"radio\" name=\"1\" type=\"radio\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithDistantGroupWithName() throws HydrationException
	{
		ToggleButtonGroup group = new ToggleButtonGroup();
		getStateCodec().setEncodedBean(group, "1");
		
		Radio radio = new Radio();
		group.add(new Box(radio));
		
		assertDehydrate("<input class=\"radio\" name=\"1\" type=\"radio\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithNameAndGroupWithName() throws HydrationException
	{
		ToggleButtonGroup group = new ToggleButtonGroup();
		getStateCodec().setEncodedBean(group, "1");
		
		Radio radio = new Radio();
		getStateCodec().setEncodedBean(radio, "2");
		group.add(radio);
		
		assertDehydrate("<input class=\"radio\" name=\"1\" type=\"radio\" value=\"2\"/>", radio);
	}

	@Test
	public void rehydrateWithSelectedProperty() throws HydrationException
	{
		Radio radio = new Radio();
		getRehydrationContext().get(State.class)
			.addProperty(new PropertyState(radio, getSelectedDescriptor(), true));
		
		rehydrate(radio);
		
		assertTrue("selected", radio.isSelected());
	}
	
	@Test
	public void rehydrateWithSelectedParameter() throws HydrationException
	{
		Radio radio = new Radio();
		getStateCodec().setEncodedBean(radio, "2");
		
		ToggleButtonGroup buttonGroup = new ToggleButtonGroup(radio);
		getStateCodec().setEncodedBean(buttonGroup, "1");
		
		setEncodedState(createEncodedState("1", "2"));
		
		rehydrate(radio);
		
		assertTrue("selected", radio.isSelected());
	}
	
	@Test
	public void rehydrateWithDifferentSelectedParameter() throws HydrationException
	{
		Radio radio1 = new Radio();
		getStateCodec().setEncodedBean(radio1, "2");
		
		Radio radio2 = new Radio();
		getStateCodec().setEncodedBean(radio2, "3");
		
		ToggleButtonGroup buttonGroup = new ToggleButtonGroup(radio1, radio2);
		getStateCodec().setEncodedBean(buttonGroup, "1");
		
		setEncodedState(createEncodedState("1", "3"));
		
		rehydrate(radio1);
		
		assertFalse("selected", radio1.isSelected());
	}
	
	/**
	 * Test that a parameter to select an earlier radio takes precedence over a property that selects an earlier one.
	 * 
	 * @throws HydrationException
	 *             if an error occurs
	 */
	@Test
	public void rehydrateWithDifferentSelectedPropertyAndSelectedParameter() throws HydrationException
	{
		Radio radio1 = new Radio();
		getStateCodec().setEncodedBean(radio1, "2");
		
		Radio radio2 = new Radio();
		getStateCodec().setEncodedBean(radio2, "3");
		
		ToggleButtonGroup buttonGroup = new ToggleButtonGroup(radio1, radio2);
		getStateCodec().setEncodedBean(buttonGroup, "1");
		
		getStateCodec().getState().addProperty(new PropertyState(radio2, getSelectedDescriptor(), true));
		setEncodedState(createEncodedState("1", "2"));
		
		rehydrate(radio1);
		rehydrate(radio2);
		
		assertTrue("selected", radio1.isSelected());
	}

	// private methods --------------------------------------------------------
	
	private static PropertyDescriptor getSelectedDescriptor()
	{
		return Properties.getDescriptor(Radio.class, Selectable.SELECTED_PROPERTY);
	}
}
