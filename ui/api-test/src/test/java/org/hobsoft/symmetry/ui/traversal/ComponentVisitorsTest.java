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

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.reflect.TypeToken;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.skipChildren;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.skipSiblings;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ComponentVisitors}.
 * 
 * @author Mark Hobson
 * @see ComponentVisitors
 */
@RunWith(JMock.class)
public class ComponentVisitorsTest
{
	// types ------------------------------------------------------------------
	
	private static class GenericDummyComponent<T> extends DummyComponent
	{
		// simple subtype
	}
	
	private static class ClassImplementationVisitor
		implements HierarchicalComponentVisitor<DummyComponent, Void, RuntimeException>
	{
		@Override
		public Visit visit(DummyComponent component, Void parameter)
		{
			return null;
		}
		
		@Override
		public EndVisit endVisit(DummyComponent component, Void parameter)
		{
			return null;
		}
	}

	private static class ParameterizedTypeImplementationVisitor
		implements HierarchicalComponentVisitor<GenericDummyComponent<?>, Void, RuntimeException>
	{
		@Override
		public Visit visit(GenericDummyComponent<?> component, Void parameter)
		{
			return null;
		}
		
		@Override
		public EndVisit endVisit(GenericDummyComponent<?> component, Void parameter)
		{
			return null;
		}
	}
	
	private static class TypeVariableImplementationVisitor<T extends Component>
		implements HierarchicalComponentVisitor<T, Void, RuntimeException>
	{
		@Override
		public Visit visit(T component, Void parameter)
		{
			return null;
		}
		
		@Override
		public EndVisit endVisit(T component, Void parameter)
		{
			return null;
		}
	}

	private static class SuperclassClassImplementationVisitor
		extends NullHierarchicalComponentVisitor<DummyComponent, Void, RuntimeException>
	{
		// simple subtype
	}
	
	private interface IndirectHierarchicalComponentVisitor<T extends Component, P, E extends Exception>
		extends HierarchicalComponentVisitor<T, P, E>
	{
		// simple subtype for level of indirection
	}

	private static class SuperinterfaceClassImplementationVisitor
		implements IndirectHierarchicalComponentVisitor<DummyComponent, Void, RuntimeException>
	{
		@Override
		public Visit visit(DummyComponent component, Void parameter)
		{
			return null;
		}
		
		@Override
		public EndVisit endVisit(DummyComponent component, Void parameter)
		{
			return null;
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// skipChildren tests -----------------------------------------------------
	
	@Test
	public void skipChildrenVisit() throws Exception
	{
		assertEquals(SKIP_CHILDREN, skipChildren().visit(null, null));
	}
	
	@Test
	public void skipChildrenEndVisit() throws Exception
	{
		assertEquals(VISIT_SIBLINGS, skipChildren().endVisit(null, null));
	}
	
	@Test
	public void skipChildrenWithDelegateVisit() throws Exception
	{
		final HierarchicalComponentVisitor<Component, String, RuntimeException> delegate = createHierarchicalVisitor(
			mockery);
		final DummyComponent component = new DummyComponent();
		
		mockery.checking(new Expectations() { {
			oneOf(delegate).visit(component, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(SKIP_CHILDREN, skipChildren(delegate).visit(component, "x"));
	}
	
	@Test
	public void skipChildrenWithDelegateEndVisit()
	{
		final HierarchicalComponentVisitor<Component, String, RuntimeException> delegate = createHierarchicalVisitor(
			mockery);
		final DummyComponent component = new DummyComponent();
		
		mockery.checking(new Expectations() { {
			oneOf(delegate).endVisit(component, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(VISIT_SIBLINGS, skipChildren(delegate).endVisit(component, "x"));
	}
	
	@Test
	public void skipChildrenWithNullDelegateVisit() throws Exception
	{
		assertEquals(SKIP_CHILDREN, skipChildren(null).visit(null, null));
	}
	
	@Test
	public void skipChildrenWithNullDelegateEndVisit() throws Exception
	{
		assertEquals(VISIT_SIBLINGS, skipChildren(null).endVisit(null, null));
	}
	
	// skipSiblings tests -----------------------------------------------------
	
	@Test
	public void skipSiblingsVisit() throws Exception
	{
		assertEquals(VISIT_CHILDREN, skipSiblings().visit(null, null));
	}
	
	@Test
	public void skipSiblingsEndVisit() throws Exception
	{
		assertEquals(SKIP_SIBLINGS, skipSiblings().endVisit(null, null));
	}
	
	@Test
	public void skipSiblingsWithDelegateVisit() throws Exception
	{
		final HierarchicalComponentVisitor<Component, String, RuntimeException> delegate = createHierarchicalVisitor(
			mockery);
		final DummyComponent component = new DummyComponent();
		
		mockery.checking(new Expectations() { {
			oneOf(delegate).visit(component, "x"); will(returnValue(VISIT_CHILDREN));
		} });
		
		assertEquals(VISIT_CHILDREN, skipSiblings(delegate).visit(component, "x"));
	}
	
	@Test
	public void skipSiblingsWithDelegateEndVisit()
	{
		final HierarchicalComponentVisitor<Component, String, RuntimeException> delegate = createHierarchicalVisitor(
			mockery);
		final DummyComponent component = new DummyComponent();
		
		mockery.checking(new Expectations() { {
			oneOf(delegate).endVisit(component, "x"); will(returnValue(VISIT_SIBLINGS));
		} });
		
		assertEquals(SKIP_SIBLINGS, skipSiblings(delegate).endVisit(component, "x"));
	}
	
	@Test
	public void skipSiblingsWithNullDelegateVisit() throws Exception
	{
		assertEquals(VISIT_CHILDREN, skipSiblings(null).visit(null, null));
	}
	
	@Test
	public void skipSiblingsWithNullDelegateEndVisit() throws Exception
	{
		assertEquals(SKIP_SIBLINGS, skipSiblings(null).endVisit(null, null));
	}
	
	// getComponentType tests -------------------------------------------------
	
	@Test
	public void getComponentTypeWithClassImplementation()
	{
		assertEquals(TypeToken.of(DummyComponent.class),
			ComponentVisitors.getComponentType(new ClassImplementationVisitor()));
	}
	
	@Test
	public void getComponentTypeWithParameterizedTypeImplementation()
	{
		assertEquals(new TypeToken<GenericDummyComponent<?>>() { /**/ },
			ComponentVisitors.getComponentType(new ParameterizedTypeImplementationVisitor()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getComponentTypeWithTypeVariableImplementation()
	{
		ComponentVisitors.getComponentType(new TypeVariableImplementationVisitor<DummyComponent>());
	}

	@Test
	public void getComponentTypeWithSuperclassClassImplementation()
	{
		assertEquals(TypeToken.of(DummyComponent.class),
			ComponentVisitors.getComponentType(new SuperclassClassImplementationVisitor()));
	}
	
	@Test
	public void getComponentTypeWithSuperinterfaceClassImplementation()
	{
		assertEquals(TypeToken.of(DummyComponent.class),
			ComponentVisitors.getComponentType(new SuperinterfaceClassImplementationVisitor()));
	}
}
