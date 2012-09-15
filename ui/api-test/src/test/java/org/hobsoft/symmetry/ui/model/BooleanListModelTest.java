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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code BooleanListModel}.
 * 
 * @author Mark Hobson
 * @see BooleanListModel
 */
public class BooleanListModelTest
{
	// fields -----------------------------------------------------------------
	
	private BooleanListModel model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new BooleanListModel();
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemWithNegativeIndex()
	{
		model.getItem(-1);
	}
	
	@Test
	public void getItemWithZeroIndex()
	{
		assertEquals(Boolean.TRUE, model.getItem(0));
	}
	
	@Test
	public void getItemWithOneIndex()
	{
		assertEquals(Boolean.FALSE, model.getItem(1));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemWithLargeIndex()
	{
		model.getItem(2);
	}
	
	@Test
	public void getItemCount()
	{
		assertEquals(2, model.getItemCount());
	}
}
