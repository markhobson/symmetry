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

import com.googlecode.jtype.Generic;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Window}.
 * 
 * @author Mark Hobson
 * @see Window
 */
public class WindowTest extends AbstractComponentTest<Window>
{
	// fields -----------------------------------------------------------------
	
	private Window window;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		window = getComponent();
	}

	@Test
	public void setTitle()
	{
		window.setTitle("a");
		
		assertEquals("a", window.getTitle());
	}
	
	@Test
	public void setTitleWithNull()
	{
		window.setTitle(null);
		
		assertEquals("", window.getTitle());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Window createComponent()
	{
		return new Window();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<Window> getComponentType()
	{
		return Generic.get(Window.class);
	}
}
