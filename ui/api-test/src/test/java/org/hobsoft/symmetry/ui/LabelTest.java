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
package org.hobsoft.symmetry.ui;

import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

import static org.hobsoft.symmetry.support.test.matcher.PropertyChangeEventMatcher.mockPropertyChangeListener;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Label}.
 * 
 * @author Mark Hobson
 * @see Label
 */
public class LabelTest extends AbstractComponentTest<Label>
{
	// fields -----------------------------------------------------------------
	
	private Label label;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		label = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setText()
	{
		label.setText("a");
		
		assertEquals("a", label.getText());
	}
	
	@Test
	public void setTextFiresEvent()
	{
		PropertyChangeListener listener = mockPropertyChangeListener(getMockery(), label, "text", "", "a");
		label.addPropertyChangeListener("text", listener);
		label.setText("a");
	}
	
	@Test
	public void setTextWithNull()
	{
		label.setText(null);
		
		assertEquals("", label.getText());
	}
	
	@Test
	public void setTextWithNullTranslatesEvent()
	{
		label.setText("a");
		
		PropertyChangeListener listener = mockPropertyChangeListener(getMockery(), label, "text", "a", "");
		label.addPropertyChangeListener("text", listener);
		label.setText(null);
	}
	
	@Test
	public void setToolTip()
	{
		label.setToolTip("a");
		
		assertEquals("a", label.getToolTip());
	}
	
	@Test
	public void setToolTipWithNull()
	{
		label.setToolTip(null);
		
		assertEquals("", label.getToolTip());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Label createComponent()
	{
		return new Label();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeToken<Label> getComponentType()
	{
		return TypeToken.of(Label.class);
	}
}
