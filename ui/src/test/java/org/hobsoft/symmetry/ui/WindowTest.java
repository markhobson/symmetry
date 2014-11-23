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

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests {@code Window}.
 */
public class WindowTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private ExpectedException thrown = ExpectedException.none();
	
	private Window window;
	
	// ----------------------------------------------------------------------------------------------------------------
	// JUnit methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		window = new Window();
	}

	@Rule
	public ExpectedException getThrown()
	{
		return thrown;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void acceptInvokesVisitWindow()
	{
		ComponentVisitor<String, RuntimeException> visitor = mock(ComponentVisitor.class);
		
		window.accept(visitor, "p");
		
		verify(visitor).visit(window, "p");
	}
	
	@Test
	public void acceptInvokesAcceptOnChild()
	{
		Component child = mock(Component.class);
		window.add(child);
		ComponentVisitor<String, RuntimeException> visitor = mock(ComponentVisitor.class);
		
		window.accept(visitor, "p");
		
		verify(child).accept(visitor, "p");
	}
	
	@Test
	public void acceptInvokesAcceptOnChildren()
	{
		Component child1 = mock(Component.class);
		window.add(child1);
		Component child2 = mock(Component.class);
		window.add(child2);
		Component child3 = mock(Component.class);
		window.add(child3);
		ComponentVisitor<String, RuntimeException> visitor = mock(ComponentVisitor.class);
		
		window.accept(visitor, "p");
		
		verify(child1).accept(visitor, "p");
		verify(child2).accept(visitor, "p");
		verify(child3).accept(visitor, "p");
	}
	
	@Test
	public void acceptInvokesEndVisitWindow()
	{
		ComponentVisitor<String, RuntimeException> visitor = mock(ComponentVisitor.class);
		
		window.accept(visitor, "p");
		
		verify(visitor).endVisit(window, "p");
	}
	
	@Test
	public void addWithComponentAddsComponent()
	{
		Component component = mock(Component.class);
		
		window.add(component);
		
		assertThat(window.getComponents(), hasItem(component));
	}
	
	@Test
	public void getComponentsReturnsImmutableList()
	{
		window.add(mock(Component.class));
		
		List<Component> actual = window.getComponents();
		
		thrown.expect(UnsupportedOperationException.class);
		actual.add(mock(Component.class));
	}
}
