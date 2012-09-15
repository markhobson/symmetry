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
import org.hobsoft.symmetry.ui.test.DummySubComponent;
import org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.test.traversal.MockComponentVisitors.createVisitorParameter;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@code CompositeComponentVisitor}.
 * 
 * @author Mark Hobson
 * @see CompositeComponentVisitor
 */
@RunWith(JMock.class)
public class CompositeComponentVisitorTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void visitWithComponentWhenNoDelegates()
	{
		CompositeComponentVisitor<Void, RuntimeException> visitor = createCompositeVisitor();
		
		visitor.visit(Generic.get(Component.class), createComponent(), null);
	}
	
	@Test
	public void visitWithComponentWhenDelegate() throws Exception
	{
		CompositeComponentVisitor<Object, RuntimeException> visitor = createCompositeVisitor();
		
		visitor.setDelegate(DummyComponent.class, MockComponentVisitors
			.<DummyComponent, Object, RuntimeException>createHierarchicalVisitor(mockery));
		
		assertNotNull(visitor.visit(Generic.get(DummyComponent.class), createComponent(), createVisitorParameter()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void visitWithSubtypeComponentWhenSupertypeDelegate() throws Exception
	{
		CompositeComponentVisitor<Object, RuntimeException> visitor = createCompositeVisitor();
		
		visitor.setDelegate(DummyComponent.class, MockComponentVisitors
			.<DummyComponent, Object, RuntimeException>createHierarchicalVisitor(mockery));
		
		visitor.visit(Generic.get(DummySubComponent.class), createSubtypeComponent(), createVisitorParameter());
	}
	
	// private methods --------------------------------------------------------
	
	private static <P, E extends Exception> CompositeComponentVisitor<P, E> createCompositeVisitor()
	{
		return new CompositeComponentVisitor<P, E>();
	}
	
	private static DummyComponent createComponent()
	{
		return new DummyComponent();
	}
	
	private static DummySubComponent createSubtypeComponent()
	{
		return new DummySubComponent();
	}
}
