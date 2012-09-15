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
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;
import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code PhasedHierarchicalComponentHydratorSupport}.
 * 
 * @author Mark Hobson
 * @see PhasedHierarchicalComponentHydratorSupport
 */
public class PhasedHierarchicalComponentHydratorSupportTest
{
	// fields -----------------------------------------------------------------
	
	private PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<Component, HydrationContext,
		HydrationException>> support;
	
	private HierarchicalComponentVisitor<Component, HydrationContext, HydrationException> defaultVisitor;
	
	private HierarchicalComponentVisitor<Component, HydrationContext, HydrationException> visitor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		support = createSupport();
		defaultVisitor = ComponentVisitors.<Component, HydrationContext, HydrationException>nullHierarchicalVisitor();
		visitor = ComponentVisitors.<Component, HydrationContext, HydrationException>nullHierarchicalVisitor();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void getDelegateHydrationContext()
	{
		support.setDelegate(DEHYDRATE, visitor);
		
		assertEquals(visitor, support.getDelegate(createHydrationContext(DEHYDRATE)));
	}
	
	@Test
	public void getDelegateHydrationContextWhenDefaultDelegate()
	{
		support = createSupport(defaultVisitor);
		support.setDelegate(DEHYDRATE, visitor);
		
		assertEquals(visitor, support.getDelegate(createHydrationContext(DEHYDRATE)));
	}
	
	@Test
	public void getDelegateHydrationContextWhenNotSet()
	{
		assertNull(support.getDelegate(createHydrationContext(DEHYDRATE)));
	}
	
	@Test
	public void getDelegateHydrationContextWhenNotSetAndDefaultDelegate()
	{
		support = createSupport(defaultVisitor);
		
		assertEquals(defaultVisitor, support.getDelegate(createHydrationContext(DEHYDRATE)));
	}
	
	@Test
	public void getDelegatePhase()
	{
		support.setDelegate(DEHYDRATE, visitor);
		
		assertEquals(visitor, support.getDelegate(DEHYDRATE));
	}
	
	@Test
	public void getDelegatePhaseWhenDefaultDelegate()
	{
		support = createSupport(defaultVisitor);
		support.setDelegate(DEHYDRATE, visitor);
		
		assertEquals(visitor, support.getDelegate(DEHYDRATE));
	}
	
	@Test
	public void getDelegatePhaseWhenNotSet()
	{
		assertNull(support.getDelegate(DEHYDRATE));
	}
	
	@Test
	public void getDelegatePhaseWhenNotSetAndDefaultDelegate()
	{
		support = createSupport(defaultVisitor);
		
		assertEquals(defaultVisitor, support.getDelegate(DEHYDRATE));
	}
	
	@Test(expected = NullPointerException.class)
	public void setDelegateWithNullPhase()
	{
		support.setDelegate(null, visitor);
	}
	
	@Test
	public void setDelegateWithNullVisitor()
	{
		support.setDelegate(DEHYDRATE, visitor);
		
		support.setDelegate(DEHYDRATE, null);
		
		assertNull(support.getDelegate(DEHYDRATE));
	}
	
	// private methods --------------------------------------------------------
	
	private static PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<Component, HydrationContext,
		HydrationException>> createSupport()
	{
		return createSupport(null);
	}
	
	private static PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<Component, HydrationContext,
		HydrationException>> createSupport(
			HierarchicalComponentVisitor<Component, HydrationContext, HydrationException> delegate)
	{
		return new PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<Component, HydrationContext,
			HydrationException>>(delegate);
	}

	private static HydrationContext createHydrationContext()
	{
		// TODO: use FakeHydrationContext
		return new RehydrationContext();
	}
	
	private static HydrationContext createHydrationContext(HydrationPhase phase)
	{
		HydrationContext context = createHydrationContext();
		context.set(HydrationPhase.class, phase);
		return context;
	}
}
