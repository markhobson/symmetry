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

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.reflect.TypeToken;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code PreorderComponentVisitor}.
 * 
 * @author Mark Hobson
 * @see PreorderComponentVisitor
 */
@RunWith(JMock.class)
public class PreorderComponentVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery() { {
		setImposteriser(ClassImposteriser.INSTANCE);
	} };

	// tests ------------------------------------------------------------------
	
	@Test
	public void visitInvokesPreorderVisit()
	{
		final PreorderComponentVisitor<Object, RuntimeException> visitor = mockVisitor();
		final DummyComponent component = createComponent();
		final Object parameter = createVisitorParameter();
		
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(component, parameter);
		} });
		
		component.accept(visitor, parameter);
	}
	
	@Test
	public void visitInvokesPreorderVisitBeforeChildren()
	{
		final PreorderComponentVisitor<Object, RuntimeException> visitor = mockVisitor();
		final DummyComponent child1 = createComponent();
		final DummyComponent child2 = createComponent();
		final Box component = new Box(child1, child2);
		final Object parameter = createVisitorParameter();
		
		final Sequence sequence = mockery.sequence("visit");
		mockery.checking(new Expectations() { {
			oneOf(visitor).visit(component, parameter); inSequence(sequence);
			oneOf(visitor).visit(child1, parameter); inSequence(sequence);
			oneOf(visitor).visit(child2, parameter); inSequence(sequence);
		} });
		
		component.accept(visitor, parameter);
	}
	
	@Test
	public void visitDoesNotReturnHierarchicalVisitor()
	{
		final PreorderComponentVisitor<Object, RuntimeException> visitor = mockVisitor();
		
		mockery.checking(new Expectations() { {
			allowing(visitor).visit(with(any(Component.class)), with(any(Object.class)));
		} });
		
		assertNull(visitor.visit(TypeToken.of(DummyComponent.class), createComponent(), createVisitorParameter()));
	}
	
	// private methods --------------------------------------------------------
	
	private <P, E extends Exception> PreorderComponentVisitor<P, E> mockVisitor()
	{
		// cannot safely mock parameterized types
		@SuppressWarnings("unchecked")
		PreorderComponentVisitor<P, E> visitor = mockery.mock(PreorderComponentVisitor.class);
		
		return visitor;
	}
	
	private static DummyComponent createComponent()
	{
		return new DummyComponent();
	}
}
