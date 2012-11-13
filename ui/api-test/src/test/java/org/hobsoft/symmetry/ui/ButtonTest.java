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

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Button}.
 * 
 * @author Mark Hobson
 * @see Button
 */
public class ButtonTest extends AbstractComponentTest<Button>
{
	// fields -----------------------------------------------------------------
	
	private Button button;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		button = getComponent();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newButtonWithImage()
	{
		Image image = createImage();
		button = new Button(image);
		
		assertEquals("text", "", button.getText());
		assertEquals("image", image, button.getImage());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Button createComponent()
	{
		return new Button();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeToken<Button> getComponentType()
	{
		return TypeToken.of(Button.class);
	}
	
	// private methods --------------------------------------------------------
	
	private static Image createImage()
	{
		return new Image(new byte[] {0, 1, 2});
	}
}
