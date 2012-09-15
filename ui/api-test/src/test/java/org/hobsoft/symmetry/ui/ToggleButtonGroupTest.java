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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code ToggleButtonGroup}.
 * 
 * @author Mark Hobson
 * @see ToggleButtonGroup
 */
public class ToggleButtonGroupTest
{
	// fields -----------------------------------------------------------------

	private ToggleButtonGroup group;
	
	// public methods ---------------------------------------------------------

	@Before
	public void setUp()
	{
		group = new ToggleButtonGroup();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setSelectedButtonWithChild()
	{
		ToggleButton button1 = new ToggleButton();
		ToggleButton button2 = new ToggleButton();
		group.add(button1, button2);
		
		group.setSelectedButton(button2);
		
		assertTrue(button2.isSelected());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setSelectedButtonWithNonChild()
	{
		group.setSelectedButton(new ToggleButton());
	}

	@Test
	public void setSelectedButtonWithNull()
	{
		ToggleButton button = new ToggleButton();
		button.setSelected(true);
		group.add(button);
		
		group.setSelectedButton(null);
		
		assertFalse(button.isSelected());
	}
}
