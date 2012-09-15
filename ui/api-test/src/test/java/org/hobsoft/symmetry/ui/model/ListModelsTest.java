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
package org.hobsoft.symmetry.ui.model;

import org.hobsoft.symmetry.ui.test.model.ListModelSupport;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ListModels}.
 * 
 * @author Mark Hobson
 * @see ListModels
 */
@RunWith(JMock.class)
public class ListModelsTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void cacheItemCount()
	{
		ListModel<String> delegate = ListModelSupport.mockModel(mockery, "a");
		
		ListModel<String> actual = ListModels.cacheItemCount(delegate);
		
		// invoke twice and expect call once
		actual.getItemCount();
		actual.getItemCount();
	}
	
	@Test
	public void insertItemAtStart()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b");
		
		DefaultListModel<String> expected = new DefaultListModel<String>("x", "a", "b");
		
		assertEquals(expected, ListModels.insertItem(delegate, 0, "x"));
	}
	
	@Test
	public void insertItemAtMiddle()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b");
		
		DefaultListModel<String> expected = new DefaultListModel<String>("a", "x", "b");
		
		assertEquals(expected, ListModels.insertItem(delegate, 1, "x"));
	}
	
	@Test
	public void insertItemAtEnd()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b");
		
		DefaultListModel<String> expected = new DefaultListModel<String>("a", "b", "x");
		
		assertEquals(expected, ListModels.insertItem(delegate, 2, "x"));
	}
	
	@Test
	public void insertItemAfterEnd()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b");
		
		DefaultListModel<String> expected = new DefaultListModel<String>("a", "b", null, "x");
		
		assertEquals(expected, ListModels.insertItem(delegate, 3, "x"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void insertItemBeforeStart()
	{
		ListModels.insertItem(new DefaultListModel<String>(), -1, "x");
	}
	
	@Test
	public void addItem()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b");
		
		DefaultListModel<String> expected = new DefaultListModel<String>("a", "b", "x");
		
		assertEquals(expected, ListModels.addItem(delegate, "x"));
	}
	
	@Test
	public void removeItemWithIndexAtStart()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b", "c");
		
		DefaultListModel<String> expected = new DefaultListModel<String>("b", "c");
		
		assertEquals(expected, ListModels.removeItem(delegate, 0));
	}
	
	@Test
	public void removeItemWithIndexAtMiddle()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b", "c");
		
		DefaultListModel<String> expected = new DefaultListModel<String>("a", "c");
		
		assertEquals(expected, ListModels.removeItem(delegate, 1));
	}
	
	@Test
	public void removeItemWithIndexAtEnd()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b", "c");
		
		DefaultListModel<String> expected = new DefaultListModel<String>("a", "b");
		
		assertEquals(expected, ListModels.removeItem(delegate, 2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void removeItemWithIndexAfterEnd()
	{
		DefaultListModel<String> delegate = new DefaultListModel<String>("a", "b", "c");
		
		ListModels.removeItem(delegate, 3);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void removeItemWithIndexBeforeStart()
	{
		ListModels.removeItem(new DefaultListModel<Object>(), -1);
	}
}
