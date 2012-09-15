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

import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitor;
import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static org.hobsoft.symmetry.ui.traversal.ComponentFilters.accept;
import static org.hobsoft.symmetry.ui.traversal.ComponentFilters.reject;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code FilteringComponentVisitor}.
 * 
 * @author Mark Hobson
 * @see FilteringComponentVisitor
 */
@RunWith(JMock.class)
public class FilteringComponentVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void visitWhenAccept() throws Exception
	{
		final DummyComponent component = createComponent();
		final Object parameter = createVisitorParameter();
		
		final HierarchicalComponentVisitor<DummyComponent, Object, RuntimeException> subvisitor
			= nullHierarchicalVisitor();
		
		final ComponentVisitor<Object, RuntimeException> delegate = createVisitor(mockery);
		
		mockery.checking(new Expectations() { {
			oneOf(delegate).visit(Generic.get(DummyComponent.class), component, parameter);
				will(returnValue(subvisitor));
		} });
		
		FilteringComponentVisitor<Object, RuntimeException> visitor = createFilteringVisitor(delegate, accept());
		
		assertSame(subvisitor, visitor.visit(Generic.get(DummyComponent.class), component, parameter));
	}
	
	@Test
	public void visitWhenReject() throws Exception
	{
		DummyComponent component = createComponent();
		Object parameter = createVisitorParameter();
		
		FilteringComponentVisitor<Object, RuntimeException> visitor = createFilteringVisitor(reject());
		
		HierarchicalComponentVisitor<DummyComponent, Object, RuntimeException> actual = visitor.visit(
			Generic.get(DummyComponent.class), component, parameter);
		
		assertEquals(SKIP_CHILDREN, actual.visit(component, parameter));
	}
	
	// private methods --------------------------------------------------------
	
	private static <P, E extends Exception> FilteringComponentVisitor<P, E> createFilteringVisitor(
		ComponentVisitor<P, E> delegate, ComponentFilter filter)
	{
		return new FilteringComponentVisitor<P, E>(delegate, filter);
	}
	
	private <P, E extends Exception> FilteringComponentVisitor<P, E> createFilteringVisitor(ComponentFilter filter)
	{
		ComponentVisitor<P, E> delegate = createVisitor(mockery);
		
		return createFilteringVisitor(delegate, filter);
	}

	private static DummyComponent createComponent()
	{
		return new DummyComponent();
	}
}
