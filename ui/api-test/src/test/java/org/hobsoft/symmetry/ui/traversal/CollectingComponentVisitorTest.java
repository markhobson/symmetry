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
package org.hobsoft.symmetry.ui.traversal;

import java.util.Arrays;
import java.util.Collections;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@code CollectingComponentVisitor}.
 * 
 * @author Mark Hobson
 * @see CollectingComponentVisitor
 */
public class CollectingComponentVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private CollectingComponentVisitor visitor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		visitor = new CollectingComponentVisitor();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void visitNone()
	{
		assertEquals(Collections.emptyList(), visitor.getComponents());
	}
	
	@Test
	public void visitSingle()
	{
		Component component = createComponent();
		
		assertNotNull(visitor.visit(Generic.get(Component.class), component, null));
		
		assertEquals(Collections.singletonList(component), visitor.getComponents());
	}
	
	@Test
	public void visitMultiple()
	{
		Component[] components = new Component[] {
			createComponent(),
			createComponent(),
			createComponent()
		};
		
		visitor.visit(Generic.get(Component.class), components[0], null);
		visitor.visit(Generic.get(Component.class), components[1], null);
		visitor.visit(Generic.get(Component.class), components[2], null);
		
		assertEquals(Arrays.asList(components), visitor.getComponents());
	}
	
	// private methods --------------------------------------------------------
	
	private static Component createComponent()
	{
		return new DummyComponent();
	}
}
