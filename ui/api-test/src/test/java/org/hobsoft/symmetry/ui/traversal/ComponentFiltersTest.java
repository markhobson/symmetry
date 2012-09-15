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
import org.hobsoft.symmetry.ui.VBox;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code ComponentFilters}.
 * 
 * @author Mark Hobson
 * @see ComponentFilters
 */
public class ComponentFiltersTest
{
	// accept tests -----------------------------------------------------------
	
	@Test
	public void accept()
	{
		assertTrue(ComponentFilters.accept().accept(component()));
	}
	
	// reject tests -----------------------------------------------------------
	
	@Test
	public void reject()
	{
		assertFalse(ComponentFilters.reject().accept(component()));
	}

	// visible tests ----------------------------------------------------------
	
	@Test
	public void acceptWhenVisible()
	{
		assertTrue(ComponentFilters.visible().accept(component()));
	}
	
	@Test
	public void acceptWhenInvisible()
	{
		assertFalse(ComponentFilters.visible().accept(invisibleComponent()));
	}
	
	// not tests --------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void notWithNull()
	{
		ComponentFilters.not(null);
	}
	
	@Test
	public void notWithAccept()
	{
		assertFalse(ComponentFilters.not(ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void notWithReject()
	{
		assertTrue(ComponentFilters.not(ComponentFilters.reject()).accept(component()));
	}

	// and tests --------------------------------------------------------------
	
	@Test
	public void andWithAccept()
	{
		assertTrue(ComponentFilters.and(ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void andWithReject()
	{
		assertFalse(ComponentFilters.and(ComponentFilters.reject()).accept(component()));
	}

	@Test
	public void andWithAcceptAndAccept()
	{
		assertTrue(ComponentFilters.and(ComponentFilters.accept(), ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void andWithAcceptAndReject()
	{
		assertFalse(ComponentFilters.and(ComponentFilters.accept(), ComponentFilters.reject()).accept(component()));
	}
	
	@Test
	public void andWithRejectAndAccept()
	{
		assertFalse(ComponentFilters.and(ComponentFilters.reject(), ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void andWithRejectAndReject()
	{
		assertFalse(ComponentFilters.and(ComponentFilters.reject(), ComponentFilters.reject()).accept(component()));
	}

	// or tests ---------------------------------------------------------------
	
	@Test
	public void orWithAccept()
	{
		assertTrue(ComponentFilters.or(ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void orWithReject()
	{
		assertFalse(ComponentFilters.or(ComponentFilters.reject()).accept(component()));
	}

	@Test
	public void orWithAcceptAndAccept()
	{
		assertTrue(ComponentFilters.or(ComponentFilters.accept(), ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void orWithAcceptAndReject()
	{
		assertTrue(ComponentFilters.or(ComponentFilters.accept(), ComponentFilters.reject()).accept(component()));
	}
	
	@Test
	public void orWithRejectAndAccept()
	{
		assertTrue(ComponentFilters.or(ComponentFilters.reject(), ComponentFilters.accept()).accept(component()));
	}
	
	@Test
	public void orWithRejectAndReject()
	{
		assertFalse(ComponentFilters.or(ComponentFilters.reject(), ComponentFilters.reject()).accept(component()));
	}
	
	// type tests -------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void typeWithNull()
	{
		ComponentFilters.type(null);
	}
	
	@Test
	public void typeWithType()
	{
		assertTrue(ComponentFilters.type(Box.class).accept(new Box()));
	}
	
	@Test
	public void typeWithSubtype()
	{
		assertTrue(ComponentFilters.type(Box.class).accept(new VBox()));
	}
	
	@Test
	public void typeWithSupertype()
	{
		assertFalse(ComponentFilters.type(VBox.class).accept(new Box()));
	}
	
	@Test
	public void typeWithNullComponent()
	{
		assertFalse(ComponentFilters.type(Box.class).accept(null));
	}
	
	// private methods --------------------------------------------------------
	
	private static Component component()
	{
		return new DummyComponent();
	}
	
	private static Component invisibleComponent()
	{
		Component component = component();
		component.setVisible(false);
		
		return component;
	}
}
