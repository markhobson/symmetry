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
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Label;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@code LabelListCellRenderer}.
 * 
 * @author Mark Hobson
 * @see LabelListCellRenderer
 */
public class LabelListCellRendererTest
{
	// fields -----------------------------------------------------------------
	
	private LabelListCellRenderer<Object> renderer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		renderer = new LabelListCellRenderer<Object>();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getListCellComponentWithFirstItem()
	{
		assertLabelText("x", renderer.getListCellComponent(new ComboBox<Object>("x"), 0));
	}
	
	@Test
	public void getListCellComponentWithSecondItem()
	{
		assertLabelText("y", renderer.getListCellComponent(new ComboBox<Object>("x", "y"), 1));
	}
	
	@Test
	public void getListCellComponentWithNullItem()
	{
		assertLabelText("", renderer.getListCellComponent(new ComboBox<Object>((Object) null), 0));
	}
	
	@Test(expected = NullPointerException.class)
	public void getListCellComponentWithNullComboBox()
	{
		renderer.getListCellComponent(null, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getListCellComponentWithNegativeItemIndex()
	{
		renderer.getListCellComponent(new ComboBox<Object>(), -1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getListCellComponentWithTooLargeItemIndex()
	{
		renderer.getListCellComponent(new ComboBox<Object>("x"), 1);
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertLabelText(String expected, Label actualLabel)
	{
		assertNotNull("label", actualLabel);
		assertEquals("text", expected, actualLabel.getText());
	}
}