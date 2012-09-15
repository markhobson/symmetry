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

import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.traversal.ComponentFilter;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hobsoft.symmetry.ui.traversal.ComponentFilters.accept;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code ComponentUtils}.
 * 
 * @author Mark Hobson
 * @see ComponentUtils
 */
@RunWith(JMock.class)
public class ComponentUtilsTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void getAncestorWithComponentWithNullParent()
	{
		Component component = createComponent();
		
		assertNull(ComponentUtils.getAncestor(component, accept()));
	}

	@Test
	public void getAncestorWithComponentWithNullParentDoesNotInvokeFilter()
	{
		Component component = createComponent();
		ComponentFilter filter = mockery.mock(ComponentFilter.class);
		
		ComponentUtils.getAncestor(component, filter);
	}
	
	@Test
	public void getAncestorWithComponentMatchesParent()
	{
		Component component = createNestedComponent();
		
		assertSame(component.getParent(), ComponentUtils.getAncestor(component, accept()));
	}

	// private methods --------------------------------------------------------
	
	private static Component createComponent()
	{
		return new DummyComponent();
	}
	
	private static Component createNestedComponent()
	{
		return new Box(new DummyComponent());
	}
}
