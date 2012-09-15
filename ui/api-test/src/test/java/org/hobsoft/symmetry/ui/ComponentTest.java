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
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code Component}.
 * 
 * @author Mark Hobson
 * @see Component
 */
public class ComponentTest extends AbstractComponentTest<DummyComponent>
{
	// tests ------------------------------------------------------------------

	@Test
	public void newComponent()
	{
		Component component = getComponent();
		
		assertFalse("Transient", component.isTransient());
		assertTrue("Visible", component.isVisible());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DummyComponent createComponent()
	{
		return new DummyComponent();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<DummyComponent> getComponentType()
	{
		return Generic.get(DummyComponent.class);
	}
}
